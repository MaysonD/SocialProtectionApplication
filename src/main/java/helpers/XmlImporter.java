package helpers;

import model.entity.DocumentEntity;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlImporter { // TODO filechoser

    public static void readXML() {
        try {

            File file = new File("D:\\file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(DocumentEntity.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            DocumentEntity document = (DocumentEntity) jaxbUnmarshaller.unmarshal(file);
            System.out.println(document.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
