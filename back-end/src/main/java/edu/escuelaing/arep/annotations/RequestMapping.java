package edu.escuelaing.arep.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 2/23/2022
 * @project OpenWeather
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
    public String value();
}
