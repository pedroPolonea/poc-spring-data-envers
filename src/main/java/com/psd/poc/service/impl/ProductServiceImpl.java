package com.psd.poc.service.impl;

import com.psd.poc.domain.entity.ProductEntity;
import com.psd.poc.domain.map.dto.ProductDTO;
import com.psd.poc.domain.map.mapping.ProductMapping;
import com.psd.poc.repository.ProductRepository;
import com.psd.poc.repository.spec.ProductSpecification;
import com.psd.poc.service.ProductService;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapping productMapping;

    @Override
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductEntity> getId(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductEntity> findAllActive() {
        return productRepository.findByActive(true);
    }

    @Override
    public List<ProductEntity> findAllActiveQueryNative() {
        return productRepository.findByActiveQueryNative();
    }

    @Override
    public List<ProductEntity> findAllActiveQuery() {
        return productRepository.findByActiveQuery();
    }

    @Override
    public ProductDTO save(final ProductDTO productDTO) {
        log.info("M=ProductServiceImpl.save, productDTO={}", productDTO);

        ProductEntity productEntity = productMapping.productDTOToProductEntity(productDTO);
        productEntity = productRepository.save(productEntity);

        log.info("M=ProductServiceImpl.save, productEntity={}", productEntity);
        return productMapping.productEntityToProductDTO(productEntity);
    }

    @Override
    public ProductDTO update(final Optional<ProductDTO> productDTO) throws Exception {
        validateUpdate(productDTO);

        log.info("M=ProductServiceImpl.update, productDTO={}", productDTO.get());
        ProductEntity productEntity = productMapping.productDTOToProductEntity(productDTO.get());
        productEntity = productRepository.save(productEntity);
        log.info("M=ProductServiceImpl.update, productEntity={}", productEntity);

        return productMapping.productEntityToProductDTO(productEntity);
    }

    private void validateUpdate(final Optional<ProductDTO> productDTO) throws Exception {
        if (!productDTO.map(dto -> dto.getId()).isPresent()) {
            log.error("M=ProductServiceImpl.validateUpdate, I=O productDTO se encontra null ou sem id definido.");
            throw new Exception("Problema na criação do Statement");
        }
    }

    public void delete(final Optional<Long> id) throws NotFoundException {

        final Optional<ProductEntity> productEntity = id.map(idProduct -> productRepository.findById(idProduct))
                .orElseThrow(() -> new NotFoundException("Informe um produto valido."));

        if(productEntity.isPresent()){
            productRepository.delete(productEntity.get());
        } else {
            log.info("M=ProductServiceImpl.delete, I=Recurso não encontrado.");
            throw new NotFoundException("Recurso não encontrado.");
        }
    }

    @Override
    public List<ProductEntity> findProductsSpecificationClass(ProductDTO productDTO) {
        return productRepository.findAll(where(ProductSpecification.getProductSpecification(productDTO)));
    }

    public List<ProductEntity> findProductsExample(final ProductDTO productDTO){
        log.info("M=findProducts, productDTO={}", productDTO);

        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("amount", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<ProductEntity> example = Example.of(ProductEntity.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .amount(productDTO.getAmount())
                .build(), customExampleMatcher);

        return productRepository.findAll(example);
    }

    public List<ProductEntity> findProductsSpecification(final ProductDTO productDTO){
        log.info("M=findProducts, productDTO={}", productDTO);

        return productRepository.findAll(where(getEntityFieldsSpec(productDTO)));
    }

    private Specification<ProductEntity> getEntityFieldsSpec(final ProductDTO productDTO) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(productDTO.getName())) {
                Predicate tenantIdPredicate = criteriaBuilder.equal(root.get("name"), productDTO.getName());
                predicates.add(tenantIdPredicate);
            }

            if (Objects.nonNull(productDTO.getActive())) {
                Predicate tenantIdPredicate = criteriaBuilder.equal(root.get("active"), productDTO.getActive());
                predicates.add(tenantIdPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }

}
