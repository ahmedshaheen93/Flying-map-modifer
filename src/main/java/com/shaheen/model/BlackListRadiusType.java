//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.29 at 09:47:49 PM EET 
//


package com.shaheen.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BlackListRadiusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BlackListRadiusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GathererBlackListRadius" type="{}GathererBlackListRadiusType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BlackListRadiusType", propOrder = {
    "gathererBlackListRadius"
})
public class BlackListRadiusType {

    @XmlElement(name = "GathererBlackListRadius")
    protected List<GathererBlackListRadiusType> gathererBlackListRadius;

    /**
     * Gets the value of the gathererBlackListRadius property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gathererBlackListRadius property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGathererBlackListRadius().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GathererBlackListRadiusType }
     * 
     * 
     */
    public List<GathererBlackListRadiusType> getGathererBlackListRadius() {
        if (gathererBlackListRadius == null) {
            gathererBlackListRadius = new ArrayList<GathererBlackListRadiusType>();
        }
        return this.gathererBlackListRadius;
    }

}
