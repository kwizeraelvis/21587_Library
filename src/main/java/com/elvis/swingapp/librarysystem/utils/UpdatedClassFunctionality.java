package com.elvis.swingapp.librarysystem.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author elvis
 */
@Retention(RetentionPolicy.CLASS)
public @interface UpdatedClassFunctionality {
    
    
    String since() default "";
     
}
