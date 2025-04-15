package com.rookies.ecomerce_services.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rookies.ecomerce_services.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_address_id", nullable = false)
    private ReceiverAddress receiverAddress;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payment_method_id",nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "created_on", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "last_updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by", referencedColumnName = "admin_id")
    private Admin createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="last_updated_by", referencedColumnName = "admin_id")
    private Admin lastUpdatedBy;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

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