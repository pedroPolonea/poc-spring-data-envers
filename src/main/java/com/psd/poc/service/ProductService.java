package com.psd.poc.service;



import com.psd.poc.domain.entity.ProductEntity;
import com.psd.poc.domain.map.dto.ProductDTO;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductEntity> getAll();

    List<ProductEntity> findProductsExample(final ProductDTO productDTO);

    List<ProductEntity> findProductsSpecification(final ProductDTO productDTO);

    Optional<ProductEntity> getId(Long id);

    List<ProductEntity> findAllActive();

    List<ProductEntity> findAllActiveQueryNative();

    List<ProductEntity> findAllActiveQuery();

    ProductDTO save(ProductDTO productDTO);

    ProductDTO update(Optional<ProductDTO> productDTO) throws Exception;

    void delete(final Optional<Long> id) throws NotFoundException;

    List<ProductEntity> findProductsSpecificationClass(final ProductDTO productDTO);
}
