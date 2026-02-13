package com.alvaro.springcloud.msvc.info.person.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.alvaro.springcloud.msvc.info.person.dto.request.AddressPhoneRequest;
import com.alvaro.springcloud.msvc.info.person.dto.response.AddressDTO;
import com.alvaro.springcloud.msvc.info.person.dto.response.AddressPhoneResponse;
import com.alvaro.springcloud.msvc.info.person.dto.response.PhoneDTO;
import com.alvaro.springcloud.msvc.info.person.entities.Address;
import com.alvaro.springcloud.msvc.info.person.entities.Phone;
import com.alvaro.springcloud.msvc.info.person.services.AddressPhoneService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AddressPhoneController {

    @Autowired
    private AddressPhoneService addressPhoneService;

    @GetMapping("/phones/{id}")
    public ResponseEntity<?> getOnlyPhones(@PathVariable UUID id) {
        List<String> respose = addressPhoneService.findPhonesByIdPerson(id);
        return ResponseEntity.ok().body(respose);
    }
    
    @GetMapping("/all/{id}")
    public ResponseEntity<?> getAllInfo(@PathVariable UUID id) {
        Optional<AddressPhoneResponse> respose = addressPhoneService.findByIdAll(id);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/address/all/by-person/{id}")
    public ResponseEntity<?> getAllAddressesByPerson(@PathVariable UUID id) {
        List<AddressDTO> respose = addressPhoneService.findByIdAllAddress(id);
        return ResponseEntity.ok().body(respose);
    }

    @GetMapping("/phone/all/by-person/{id}")
    public ResponseEntity<?> getAllPhonesByPerson(@PathVariable UUID id) {
        List<PhoneDTO> respose = addressPhoneService.findByIdAllPhones(id);
        return ResponseEntity.ok().body(respose);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<?> getAddress(@PathVariable UUID id) {
        Optional<AddressDTO> respose = addressPhoneService.getAddress(id);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/phone/{id}")
    public ResponseEntity<?> getPhone(@PathVariable UUID id) {
        Optional<PhoneDTO> respose = addressPhoneService.getPhone(id);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/address-phone")
    public ResponseEntity<?> saveAddresPhone(@RequestBody AddressPhoneRequest request) {
        Optional<AddressPhoneResponse> respose = addressPhoneService.save(request);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //revisar esto ya que no puede eliminar las direcciones que no se envien en el request
    @PutMapping("/address-phone")
    public ResponseEntity<?> updateAddressPhone(@RequestBody AddressPhoneRequest request) {
        Optional<AddressPhoneResponse> respose = addressPhoneService.update(request);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/address-phone/{id}")
    public ResponseEntity<?> deleteAddressPhone(@PathVariable UUID id) {
        Optional<AddressPhoneResponse> respose = addressPhoneService.deleteAll(id);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/address-person/{id}")
    public ResponseEntity<?> deleteAddressAll(@PathVariable UUID id) {
        List<AddressDTO> respose = addressPhoneService.deleteAllAddress(id);
        if(respose != null){
            return ResponseEntity.ok().body(respose);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/phone-person/{id}")
    public ResponseEntity<?> deletePhoneAll(@PathVariable UUID id) {
        List<PhoneDTO> respose = addressPhoneService.deleteAllPhones(id);
        if(respose != null){
            return ResponseEntity.ok().body(respose);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/address")
    public ResponseEntity<?> saveAddres(@RequestBody Address request) {
        Optional<AddressDTO> respose = addressPhoneService.saveAddress(request);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<?> updateAddress(@RequestBody Address request, UUID id) {
        Optional<AddressDTO> respose = addressPhoneService.updateAddress(request, id);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable UUID id) {
        Optional<AddressDTO> respose = addressPhoneService.deleteAddress(id);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/phone")
    public ResponseEntity<?> savePhone(@RequestBody Phone request) {
        Optional<PhoneDTO> respose = addressPhoneService.savePhone(request);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/phone/{id}")
    public ResponseEntity<?> updatePhone(@RequestBody Phone request, @PathVariable UUID id) {
        Optional<PhoneDTO> respose = addressPhoneService.updatePhone(request, id);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/phone/{id}")
    public ResponseEntity<?> deletePhone(@PathVariable UUID id) {
        Optional<PhoneDTO> respose = addressPhoneService.deletePhone(id);
        return respose.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
