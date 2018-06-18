package io.global.logic.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.global.logic.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	Optional<Product> getProductById(String productId);

	//Optional<Product> findByProductId(String productId);
}
