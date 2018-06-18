package io.global.logic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.global.logic.config.PMSConfig;
import io.global.logic.request.RequestProduct;
import io.global.logic.response.ResponseProduct;
import io.global.logic.service.ProductService;


/**
 * Created by chethana.nk on 29/3/18.
 * updated by sushil kumar kushwaha on 16-06-2018
 * 
 */


@RestController
public class PMSController {
	
	@Autowired
    private ProductService productService;
	
	@Autowired
	private PMSConfig property;

	@GetMapping(value = "v1/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseProduct> getProductById(@PathVariable String productId){
    	System.out.println("hello");
    	System.out.println(property.getName() + " " + property.getUrl());
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(productId));
    }

    @PostMapping(value = "v1/product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseProduct> createProduct(@RequestBody RequestProduct product){
    	System.out.println(property.getName() + " " + property.getUrl());
    	return ResponseEntity.status(HttpStatus.OK).body(productService.save(product));
    }

    @DeleteMapping(value = "v1/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted successful" + productId);
    }

    @PutMapping(value = "v1/products/{productId}/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseProduct> updateProduct(@PathVariable String productId, @RequestBody RequestProduct requestProduct){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productId, requestProduct));
    }
}
