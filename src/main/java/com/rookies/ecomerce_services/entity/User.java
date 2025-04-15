package com.rookies.ecomerce_services.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name="password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name="is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Column(name="created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name="last_updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Customer customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Admin admin;


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
