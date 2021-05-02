package murraco.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name="User")
public class User {
  @ApiModelProperty(position = 0)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ApiModelProperty(position = 1)
  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  @Column(unique = true, nullable = false)
  private String username;

  @ApiModelProperty(position = 2)
  @Column(unique = true, nullable = false)
  private String email;

  
  @ApiModelProperty(position = 3)
  @Size(min = 8, message = "Minimum password length: 8 characters")
  private String password;

  @ApiModelProperty(position = 4)
  private char gender;
  
  @ApiModelProperty(position = 5)
  private int m_count = 0;
  
  @ApiModelProperty(position = 6)
  private int f_count = 0;
  
  @ApiModelProperty(position = 7)
  private int d_count = 0;
  
  @ManyToMany
  @JoinTable(
		  name = "products_user", 
		  joinColumns = @JoinColumn(name = "user_id"), 
		  inverseJoinColumns = @JoinColumn(name = "product_id"))
  private List<Products> products;
  
  public List<Products> getProducts() {
	return products;
}

public void setProducts(List<Products> products) {
	this.products = products;
}

@ElementCollection(fetch = FetchType.EAGER)
  List<Role> roles;
  
  
  public char getGender() {
	return gender;
}

public void setGender(char gender) {
	this.gender = gender;
}

public int getM_count() {
	return m_count;
}

public void setM_count(int m_count) {
	this.m_count = m_count;
}

public int getF_count() {
	return f_count;
}

public void setF_count(int f_count) {
	this.f_count = f_count;
}

public int getD_count() {
	return d_count;
}

public void setD_count(int d_count) {
	this.d_count = d_count;
}

  
  

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

}
