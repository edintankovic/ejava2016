/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1;

import java.util.Date;

/**
 *
 * @author edint_000
 */
public class Appt {

    private Integer appointmentId;
    private Long dateTime;
    private String personId;
    private String description;

    public Appt(Integer appointmentId, String description, Long dateTime, String personId) {
        this.appointmentId = appointmentId;
        this.description = description;
        this.dateTime = dateTime;
        this.personId = personId;
    }
    
    public String toString(){
        return String.format("{'appointmentId': %d, 'dateTime': %d, 'personId': '%s', 'description': '%s'}", appointmentId, dateTime, personId, description);
    }
}
