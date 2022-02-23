package edu.escuelaing.arep;

import edu.escuelaing.arep.annotations.RequestMapping;
import edu.escuelaing.arep.server.HttpServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 2/23/2022
 * @project OpenWeather
 */
public class NanoSpringBoot {

    //    private File pathToSearch;
    private Map<String, Method> services = new HashMap<>();
    private static NanoSpringBoot _instance = new NanoSpringBoot();

    /**
     * Method that return the only instance created of our framework
     *
     * @return App, Returns the unique instance created of our framework.
     */
    public static NanoSpringBoot getInstance() {
        if (_instance == null) _instance = new NanoSpringBoot();
        return _instance;
    }

    private String[] searchComponentList() {
        return new String[]{"edu.escuelaing.arep.controllers.ClimaController","edu.escuelaing.arep.services.impl.HttpConnectionService"};
    }

    /**
     * Method that save all the methods in our hashmap of a specific class
     *
     * @param componentName, className of the component with a @Component annotation
     */
    private void loadRequestMapping(String componentName) {
        try {
            Class c = Class.forName(componentName);

            //loading methods of the class
            Method[] declaredMethods = c.getDeclaredMethods();
            //Saving all the methods that have de RequesMapping annotation
            for (Method method : declaredMethods) {
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    //annotation = @RequestMapping("value")
                    RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                    services.put(annotation.value(), method);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that load all the components with the @componnet annotation
     */
    private void loadComponents() {
        String[] searchComponentList = searchComponentList();

        //Loading all the methods that have the @component
        if (searchComponentList != null) {
            for (String componentName : searchComponentList) {
                loadRequestMapping(componentName);
            }
        }
    }

    public Method getMethod(String serviceName){
        if (!services.containsKey(serviceName)) serviceName = "notFound";
        Method serviceMethod = services.get(serviceName);
        return serviceMethod;
    }

    /**
     * Method that invoke a specific method from a class
     *
     * @param serviceName, String that represents the name service stored on our Framework
     * @return String, That represents the execution of the method
     */
    public String invokeService(String serviceName) {
        // buscar que el servicio exista en mis metodos
        try {
            if (!services.containsKey(serviceName)) serviceName = "notFound";
            Method serviceMethod = services.get(serviceName);
            return (String) serviceMethod.invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "Service error";
    }

    /**
     * Run server method
     */
    public void startServer() {
        loadComponents();
        HttpServer server = new HttpServer();
        try {
            try {
                server.start();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

}
