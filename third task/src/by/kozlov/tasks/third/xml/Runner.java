package by.kozlov.tasks.third.xml;

import by.kozlov.tasks.third.domain.Medicine;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.stream.XMLStreamException;

public class Runner {
    public static void main(String[] args) throws IOException, XMLStreamException {
        MedicineXmlValidator validator = new MedicineXmlValidator("medicines.xml");
        if (validator.validate()) {
            MedicineXmlReader reader = new MedicineXmlReader();
            List<Medicine> medicines = reader.read("medicines.xml");

            Collections.sort(medicines, new Comparator<Medicine>() {
                @Override
                public int compare(Medicine o1, Medicine o2) {
                    return o2.getCost().compareTo(o1.getCost());
                }
            });
            for (Medicine medicine : medicines) {
                System.out.println(medicine);
            }
            MedicineXmlWriter writer = new MedicineXmlWriter();
            writer.write(medicines, "medicines-new.xml");
            System.out.println("OK");
        } else {
            System.out.println(validator.getError());
        }
    }
}