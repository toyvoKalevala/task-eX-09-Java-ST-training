package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import xmlUtils.XMLUtils;

@XmlRootElement(name = "Aquarium")
public class Aquarium {

	public Aquarium() throws ParserConfigurationException, SAXException, IOException {
		Document listOfAccessories = XMLUtils.importXML("resources/in/accessories.xml");
		List<Accessory> accessories = XMLUtils.parseAccessoriesXML(listOfAccessories);
		this.addAccessories(accessories);
	}

	@XmlElement(name = "inmate", type = Inmate.class)
	private List<Inmate> inmates = new ArrayList<>();
	@XmlElement(name = "accessory", type = Accessory.class)
	private List<Accessory> accessories = new ArrayList<Accessory>();
	double inmatesPrice, accesoriesPrice;
	@XmlAttribute
	double aquariumPrice = 100;

	public boolean addInmates(List<Inmate> inmates) {
		this.inmates = inmates;
		return true;
	}

	public boolean addAccessories(List<Accessory> accessories) {
		this.accessories = accessories;
		return true;
	}

	public double countPrice() {
		for (Inmate inmate : inmates) {
			inmatesPrice += inmate.getPrice() * inmate.getCount();
		}
		for (Accessory accessory : accessories) {
			accesoriesPrice += accessory.getPrice();
		}

		return this.aquariumPrice += inmatesPrice + accesoriesPrice;
	}

}
