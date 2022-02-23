package edu.escuelaing.arep.services.impl;

import edu.escuelaing.arep.services.IHttpConnectionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
import net.minidev.json.parser.ParseException;


/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 2/23/2022
 * @project OpenWeather
 */
public class HttpConnectionService implements IHttpConnectionService {
    private static int lon;
    private static int lat;
    private HttpURLConnection con;
    private String endPoint;
    private URL url;
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_KEY = "9228d66e04138dbdcf322761ec31e53d";
    private static final String PATH = "https://api.openweathermap.org/data/2.5/weather?appid=" + API_KEY;

    /**
     * Method that add the default queries of the API
     *
     * @return String, return all the queries for the API
     */
    private String addQueries() {
        return "&lat=" + this.lat + "&lon=" + this.lon;
    }

    /**
     * Class that gonna make the HTTP connection to the API, using a default lat and lon
     */
    public HttpConnectionService() {
        this(0, 0);
    }

    /**
     * Class that gonna make the HTTP connection to the API, using a specific lat and lon
     *
     * @param lat, int latitude
     * @param lon, int longitude
     */
    public HttpConnectionService(int lat, int lon) {
        this.lat = lat;
        this.lon = lon;
        try {
            this.url = new URL(PATH + addQueries());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that return the JsonObject of our petition
     * @return JSONObject, response of the petition
     * @throws IOException
     */
    private JSONObject getResponse() throws IOException {
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("*****************************************************************************");
        System.out.println("GET Response Code :: " + responseCode + "on petition" + url.getPath());

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            System.out.println("starting GET petition on " + url.getPath());
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            JSONObject json = new JSONObject(response.toString());
            return json;
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE from url" + url.getPath() + "\n");
        System.out.println("*****************************************************************************");
        return null;

    }

    /**
     * Method that start the connection between the url and the API
     * @return JSONObject, response of the petition
     * @throws IOException
     */
    @Override
    public JSONObject startConnection() throws IOException {
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        return getResponse();
    }

    public static void main(String[] args) {
        try {
            new HttpConnectionService(0, 0).startConnection();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
