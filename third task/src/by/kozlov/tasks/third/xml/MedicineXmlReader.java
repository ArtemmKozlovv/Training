package by.kozlov.tasks.third.xml;

import by.kozlov.tasks.third.domain.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class MedicineXmlReader {
    public List<Medicine> read(String fileName) throws FileNotFoundException {
        XMLStreamReader reader = null;
        try {
            List<Medicine> medicines = new ArrayList<Medicine>();
            Medicine medicine = null;
            XMLInputFactory factory = XMLInputFactory.newFactory();
            reader = factory.createXMLStreamReader(new FileInputStream(fileName));
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("medicine".equals(tagName)) {
                            medicine = new Medicine();
                            medicine.setIdentity(reader.getAttributeValue(null, "id"));
                        } else if ("name".equals(tagName)) {
                            medicine.setName(reader.getElementText());
                        } else if ("pharm".equals(tagName)) {
                            medicine.setPharm(reader.getElementText());
                        } else if ("group".equals(tagName)) {
                            medicine.setGroup(Group.valueOf(reader.getElementText()));
                        } else if ("analogFirst".equals(tagName)) {
                            medicine.setAnalogFirst(reader.getElementText());
                        } else if ("analogSecond".equals(tagName)) {
                            medicine.setAnalogSecond(reader.getElementText());
                        }else if ("versions".equals(tagName)) {
                            medicine.setVersions(Long.parseLong(reader.getElementText()));
                        } else if ("number".equals(tagName)) {
                            medicine.setNumber(Long.parseLong(reader.getElementText()));
                        } else if ("date_of_manufacture".equals(tagName)) {
                            medicine.setDate(reader.getElementText());
                        } else if ("number_of_tablets".equals(tagName)) {
                            medicine.setNumberOfTablets(Long.parseLong(reader.getElementText()));
                        } else if ("cost".equals(tagName)) {
                            medicine.setCost(Long.parseLong(reader.getElementText()));
                        } else if ("dosage".equals(tagName)) {
                            medicine.setDosage(reader.getElementText());
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("medicine".equals(tagName)) {
                            medicines.add(medicine);
                        }
                        break;
                    }
                }
            }
            return medicines;
        } catch (XMLStreamException e) {
            return null;
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
            }
        }
    }
}
