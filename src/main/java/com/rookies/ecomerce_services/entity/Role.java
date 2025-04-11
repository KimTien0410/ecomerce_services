package com.rookies.ecomerce_services.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;
    @Column(name="role_name", nullable = false, unique = true)
    private String roleName;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="last_updated_on")
    private Date lastUpdatedOn;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> user;

    @PrePersist
    public void prePersist() {
        this.createdOn = new Date();
        this.lastUpdatedOn = new Date();
    }
    @PreUpdate
    public void preUpdate() {
        this.lastUpdatedOn = new Date();
    }
}
