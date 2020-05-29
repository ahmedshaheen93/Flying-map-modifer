package com.shaheen.utiles;

import com.shaheen.model.GathererProfileType;
import com.shaheen.model.ObjectFactory;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;

public class ReadWriteXmlJXB {
    public synchronized JAXBElement readXmlFileRootElement(File xmlFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.shaheen.model");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (JAXBElement) unmarshaller.unmarshal(xmlFile);
    }

    public synchronized void writeXmlFile(File xmlFile, GathererProfileType gathererProfileType) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance("com.shaheen.model");
        ObjectFactory factory = new ObjectFactory();
        JAXBElement jaxbElement = factory.createGathererProfile(gathererProfileType);
        Marshaller marsh = context.createMarshaller();
        marsh.setProperty
                (Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marsh.marshal
                (jaxbElement, xmlFile);
    }
}
