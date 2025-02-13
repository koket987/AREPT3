package co.edu.eci.arep;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpServerFrameTest {

    private static final String SERVER_URL = "http://localhost:35000";
    private static Thread serverThread;

    @BeforeAll
    static void setup() throws Exception {
        // Iniciar el servidor pasando la clase del controlador para registrar los endpoints REST
        serverThread = new Thread(() -> {
            try {
                HttpServer.main(new String[] {"co.edu.eci.arep.GreetingController"});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();
        // Esperar para que el servidor inicie correctamente
        Thread.sleep(3000);
    }

    @Test
    void testGreetingEndpoint() throws IOException {
        // Se asume que GreetingController registra el endpoint /greeting
        URL url = new URL(SERVER_URL + "/App/rests/greeting?name=Juan");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        assertEquals(200, conn.getResponseCode());
        // Se espera que el GreetingController retorne: {"message": "Hola Juan"}
        assertEquals("{\"message\": \"Hola Juan\"}", readResponse(conn));
    }

    @Test
    void testStaticFileServing() throws IOException {
        // Se asume que en el directorio STATIC_FILES_PATH existe un index.html
        URL url = new URL(SERVER_URL + "/App/index.html");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        assertEquals(200, conn.getResponseCode());
        String response = readResponse(conn);
        assertTrue(response.contains("<html>") || response.contains("<!DOCTYPE html>"));
    }

    private String readResponse(HttpURLConnection conn) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        return response.toString();
    }
}
