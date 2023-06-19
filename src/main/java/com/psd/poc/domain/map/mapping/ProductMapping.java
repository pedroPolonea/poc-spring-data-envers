package com.psd.poc.domain.map.mapping;



import com.psd.poc.domain.entity.ProductEntity;
import com.psd.poc.domain.map.dto.ProductDTO;
import com.psd.poc.domain.map.dto.ProductSummaryDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapping {

    ProductDTO productEntityToProductDTO(ProductEntity product);

    ProductEntity productDTOToProductEntity(ProductDTO dto);

    ProductSummaryDTO productEntityToProductSummaryDTO(ProductEntity products);

    List<ProductSummaryDTO> productEntityToProductSummaryDTO(List<ProductEntity> products);

}
