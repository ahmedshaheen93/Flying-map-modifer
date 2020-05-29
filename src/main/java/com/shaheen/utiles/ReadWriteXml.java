package com.shaheen.utiles;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class ReadWriteXml {

    public synchronized Document readSourceDocument(File xmlFile, String schemaPath) throws
            ParserConfigurationException, IOException, SAXException {
        validateDocument(xmlFile, schemaPath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(xmlFile);
    }

    private void validateDocument(File xmlFile, String schemaPath) throws SAXException, IOException {
        StreamSource xsdStreamSource = new StreamSource(schemaPath);
        StreamSource xmlStreamSource = new StreamSource(xmlFile);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(xsdStreamSource);
        schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        schemaFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        // validators will also inherit of these properties  
        Validator validator = schema.newValidator();
        validator.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // Compliant  
        validator.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""); // Compliant  
        StringWriter writer = new StringWriter();
        validator.validate(xmlStreamSource, new StreamResult(writer));
    }

    public synchronized void writeSourceDocument(Document document, String path)
            throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));

        transformer.transform(domSource, streamResult);
    }

    public void printNode(NodeList childNodes) {
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            printNode(childNodes.item(i).getChildNodes());
            System.out.println("NodeName =" + childNodes.item(i).getNodeName());


            NamedNodeMap attributes = childNodes.item(i).getAttributes();
            if (attributes != null) {
                for (int j = 0; j < attributes.getLength(); j++) {
                    System.out.println("NodeName =" + attributes.item(j).getNodeName());
                    System.out.println("NodeValue =" + attributes.item(j).getNodeValue());
                }
            }
            System.out.println("TextContent =" + childNodes.item(i).getTextContent());
        }
    }
}
