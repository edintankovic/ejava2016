/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca3;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author edint_000
 */


@XmlRootElement
public class ePodRecord implements Serializable{
    private static final long serialVersionUID = 1L;
    private String teamId;
    private Integer podId;
    private String name;
    private String address;
    private String phone;

    public ePodRecord(String teamId, Integer podId, String name, String address, String phone) {
        this.teamId = teamId;
        this.podId = podId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    /**
     * @return the teamId
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * @param teamId the teamId to set
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    /**
     * @return the podId
     */
    public Integer getPodId() {
        return podId;
    }

    /**
     * @param podId the podId to set
     */
    public void setPodId(Integer podId) {
        this.podId = podId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String toString(){
        
        return String.format("{teamId = '%s', podId = '%d', name = '%s', address = '%s', phone = '%s'}", teamId, podId, name, address, phone);

    }
    

}
