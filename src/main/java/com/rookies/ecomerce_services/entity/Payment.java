package com.rookies.ecomerce_services.entity;

import com.rookies.ecomerce_services.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", referencedColumnName = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name="payment_status", referencedColumnName = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;

    @Column(name= "amount", nullable = false)
    private Double amount;

    @Column(name="payment_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentAt;

    @Column(name = "created_on", nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "last_updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;


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
