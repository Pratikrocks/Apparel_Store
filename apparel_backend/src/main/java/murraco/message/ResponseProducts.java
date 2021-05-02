package murraco.message;

public class ResponseProducts {
	private String id;
	private String name;
	private String url;
	private String type;
	private float price;
	private float discount;
	private String description;
	private char genre;
	public char getGenre() {
		return genre;
	}
	public void setGenre(char genre) {
		this.genre = genre;
	}
	private int m;
	private int f;
	private int d;
	public ResponseProducts(String id, String name, String url, String type, float price, float discount,
			String description, char genre,int m, int f, int d) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.type = type;
		this.price = price;
		this.discount = discount;
		this.description = description;
		this.genre = genre;
		this.m = m;
		this.f = f;
		this.d = d;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
