package murraco.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import murraco.model.Products;

public interface ProductRepository extends JpaRepository<Products, String> {
	
	@Query(value = "SELECT * FROM products WHERE genre = 'M'", nativeQuery = true)
	Stream<Products> fetchMProductsByGenre(char genre);

	@Query(value = "SELECT * FROM products WHERE genre = 'F'", nativeQuery = true)
	Stream<Products> fetchFProductsByGenre(char genre);

}
