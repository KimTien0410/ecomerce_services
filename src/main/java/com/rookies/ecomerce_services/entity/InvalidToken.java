package com.rookies.ecomerce_services.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invalid_tokens")
public class InvalidToken {
    @Id
    private String id;
    private Date expiredAt;
}
