package logic;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import data.Aquarium;
import data.Inmate;
import xmlUtils.XMLUtils;

public class Main {

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, JAXBException {

		Document listOfInmates = XMLUtils.importXML("resources/in/inmates.xml");

		List<Inmate> inmates = XMLUtils.parseInmatesXML(listOfInmates);

		Aquarium nonPredatoryFishesAquarium = new Aquarium();

		nonPredatoryFishesAquarium.addInmates(inmates);

		nonPredatoryFishesAquarium.countPrice();

		XMLUtils.exportXML(nonPredatoryFishesAquarium, "resources/out/aquariumPrice.xml");

	}

}
