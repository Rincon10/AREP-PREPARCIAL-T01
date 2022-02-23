package edu.escuelaing.arep.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static edu.escuelaing.arep.utils.Constants.TYPES;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 2/23/2022
 * @project OpenWeather
 */
public class HttpServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private URI resourceURI;
    public static OutputStream outputStream;

    public final static Map<String, String> typesMap = new HashMap<String, String>();

    public HttpServer() {
        setTypes();
    }

    /**
     * Method that prepares all possible types of a file
     */
    private void setTypes() {
        for (String[] type : TYPES) {
            typesMap.put(type[0], type[1]);
        }
    }

    /**
     * Method that return a default html page
     *
     * @return String, that represents the html page
     */
    private String getDefaultHTML() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Default Page</title>\n"
                + "</head>"
                + "<body>"
                + "<h1>Default Page</h1>"
                + "</body>"
                + "</html>";
    }
}
