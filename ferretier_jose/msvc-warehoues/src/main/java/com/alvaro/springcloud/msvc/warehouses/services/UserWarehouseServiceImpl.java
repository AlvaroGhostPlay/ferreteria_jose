package com.alvaro.springcloud.msvc.warehouses.services;

import com.alvaro.springcloud.msvc.warehouses.entities.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserWarehouseServiceImpl {
    @Autowired
    private WebClient webClient;

}
