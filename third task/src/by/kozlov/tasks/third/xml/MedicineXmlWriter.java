package by.kozlov.tasks.third.xml;


import by.kozlov.tasks.third.domain.Medicine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class MedicineXmlWriter {
    public void write(List<Medicine> medicines, String fileName) throws FileNotFoundException, XMLStreamException {
        XMLStreamWriter writer = null;
        try {
            XMLOutputFactory factory = XMLOutputFactory.newFactory();
            writer = factory.createXMLStreamWriter(new FileOutputStream(fileName), "UTF-8");
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("medicines");
            writer.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            writer.writeAttribute("xmlns", "http://www.example.org/medicines");
            writer.writeAttribute("xsi:schemaLocation", "http://www.example.org/medicines medicines.xsd");
            for (Medicine medicine : medicines) {
                writer.writeStartElement("medicine");
                writer.writeAttribute("id", medicine.getIdentity());
                writer.writeStartElement("name");
                writer.writeCData(medicine.getName());
                writer.writeEndElement();
                writer.writeStartElement("pharm");
                writer.writeCharacters(medicine.getPharm());
                writer.writeEndElement();
                writer.writeStartElement("group");
                writer.writeCharacters(medicine.getGroup().toString());
                writer.writeEndElement();
                writer.writeStartElement("analogs");
                writer.writeStartElement("analogFirst");
                writer.writeCharacters(medicine.getAnalogFirst());
                writer.writeEndElement();
                writer.writeStartElement("analogSecond");
                writer.writeCharacters(medicine.getAnalogSecond());
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeStartElement("versions");
                writer.writeCharacters(medicine.getVersions().toString());
                writer.writeEndElement();
                writer.writeStartElement("certificate");
                writer.writeStartElement("number");
                writer.writeCharacters(medicine.getNumber().toString());
                writer.writeEndElement();
                writer.writeStartElement("date_of_manufacture");
                writer.writeCharacters(medicine.getDate());
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeStartElement("package");
                writer.writeStartElement("number_of_tablets");
                writer.writeCharacters(medicine.getNumberOfTablets().toString());
                writer.writeEndElement();
                writer.writeStartElement("cost");
                writer.writeCharacters(medicine.getCost().toString());
                writer.writeEndElement();
                writer.writeEndElement();
                writer.writeStartElement("dosage");
                writer.writeCharacters(medicine.getDosage());
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
