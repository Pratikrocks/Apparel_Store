package murraco.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import murraco.model.Role;

public class UserDataDTO {
  
  @ApiModelProperty(position = 0)
  private String username;
  
  @ApiModelProperty(position = 1)
  private String email;
  
  @ApiModelProperty(position = 2)
  private String password;
  
  @ApiModelProperty(position = 3)
  private String gender;
  
  @ApiModelProperty(position = 4)
  List<Role> roles;
  
  @ApiModelProperty(position = 5)
  private int m_count = 0;
  
  @ApiModelProperty(position = 6)
  private int f_count = 0;
  
  @ApiModelProperty(position = 7)
  private int d_count = 0;
  
  
  public String getGender() {
	return gender;
}

public void setGender(String gender) {
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
