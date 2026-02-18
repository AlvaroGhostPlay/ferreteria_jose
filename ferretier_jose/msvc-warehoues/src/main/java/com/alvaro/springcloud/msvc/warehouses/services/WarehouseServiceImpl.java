package com.alvaro.springcloud.msvc.warehouses.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvaro.springcloud.msvc.warehouses.entities.Warehouse;
import com.alvaro.springcloud.msvc.warehouses.repositories.WarehouseRepository;

@Service
public class WarehouseServiceImpl implements WarehouseService{

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Warehouse> getWarehouse(UUID id) {
        Optional<Warehouse> wareOptional = warehouseRepository.findByWarehouseId(id);   
        return wareOptional;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Warehouse> getWarehouses(Pageable pageable) {
        return this.warehouseRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Optional<Warehouse> save(Warehouse warehouse) {
        Warehouse dats = new Warehouse();
        dats.setCreatedAt(LocalDateTime.now());
        dats.setUpdateAt(LocalDateTime.now());
        dats.setWarehouseName(warehouse.getWarehouseName());
        dats.setEnabled(warehouse.getEnabled());
        return Optional.of(this.warehouseRepository.save(dats));
    }

    @Transactional
    @Override
    public Optional<Warehouse> update(Warehouse warehouse, UUID id) {
        Optional<Warehouse> optional = this.warehouseRepository.findByWarehouseId(id);
        if (optional.isPresent()) {
            optional.get().setWarehouseName(warehouse.getWarehouseName());
            optional.get().setEnabled(warehouse.getEnabled());
            optional.get().setUpdateAt(LocalDateTime.now());
            return optional;
        }
        return Optional.empty();
    }

    
    @Transactional
    @Override
    public Optional<Warehouse> deleteById(UUID id) {
        Optional<Warehouse> optional = this.warehouseRepository.findByWarehouseId(id);
        if (optional.isPresent()) {
            return this.warehouseRepository.deleteByWarehouseId(id);
        }
        return Optional.empty();
    }
    
}
