/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversors;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
/**
 *
 * @author lyndemberg
 */
@Converter(autoApply = false)
public class LocalDateTimeToStamp implements AttributeConverter<LocalDateTime,Timestamp>{

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDate) {
        if(localDate == null){
            return null;
        }else{
            return Timestamp.valueOf(localDate);
        }
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp y) {
        if(y == null){
            return null;
        }else{
            return y.toLocalDateTime();
        }
    }

    
    
}
