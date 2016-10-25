package ca1;

import ca1.Appointment;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-25T08:05:51")
@StaticMetamodel(People.class)
public class People_ { 

    public static volatile SingularAttribute<People, String> name;
    public static volatile SingularAttribute<People, String> pid;
    public static volatile SingularAttribute<People, String> email;
    public static volatile CollectionAttribute<People, Appointment> appointmentCollection;

}