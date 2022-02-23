package edu.escuelaing.arep.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 2/23/2022
 * @project OpenWeather
 * Interface that is gonna told us the different components of our framework
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
}
