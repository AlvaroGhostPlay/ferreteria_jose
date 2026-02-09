package com.alvaro.springcloud.msvc.info.person.dto.response;

import java.util.List;

public class AddressPhoneResponse {
    private List<AddressDTO> addresses;
    private List<PhoneDTO> phones;

    public AddressPhoneResponse(List<AddressDTO> addresses, List<PhoneDTO> phones){
        this.addresses = addresses;
        this.phones = phones;
    }

    public AddressPhoneResponse(){
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }
}
