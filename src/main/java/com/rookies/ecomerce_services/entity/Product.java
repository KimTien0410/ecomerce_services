package com.rookies.ecomerce_services.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;


import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long productId;
    @Column(name="product_name",unique = true,nullable = false)
    private String productName;
    @Column(name="product_slug",unique = true,nullable = false)
    private String productSlug;
    @Column(name="product_price")
    private double productPrice;
    @Column(name="stock_quantity",nullable = false, columnDefinition = "int default 0")
    private int stockQuantity;
    @Column(name="product_images")
    private String productImages;
    @Column(name="product_description",columnDefinition = "TEXT")
    private String productDescription;

    @Column(name="average_rating")
    private double averageRating;

    @Column(name="created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name="last_updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;
    @Column(name="is_deleted", columnDefinition = "BOOLEAN DEFAULT false", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="created_by", referencedColumnName = "admin_id")
    private Admin createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="last_updated_by", referencedColumnName = "admin_id")
    private Admin lastUpdatedBy;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    private List<Rating> ratings;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private FeatureProduct featureProduct;

    @PrePersist
    public void prePersist() {
        this.createdOn = new Date();
        this.lastUpdatedOn = new Date();
        this.isDeleted = false;
        this.averageRating= 0.0;
    }
    @PreUpdate
    public void preUpdate() {
        this.lastUpdatedOn = new Date();
    }
}
