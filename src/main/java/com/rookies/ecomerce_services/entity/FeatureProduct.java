package com.rookies.ecomerce_services.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="feature_products")
public class FeatureProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="feature_product_id")
    private Long featureProductId;
    @Column(name = "feature_product_description")
    private String featureProductDescription;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    private Integer priority;
    @OneToOne()
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

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
