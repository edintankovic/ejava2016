package week03_ca;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import week03_ca.Appointment;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-27T04:39:37")
@StaticMetamodel(People.class)
public class People_ { 

    public static volatile SingularAttribute<People, String> name;
    public static volatile SingularAttribute<People, String> pid;
    public static volatile SingularAttribute<People, String> email;
    public static volatile CollectionAttribute<People, Appointment> appointmentCollection;

}