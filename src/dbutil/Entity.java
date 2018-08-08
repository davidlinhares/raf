package dbutil;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited 
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)  
public @interface Entity {

}
