//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.7によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2016.10.13 時間 07:03:57 PM JST 
//


package io.spring.guides.gs_producing_web_service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="playername" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="left" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fire" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="userlist" type="{http://spring.io/guides/gs-producing-web-service}valuelist"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "username",
    "playername",
    "code",
    "left",
    "fire",
    "userlist"
})
@XmlRootElement(name = "getUserparaResponse")
public class GetUserparaResponse {

    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String playername;
    protected int code;
    protected int left;
    protected boolean fire;
    @XmlList
    @XmlElement(required = true)
    protected List<String> userlist;

    /**
     * usernameプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * usernameプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * playernameプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlayername() {
        return playername;
    }

    /**
     * playernameプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlayername(String value) {
        this.playername = value;
    }

    /**
     * codeプロパティの値を取得します。
     * 
     */
    public int getCode() {
        return code;
    }

    /**
     * codeプロパティの値を設定します。
     * 
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * leftプロパティの値を取得します。
     * 
     */
    public int getLeft() {
        return left;
    }

    /**
     * leftプロパティの値を設定します。
     * 
     */
    public void setLeft(int value) {
        this.left = value;
    }

    /**
     * fireプロパティの値を取得します。
     * 
     */
    public boolean isFire() {
        return fire;
    }

    /**
     * fireプロパティの値を設定します。
     * 
     */
    public void setFire(boolean value) {
        this.fire = value;
    }

    /**
     * Gets the value of the userlist property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userlist property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserlist().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserlist() {
        if (userlist == null) {
            userlist = new ArrayList<String>();
        }
        return this.userlist;
    }

}
