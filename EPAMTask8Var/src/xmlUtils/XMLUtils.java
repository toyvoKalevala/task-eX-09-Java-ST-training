package xmlUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import data.Accessory;
import data.Aquarium;
import data.Inmate;
import data.InmatesTypes;
import data.NonPredatoryFish;
import data.Shrimp;
import data.Snail;

public class XMLUtils {

	public static Document importXML(String path) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(path));

		return document;
	}

	public static ArrayList<Inmate> parseInmatesXML(Document document)
			throws ParserConfigurationException, SAXException, IOException {

		ArrayList<Inmate> inmates = new ArrayList<>();

		for (InmatesTypes inmateTypeEnum : InmatesTypes.values()) {

			NodeList inmatesElements = document.getDocumentElement().getElementsByTagName(inmateTypeEnum.toString());

			for (int i = 0; i < inmatesElements.getLength(); i++) {

				Node inmate = inmatesElements.item(i);
				NamedNodeMap attributes = inmate.getAttributes();
				String inmateType = attributes.getNamedItem("type").getNodeValue();

				if (inmateType.equals("Non predatory fish")) {
					inmates.add(new NonPredatoryFish(Long.parseLong(attributes.getNamedItem("id").getNodeValue()),
							attributes.getNamedItem("type").getNodeValue(),
							attributes.getNamedItem("name").getNodeValue(),
							Double.parseDouble(attributes.getNamedItem("price").getNodeValue()),
							Integer.parseInt(attributes.getNamedItem("count").getNodeValue())));
				} else if (inmateType.equals("Shrimp")) {
					inmates.add(new Shrimp(Long.parseLong(attributes.getNamedItem("id").getNodeValue()),
							attributes.getNamedItem("type").getNodeValue(),
							attributes.getNamedItem("name").getNodeValue(),
							Double.parseDouble(attributes.getNamedItem("price").getNodeValue()),
							Integer.parseInt(attributes.getNamedItem("count").getNodeValue())));
				} else if (inmateType.equals("Snail")) {
					inmates.add(new Snail(Long.parseLong(attributes.getNamedItem("id").getNodeValue()),
							attributes.getNamedItem("type").getNodeValue(),
							attributes.getNamedItem("name").getNodeValue(),
							Double.parseDouble(attributes.getNamedItem("price").getNodeValue()),
							Integer.parseInt(attributes.getNamedItem("count").getNodeValue())));
				}
			}
		}
		return inmates;
	}

	public static ArrayList<Accessory> parseAccessoriesXML(Document document)
			throws ParserConfigurationException, SAXException, IOException {

		NodeList accessoriesElements = document.getDocumentElement().getElementsByTagName("Accessory");
		ArrayList<Accessory> accessories = new ArrayList<>();

		for (int i = 0; i < accessoriesElements.getLength(); i++) {
			Node accessory = accessoriesElements.item(i);

			NamedNodeMap attributes = accessory.getAttributes();

			accessories.add(new Accessory(Long.parseLong(attributes.getNamedItem("id").getNodeValue()),
					attributes.getNamedItem("name").getNodeValue(),
					Double.parseDouble(attributes.getNamedItem("price").getNodeValue())));
		}
		return accessories;
	}

	public static void exportXML(Aquarium aquarium, String filePath) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Aquarium.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(aquarium, new File(filePath));

	}

}
