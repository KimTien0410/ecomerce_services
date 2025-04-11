package com.rookies.ecomerce_services.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_methods")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private Long paymentMethodId;
    @Column(name = "payment_method_name")
    private String paymentMethodName;
    @Column(name = "payment_method_icon")
    private String paymentMethodIcon;


}
