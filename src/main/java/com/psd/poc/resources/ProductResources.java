package com.psd.poc.resources;



import com.psd.poc.domain.map.dto.ProductDTO;
import com.psd.poc.service.ProductService;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = "products", produces = MediaType.APPLICATION_JSON_VALUE, headers ="version=V1" )
public class ProductResources {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "example")
    public ResponseEntity<?> findProductsExample(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.findProductsExample(productDTO), HttpStatus.OK);
    }

    @GetMapping(value = "specification")
    public ResponseEntity<?> findProductsSpecification(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.findProductsSpecification(productDTO), HttpStatus.OK);
    }

    @GetMapping(value = "specification-class")
    public ResponseEntity<?> findProductsSpecificationClass(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.findProductsSpecificationClass(productDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllProduts(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "active")
    public ResponseEntity<?> getAllActiveProduts(){
        return new ResponseEntity<>(productService.findAllActive(), HttpStatus.OK);
    }

    @GetMapping(value = "active-query")
    public ResponseEntity<?> getAllActiveQueryProduts(){
        return new ResponseEntity<>(productService.findAllActive(), HttpStatus.OK);
    }

    @GetMapping(value = "active-query-native")
    public ResponseEntity<?> getAllActiveQueryNativeProduts(){
        return new ResponseEntity<>(productService.findAllActive(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.save(productDTO), HttpStatus.OK);
    }

    @PutMapping
    public  ResponseEntity<?> update(@Valid @RequestBody ProductDTO productDTO) throws Exception {
        return new ResponseEntity<>(productService.update(Optional.ofNullable(productDTO)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
        productService.delete(Optional.ofNullable(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public  ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(productService.getId(id), HttpStatus.OK);
    }
}
