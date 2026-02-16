package com.alvaro.springcloud.msvc.persons.DTO.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class PersonCrudDTO {

    @NotNull
    private PersonKind kind;

    @NotBlank
    private String idPersonType;

    @NotNull
    private Boolean isClient;
    @NotNull
    private Boolean isSupplier;
    @NotNull
    private Boolean isEmployee;

    @NotNull
    private Boolean enabled;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String idDocumentType;

    @NotBlank
    private String documentType;

    @NotBlank
    private String name;

    @Valid
    private NaturalData personNaturalData;

    @Valid
    private LegalData personLegalData;




    public static class NaturalData {
        @NotBlank private String firstName;
        @NotBlank private String lastName;
        @NotBlank private String idGener;
        @NotBlank private String middleName;
        @NotBlank private String secondLastName;
        @NotBlank private String idSocialStatus;

        private String thirdName;
        private String marriedLastName;

        public String getFirstName() {
            return firstName;
        }

        public void setfirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getIdGener() {
            return idGener;
        }

        public void setIdGener(String idGener) {
            this.idGener = idGener;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getSecondLastName() {
            return secondLastName;
        }

        public void setSecondLastName(String secondLastName) {
            this.secondLastName = secondLastName;
        }

        public String getIdSocialStatus() {
            return idSocialStatus;
        }

        public void setIdSocialStatus(String idSocialStatus) {
            this.idSocialStatus = idSocialStatus;
        }

        public String getThirdName() {
            return thirdName;
        }

        public void setThirdName(String thirdName) {
            this.thirdName = thirdName;
        }

        public String getMarriedLastName() {
            return marriedLastName;
        }

        public void setMarriedLastName(String marriedLastName) {
            this.marriedLastName = marriedLastName;
        }


    }




    public static class LegalData {
        @NotBlank private String legalName;
        @NotBlank private String comercialName;
        @NotBlank private String idDocumentTypeRepresentative;
        @NotBlank private String representativeLegalDocument;

        public void setIdDocumentTypeRepresentative(String idDocumentTypeRepresentative){
            this.idDocumentTypeRepresentative = idDocumentTypeRepresentative;
        }

        public String getIdDocumentTypeRepresentative(){
            return this.idDocumentTypeRepresentative;
        }

        public String getLegalName() {
            return legalName;
        }

        public void setLegalName(String legalName) {
            this.legalName = legalName;
        }

        public String getComercialName() {
            return comercialName;
        }

        public void setComercialName(String comercialName) {
            this.comercialName = comercialName;
        }

        public String getRepresentativeLegalDocument() {
            return representativeLegalDocument;
        }

        public void setRepresentativeLegalDocument(String representativeLegalDocument) {
            this.representativeLegalDocument = representativeLegalDocument;
        }
    }


    public @NotNull PersonKind getKind() {
        return kind;
    }

    public void setKind(@NotNull PersonKind kind) {
        this.kind = kind;
    }

    public @NotBlank String getIdPersonType() {
        return idPersonType;
    }

    public void setIdPersonType(@NotBlank String idPersonType) {
        this.idPersonType = idPersonType;
    }

    public Boolean getClient() {
        return isClient;
    }

    public void setClient(Boolean client) {
        isClient = client;
    }

    public Boolean getSupplier() {
        return isSupplier;
    }

    public void setSupplier(Boolean supplier) {
        isSupplier = supplier;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }

    public void setEmployee(Boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public @NotNull Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(@NotNull Boolean enabled) {
        this.enabled = enabled;
    }

    public @Email @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank String email) {
        this.email = email;
    }

    public String getIdDocumentType() {
        return idDocumentType;
    }

    public void setIdDocumentType(String idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public @Valid NaturalData getPersonNaturalData() {
        return personNaturalData;
    }

    public void setPersonNaturalData(@Valid NaturalData personNaturalData) {
        this.personNaturalData = personNaturalData;
    }

    public @Valid LegalData getPersonLegalData() {
        return personLegalData;
    }

    public void setPersonLegalData(@Valid LegalData personLegalData) {
        this.personLegalData = personLegalData;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }
}