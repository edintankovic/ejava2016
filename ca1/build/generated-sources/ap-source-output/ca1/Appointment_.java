package ca1;

import ca1.People;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-25T08:05:51")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile SingularAttribute<Appointment, String> description;
    public static volatile SingularAttribute<Appointment, People> pid;
    public static volatile SingularAttribute<Appointment, Integer> apptId;
    public static volatile SingularAttribute<Appointment, Date> apptDate;

}