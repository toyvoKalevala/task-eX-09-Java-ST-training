package data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id", "type", "name", "price", "count" })
public abstract class Inmate {

	private long id;
	private String type;
	private String name;
	private double price;
	private int count;

	public Inmate(long id, String type, String name, double price, int count) {
		this.id = id;
		this.type = type;
		this.name = name;

		this.price = price;
		this.count = count;
	}

	@XmlAttribute
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
