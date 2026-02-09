package com.alvaro.springcloud.msvc.catalog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "social_status")
public class SocialStatus {
    @Id
    @Column(name = "solcial_status_id")
    private String socialStatusId;
    @Column(name = "social_status")
    private String socialStatus;

    public String getSocialStatusId() {
        return socialStatusId;
    }

    public void setSocialStatusId(String socialStatusId) {
        this.socialStatusId = socialStatusId;
    }

    public String getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(String socialStatus) {
        this.socialStatus = socialStatus;
    }
}
