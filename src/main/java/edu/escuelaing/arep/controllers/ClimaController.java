package edu.escuelaing.arep.controllers;

import edu.escuelaing.arep.annotations.Component;
import edu.escuelaing.arep.annotations.RequestMapping;
import edu.escuelaing.arep.server.HttpServer;
import edu.escuelaing.arep.services.impl.HttpConnectionService;

import java.io.IOException;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 2/23/2022
 * @project OpenWeather
 */

@Component
public class ClimaController {

    @RequestMapping("notFound")
    public static String notFound() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Not found</title>\n"
                + "</head>"
                + "<body>"
                + "<div>"
                + "<h1>404 Error</h1>"
                + "</div>"
                + "<h2>Oops! This Page Could Not Be Found</h2>"
                + "<p>"
                + "Sorry but the page you are looking for does not exist or have been removed. name changed or is temporarily unavailable"
                + "</p>"
                + "</body>"
                + "</html>";
    }

    @RequestMapping("mainPage")
    public static String mainPage() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>main page</title>\n"
                + "</head>"
                + "<body>"
                + "<div>"
                + "<h1>Main Page</h1>"
                + "</div>"
                + "<h2>this is the main page</h2>"
                + "</body>"
                + "</html>";
    }

    @RequestMapping("json")
    public static String getResponse() {
        String responseContent = null;
        try {
            responseContent = "HTTP/1.1 200 OK \r\n"
                    + "Content-Type: " + HttpServer.typesMap.get("json") + "\r\n"
                    + "\r\n"
                    + new HttpConnectionService().startConnection();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return responseContent;
    }

}
