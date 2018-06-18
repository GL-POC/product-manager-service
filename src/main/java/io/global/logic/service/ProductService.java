package io.global.logic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import io.global.logic.entity.Product;
import io.global.logic.exceptions.ProductException;
import io.global.logic.exceptions.ProductNotFoundException;
import io.global.logic.repository.ProductRepository;
import io.global.logic.request.RequestProduct;
import io.global.logic.response.ResponseProduct;

@Service
public class ProductService {

	@Autowired
    private ProductRepository productRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true, rollbackFor = ProductNotFoundException.class)
    public ResponseProduct getProductById(String productId){
    	Optional<Product> product = productRepository.getProductById(productId);
    	if (!product.isPresent()) {
    		throw new ProductNotFoundException(productId);
    	}
    	else {
    		Product response = product.get();
    		return new ResponseProduct(response.getId(), response.getProductName(), response.getLocationID());
    	}
    }
    
	public ResponseProduct save(RequestProduct request){
		Product product = new Product();
		product.setLocationID(request.getLocationID());
		product.setProductName(request.getProductName());
		Product response = productRepository.save(product);
		if (response == null) {
			throw new ProductException(request.getProductName());
		}
		else {
			return new ResponseProduct(response.getId(), response.getProductName(), response.getLocationID());
		}
    }

    public void deleteProduct(String productId){
        Optional<Product> productToDelete = productRepository.getProductById(productId);
        if (!productToDelete.isPresent()) {
            throw new ProductNotFoundException(productId);
        }
        else {
            Product response = productToDelete.get();
            productRepository.deleteById(response.getId());
        }
    }

    public ResponseProduct updateProduct(String productId, RequestProduct request){
       Optional<Product> productById= productRepository.getProductById(productId);
       if (!productById.isPresent()) {
    	   throw new ProductNotFoundException(productId);
       }
       else {
    	   Product product = productById.get();
    	   product.setProductName(request.getProductName());
    	   product.setLocationID(request.getLocationID());
           Product response = productRepository.save(product);
           if (response == null) {
    			throw new ProductException(product.getId());
    		}
    		else {
    			return new ResponseProduct(response.getId(), response.getProductName(), response.getLocationID());
    		}
       }
    }
}
