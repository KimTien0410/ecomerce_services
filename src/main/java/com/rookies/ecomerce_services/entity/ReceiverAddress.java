package com.rookies.ecomerce_services.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="receiver_addresses")
public class ReceiverAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="receiver_address_id")
    private Long receiverAddressId;
    @Column(name="receiver_name", nullable = false)
    private String receiverName;
    @Column(name="receiver_phone", nullable = false)
    private String receiverPhone;
    @Column(name="receiver_street",nullable = false)
    private String receiverStreet;
    @Column(name="receiver_ward",nullable = false)
    private String ward;
    @Column(name="receiver_district",nullable = false)
    private String district;
    @Column(name="receiver_city",nullable = false)
    private String city;
    @Column(name="receiver_address")
    private String receiverAddress;
    @Column(name="is_default",columnDefinition = "boolean default false")
    private Boolean isDefault;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;



}
