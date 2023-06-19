package com.psd.poc.domain.map.dto;


import com.psd.poc.domain.entity.enums.ProductType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;

    @NotNull
    private String name;

    private String description;

    private Integer amount;

    private BigDecimal unitaryValue;

    private BigDecimal acquisitionValue;

    private LocalDateTime dateAcquisition;

    private LocalDateTime dateCreation;

    private Boolean active;

    private ProductType productType;
}
