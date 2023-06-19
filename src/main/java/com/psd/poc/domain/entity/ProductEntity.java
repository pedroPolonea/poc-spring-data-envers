package com.psd.poc.domain.entity;

import com.psd.poc.domain.entity.enums.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_PRODUCT", nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @Column(name ="UNITARY_VALUE", nullable = false)
    private BigDecimal unitaryValue;

    @Column(name = "ACQUISITION_VALUE", nullable = false)
    private BigDecimal acquisitionValue;

    @Column(name = "DAT_ACQUISITION")
    private LocalDateTime dateAcquisition;

    @Column(name = "DAT_CREATION")
    private LocalDateTime dateCreation;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_TYPE", nullable = false)
    private ProductType productType;

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
        this.active = true;
    }

    @PreUpdate
    public void preUpdate(){

    }
}
