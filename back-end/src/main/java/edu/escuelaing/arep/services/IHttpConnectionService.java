package edu.escuelaing.arep.services;

import java.io.IOException;
import org.json.JSONObject;
/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 2/23/2022
 * @project OpenWeather
 * Interface that's gonna make the connection to the OMW API
 */
public interface IHttpConnectionService {
    /**
     *Service that make a connection HTTP to our API
     * @throws IOException
     */
    public JSONObject startConnection() throws IOException;
}
