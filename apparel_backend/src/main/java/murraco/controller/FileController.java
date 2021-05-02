package murraco.controller;


import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.models.Response;
import murraco.dto.UserResponseDTO;
import murraco.message.ResponseFile;
import murraco.message.ResponseMessage;
import murraco.message.ResponseProducts;
import murraco.model.FileDB;
import murraco.model.FileMessages;
import murraco.model.Products;
import murraco.model.User;
import murraco.repository.ProductRepository;
import murraco.repository.UserRepository;
import murraco.service.FileStorageService;
import murraco.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/services")
@Api(tags = "services")
public class FileController {

  @Autowired
  private FileStorageService storageService;

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private ModelMapper modelMapper;
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private ProductRepository productRepository;
  
  @PostMapping(value = "/addproduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ResponseMessage> addProduct(@RequestParam("image") MultipartFile image, 
		  											@RequestParam("description") String description,
		  											@RequestParam("genre") char genre,
		  											@RequestParam("discount") int discount,
		  											@RequestParam("price") float price
		  ) {
    String message = "";
    try {
      System.out.println("upload file");
      storageService.store(image, description, genre, discount, price);
//
      message = "Uploaded the file successfully: " + image.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + image.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }
  

  @GetMapping("/files")
  public ResponseEntity<List<ResponseProducts>> getListFiles() {
    List<ResponseProducts> files = storageService.getAllFiles().map(product -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/services/files/")
          .path(product.getId())
          .toUriString();

      return new ResponseProducts(
    	  product.getId(),
          product.getName(),
          fileDownloadUri,
          product.getType(),
          product.getPrice(), product.getDiscount(), product.getDescription(), product.getGenre(),product.getM(), product.getF(), product.getD());
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }

  @GetMapping("/files/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
    Products fileDB = storageService.getFile(id);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getImage());
  }
  
  @PostMapping("/cart/{productId}")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public ResponseEntity<ResponseMessage> addToCart(@PathVariable String productId, HttpServletRequest req) {
	  String message = "";
	  UserResponseDTO uDTO = modelMapper.map(userService.whoami(req), UserResponseDTO.class);
	  System.out.println(uDTO.getId());
	  User user = userRepository.findByUsername(uDTO.getUsername());
	  
	  System.out.println(user.getUsername() + " " + user.getEmail());
	  
	  Products product = productRepository.findById(productId).get();
//	  
	  System.out.println(product.getName() + " " + product.getDescription());
	  user.getProducts().add(product);
	  
	  userRepository.save(user);
	  
	  
	  try {
		  return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	  } catch(Exception e){
		  return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	  }
  }
  
  @GetMapping("/products/{genre}")
  @Transactional(readOnly = true)
  public ResponseEntity<List<ResponseProducts>> getProductsByGenre(@PathVariable char genre) {
	  Stream<Products> queries;
	  System.out.println(genre);
	  if(genre == 'M') {
		  System.out.println(genre);
		  queries = productRepository.fetchMProductsByGenre(genre);
	  } else {
		  System.out.println(genre);
		  queries = productRepository.fetchFProductsByGenre(genre);
	  }
	  
	 List<ResponseProducts> files = queries.map(product -> {
	      String fileDownloadUri = ServletUriComponentsBuilder
	          .fromCurrentContextPath()
	          .path("/services/files/")
	          .path(product.getId())
	          .toUriString();
	      char ch = product.getGenre();
	      return new ResponseProducts(
	    	  product.getId(),
	          product.getName(),
	          fileDownloadUri,
	          product.getType(),
	          product.getPrice(), product.getDiscount(), product.getDescription(), ch ,product.getM(), product.getF(), product.getD());
	    }).collect(Collectors.toList());
	  
	  return ResponseEntity.status(HttpStatus.OK).body(files);
	  
	  
  }
  
  @GetMapping("/mycart")
  public ResponseEntity<List<ResponseProducts>> getMyCart(HttpServletRequest req) {
	  List<ResponseProducts> files = new ArrayList<ResponseProducts>();
	  
	  UserResponseDTO uDTO = modelMapper.map(userService.whoami(req), UserResponseDTO.class);
	  System.out.println(uDTO.getId());
	  
	  User user = userRepository.findByUsername(uDTO.getUsername());
	  
	  List<Products> products = user.getProducts();
	  
	  
	  for(Products product : products) {
		  String fileDownloadUri = ServletUriComponentsBuilder
		          .fromCurrentContextPath()
		          .path("/services/files/")
		          .path(product.getId())
		          .toUriString();
		  ResponseProducts resPrd = new ResponseProducts(product.getId(),
		          product.getName(),
		          fileDownloadUri,
		          product.getType(),
		          product.getPrice(), product.getDiscount(), product.getDescription(), product.getGenre() ,product.getM(), product.getF(), product.getD());
		  
		  files.add(resPrd);
	  }
	  
	  
	  return ResponseEntity.status(HttpStatus.OK).body(files);
  }
  
  @GetMapping("/view/product/{productId}")
  public ResponseEntity<ResponseProducts> getProductById(@PathVariable String productId) {
	  Products product = productRepository.findById(productId).get();
	  String fileDownloadUri = ServletUriComponentsBuilder
	          .fromCurrentContextPath()
	          .path("/services/files/")
	          .path(product.getId())
	          .toUriString();
	  
	  return ResponseEntity.status(HttpStatus.OK).body(new ResponseProducts(
	    	  product.getId(),
	          product.getName(),
	          fileDownloadUri,
	          product.getType(),
	          product.getPrice(), product.getDiscount(), product.getDescription(), product.getGenre() ,product.getM(), product.getF(), product.getD()));
  }
  
}