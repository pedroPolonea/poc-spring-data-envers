package com.psd.poc.repository.spec;


import com.psd.poc.domain.entity.ProductEntity;
import com.psd.poc.domain.map.dto.ProductDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductSpecification {

    public static Specification<ProductEntity> getProductSpecification(final ProductDTO productDTO){
        return new Specification<ProductEntity>() {
            @Override
            public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
            }
        };
    }
}
