package tczr.projects.azstore.shared;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Helper {


  public static LocalDateTime toLocalDateTime(Object object){
    if(object == null) return null;
    if(object instanceof Timestamp)
     return ((Timestamp)object).toLocalDateTime();

    return null;
  };

}
