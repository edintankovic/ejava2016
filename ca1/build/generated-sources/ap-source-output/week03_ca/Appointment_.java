package week03_ca;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import week03_ca.People;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-27T04:39:37")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile SingularAttribute<Appointment, String> description;
    public static volatile SingularAttribute<Appointment, People> pid;
    public static volatile SingularAttribute<Appointment, Integer> apptId;
    public static volatile SingularAttribute<Appointment, Date> apptDate;

}