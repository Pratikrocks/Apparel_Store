package murraco.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import murraco.model.Products;
import murraco.repository.ProductRepository;

import org.springframework.util.StringUtils;

@Service
public class FileStorageService {
	  
	  @Autowired
	  private ProductRepository productRepository;

	  public Products store(MultipartFile file, String description, char genre, int discount, float price) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    Products product = new Products(fileName, file.getContentType(), file.getBytes(), description, price, genre, discount);

	    return productRepository.save(product);
	  }

	  public Products getFile(String id) {
	    return productRepository.findById(id).get();
	  }
	  
	  public Stream<Products> getAllFiles() {
	    return productRepository.findAll().stream();
	  }
}
