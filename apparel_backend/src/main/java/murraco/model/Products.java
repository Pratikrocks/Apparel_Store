package murraco.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Products")
public class Products {
	  @Id
	  @GeneratedValue(generator = "uuid")
	  @GenericGenerator(name = "uuid", strategy = "uuid2")
	  private String id;

	  private String name;

	  private String type;

	  @Lob
	  private byte[] Image;
	  
	  private String Description;
	  
	  private float price;
	  
	  private char Genre;
	  
	  private float discount;
	  
	  private int m = 0;
	  
	  private int f = 0;
	  
	  private int d = 0;

	  @ManyToMany(mappedBy = "products")
	  private List<User> user;
	  
	  public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public Products() {
		  
	  }
	  
	public Products(String name, String type, byte[] image, String description, float price, char genre,
			float discount) {
		super();
		this.name = name;
		this.type = type;
		Image = image;
		Description = description;
		this.price = price;
		Genre = genre;
		this.discount = discount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImage() {
		return Image;
	}

	public void setImage(byte[] image) {
		Image = image;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public char getGenre() {
		return Genre;
	}

	public void setGenre(char genre) {
		Genre = genre;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}
	  
	  
	
}
