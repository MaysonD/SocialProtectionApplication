package helpers;

import database.DatabaseWorker;
import model.entity.DocumentEntity;
import model.entity.VeteranEntity;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class XmlExporter { // TODO filechoser

    public static void generateXML() {
        List<VeteranEntity> veterans = DatabaseWorker.executeSelectVeteransQuery("SELECT * FROM veterans");
        VeteranEntity vetaran = veterans.get(0);
        
        try {

            File file = new File("D:\\file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(DocumentEntity.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(vetaran, file);
            jaxbMarshaller.marshal(vetaran, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }



}
