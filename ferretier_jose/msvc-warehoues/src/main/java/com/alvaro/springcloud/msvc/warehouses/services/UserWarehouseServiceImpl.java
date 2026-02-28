package com.alvaro.springcloud.msvc.warehouses.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.alvaro.springcloud.msvc.warehouses.DTO.request.CreateUserWarehouseJobRole;
import com.alvaro.springcloud.msvc.warehouses.DTO.response.JobRoleDTO;
import com.alvaro.springcloud.msvc.warehouses.DTO.response.UserDTO;
import com.alvaro.springcloud.msvc.warehouses.DTO.response.UserJobsWarehousesResponse;
import com.alvaro.springcloud.msvc.warehouses.DTO.response.UserWarhouseDTO;
import com.alvaro.springcloud.msvc.warehouses.entities.JobRole;
import com.alvaro.springcloud.msvc.warehouses.entities.UserJobRoleWarehouseId;
import com.alvaro.springcloud.msvc.warehouses.entities.UserWarehouse;
import com.alvaro.springcloud.msvc.warehouses.entities.Warehouse;
import com.alvaro.springcloud.msvc.warehouses.repositories.JobRoleRepository;
import com.alvaro.springcloud.msvc.warehouses.repositories.UserJobRoleWarehouseRepository;
import com.alvaro.springcloud.msvc.warehouses.repositories.WarehouseRepository;

import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;

@Service
public class UserWarehouseServiceImpl implements UserWarehouseService {

    @Autowired
    private UserJobRoleWarehouseRepository userJobRoleWarehouseRepository;

    @Autowired
    private JobRoleRepository jobRoleRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WebClient webClient;

    @Override
    public List<UserJobsWarehousesResponse> getAllUserJobWarehouses() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<UserJobsWarehousesResponse> getUserJobWarehouse(UUID id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Page<UserJobsWarehousesResponse> getUserJobWarehouses(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserWarhouseDTO> getUsersByWarehouse(UUID warehouseId) {
        Set<UUID> uniqueUserIds = userJobRoleWarehouseRepository.findAllById_IdWarehouse(warehouseId)
                .stream()
                .map(uw -> uw.getId().getIdUser())
                .collect(Collectors.toSet());

        return uniqueUserIds.stream()
                .map(this::getUserById)
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    @Transactional
    public Mono<UserWarhouseDTO> saveUsersByWarehouse(CreateUserWarehouseJobRole warehouseId) {
        UserWarehouse dat = new UserWarehouse();
        UserJobRoleWarehouseId id = new UserJobRoleWarehouseId(warehouseId.idUser(), warehouseId.idJobRole(),
                warehouseId.idWarehouse());
        Warehouse warehouse = warehouseRepository.getReferenceById(warehouseId.idWarehouse());
        JobRole jobrole = jobRoleRepository.getReferenceById(warehouseId.idJobRole());

        dat.setJobRole(jobrole);
        dat.setWarehouse(warehouse);
        dat.setId(id);

        this.userJobRoleWarehouseRepository.save(dat);
        return getUserByIdReactive(id.getIdUser());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserWarhouseDTO> getJobRolesByUserId(UUID id) {
        List<UserDTO> users = new ArrayList<>();
        Set<JobRoleDTO> uniqueRolesIds = userJobRoleWarehouseRepository.findAllById_IdUser(id)
                .stream()
                .map(uw ->  new JobRoleDTO(uw.getId().getIdJobRole(), uw.getJobRole().getJobRole()))
                .collect(Collectors.toSet());
        UserWarhouseDTO response = new UserWarhouseDTO(
                null,
                uniqueRolesIds
        );
        return Optional.of(response);
    }

    private UserWarhouseDTO getUserById(UUID userId) {
        UserDTO user = null;
        try {
            user = webClient.get()
                    .uri("http://msvc-users/{id}", userId) // ← usa baseUrl
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError,
                            r -> Mono.error(new RuntimeException("User service error")))
                    .bodyToMono(UserDTO.class)
                    .block();
            System.out.println(user.username());
        } catch (Exception e) {
            System.out.println(e);
            return null; // o lanza excepción de negocio
        }
        Set<UserWarehouse> userWarehouses = this.userJobRoleWarehouseRepository.getRoleByUserId(user.userId());
        Set<JobRoleDTO> jobRoleDTOs = userWarehouses.stream()
                .map(uw -> new JobRoleDTO(
                        uw.getJobRole().getJobRoleId(),
                        uw.getJobRole().getJobRole()))
                .collect(Collectors.toSet());

        return new UserWarhouseDTO(user, jobRoleDTOs);
    }

    private Mono<UserWarhouseDTO> getUserByIdReactive(UUID userId) {

    return webClient.get()
            .uri("http://msvc-users/{id}", userId)
            .retrieve()
            .bodyToMono(UserDTO.class)
            .switchIfEmpty(Mono.error(
                    new RuntimeException("Usuario no encontrado")))
            .map(user -> {

                Set<JobRoleDTO> roles =
                        userJobRoleWarehouseRepository
                                .getRoleByUserId(user.userId())
                                .stream()
                                .map(uw -> new JobRoleDTO(
                                        uw.getJobRole().getJobRoleId(),
                                        uw.getJobRole().getJobRole()))
                                .collect(Collectors.toSet());

                return new UserWarhouseDTO(user, roles);
            });
}
}
