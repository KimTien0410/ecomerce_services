package com.rookies.ecomerce_services.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="avatar")
    private String avatar;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id", nullable = false, unique = true)
    @JsonIgnore
    private User user;


}
