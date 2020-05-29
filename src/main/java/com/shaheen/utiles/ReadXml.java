package com.shaheen.utiles;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ReadXml {
    public synchronized JAXBElement readXmlFileRootElement(File xmlFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.shaheen.model");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (JAXBElement) unmarshaller.unmarshal(xmlFile);
    }
}
