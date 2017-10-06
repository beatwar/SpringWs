//
// ���̃t�@�C���́AJavaTM Architecture for XML Binding(JAXB) Reference Implementation�Av2.2.7�ɂ���Đ�������܂��� 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>���Q�Ƃ��Ă������� 
// �\�[�X�E�X�L�[�}�̍ăR���p�C�����ɂ��̃t�@�C���̕ύX�͎����܂��B 
// ������: 2016.10.13 ���� 07:03:57 PM JST 
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
 * <p>anonymous complex type��Java�N���X�B
 * 
 * <p>���̃X�L�[�}�E�t���O�����g�́A���̃N���X���Ɋ܂܂��\�������R���e���c���w�肵�܂��B
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
     * username�v���p�e�B�̒l���擾���܂��B
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
     * username�v���p�e�B�̒l��ݒ肵�܂��B
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
     * playername�v���p�e�B�̒l���擾���܂��B
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
     * playername�v���p�e�B�̒l��ݒ肵�܂��B
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
     * code�v���p�e�B�̒l���擾���܂��B
     * 
     */
    public int getCode() {
        return code;
    }

    /**
     * code�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * left�v���p�e�B�̒l���擾���܂��B
     * 
     */
    public int getLeft() {
        return left;
    }

    /**
     * left�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     */
    public void setLeft(int value) {
        this.left = value;
    }

    /**
     * fire�v���p�e�B�̒l���擾���܂��B
     * 
     */
    public boolean isFire() {
        return fire;
    }

    /**
     * fire�v���p�e�B�̒l��ݒ肵�܂��B
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
