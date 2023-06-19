package com.psd.poc.repository;


import com.psd.poc.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity>, RevisionRepository<ProductEntity, Long, Long> {

    List<ProductEntity> findByActive(Boolean active);

    @Query("FROM products p WHERE p.active = true")
    List<ProductEntity> findByActiveQuery();

    @Query(value = "SELECT * FROM products p WHERE p.active = 1", nativeQuery = true)
    List<ProductEntity> findByActiveQueryNative();
}
