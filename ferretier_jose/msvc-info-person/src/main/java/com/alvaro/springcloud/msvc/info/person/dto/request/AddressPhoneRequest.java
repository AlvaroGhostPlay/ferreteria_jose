package com.alvaro.springcloud.msvc.info.person.dto.request;

import java.util.List;

import com.alvaro.springcloud.msvc.info.person.entities.Address;
import com.alvaro.springcloud.msvc.info.person.entities.Phone;

import jakarta.validation.Valid;

public class AddressPhoneRequest {

    @Valid
    private List<Address> addresses;

    @Valid
    private List<Phone> phones;

    public List<Address> getAddresses(){
        return this.addresses;
    }

    public void setAddress(@Valid List<Address> addresses){
        this.addresses = addresses;
    }

    public List<Phone> getPhones(){
        return this.phones;
    }

    public void setPhone(@Valid List<Phone> phones){
        this.phones = phones;
    }

}
