//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.29 at 09:47:49 PM EET 
//


package com.shaheen.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GathererProfileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GathererProfileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Vectors3" type="{}Vectors3Type"/>
 *         &lt;element name="Npc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BlackListRadius" type="{}BlackListRadiusType"/>
 *         &lt;element name="GatherEntry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NotLoop" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GathererProfileType", propOrder = {
    "vectors3",
    "npc",
    "blackListRadius",
    "gatherEntry",
    "notLoop"
})
public class GathererProfileType {

    @XmlElement(name = "Vectors3", required = true)
    protected Vectors3Type vectors3;
    @XmlElement(name = "Npc", required = true)
    protected String npc;
    @XmlElement(name = "BlackListRadius", required = true)
    protected BlackListRadiusType blackListRadius;
    @XmlElement(name = "GatherEntry", required = true)
    protected String gatherEntry;
    @XmlElement(name = "NotLoop", required = true)
    protected String notLoop;

    /**
     * Gets the value of the vectors3 property.
     * 
     * @return
     *     possible object is
     *     {@link Vectors3Type }
     *     
     */
    public Vectors3Type getVectors3() {
        return vectors3;
    }

    /**
     * Sets the value of the vectors3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vectors3Type }
     *     
     */
    public void setVectors3(Vectors3Type value) {
        this.vectors3 = value;
    }

    /**
     * Gets the value of the npc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNpc() {
        return npc;
    }

    /**
     * Sets the value of the npc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNpc(String value) {
        this.npc = value;
    }

    /**
     * Gets the value of the blackListRadius property.
     * 
     * @return
     *     possible object is
     *     {@link BlackListRadiusType }
     *     
     */
    public BlackListRadiusType getBlackListRadius() {
        return blackListRadius;
    }

    /**
     * Sets the value of the blackListRadius property.
     * 
     * @param value
     *     allowed object is
     *     {@link BlackListRadiusType }
     *     
     */
    public void setBlackListRadius(BlackListRadiusType value) {
        this.blackListRadius = value;
    }

    /**
     * Gets the value of the gatherEntry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatherEntry() {
        return gatherEntry;
    }

    /**
     * Sets the value of the gatherEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatherEntry(String value) {
        this.gatherEntry = value;
    }

    /**
     * Gets the value of the notLoop property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotLoop() {
        return notLoop;
    }

    /**
     * Sets the value of the notLoop property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotLoop(String value) {
        this.notLoop = value;
    }

}
