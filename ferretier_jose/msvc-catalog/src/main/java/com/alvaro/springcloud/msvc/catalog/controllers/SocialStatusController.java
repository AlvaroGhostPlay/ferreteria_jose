package com.alvaro.springcloud.msvc.catalog.controllers;

import com.alvaro.springcloud.msvc.catalog.entities.SocialStatus;
import com.alvaro.springcloud.msvc.catalog.services.socila_status.SocialStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/social-status")
public class SocialStatusController {

    @Autowired
    private SocialStatusService socialStatusService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAllGenerTypePage(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(socialStatusService.getAllSocialStatusPage(pageable));
    }

    @GetMapping
    public ResponseEntity<?> getGenerTypeAll() {
        return ResponseEntity.ok().body(socialStatusService.getAllSocilaStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGenerType(@PathVariable String id) {
        Optional<SocialStatus> response = socialStatusService.getSocialStatusById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SocialStatus request){
        Optional<SocialStatus> response = socialStatusService.save(request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(201).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody SocialStatus request, @PathVariable String id){
        Optional<SocialStatus> response = socialStatusService.update(request, id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<SocialStatus> response = socialStatusService.delete(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
