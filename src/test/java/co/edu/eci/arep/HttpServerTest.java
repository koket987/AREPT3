package co.edu.eci.arep;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpServerTest {

    private static final String SERVER_URL = "http://localhost:8080";

    @BeforeAll
    static void setup() throws Exception {
        // Iniciar el servidor en un hilo separado
        new Thread(() -> {
            try {
                HttpServer.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        // Esperar para que el servidor se inicie
        Thread.sleep(2000);
    }

    @Test
    void testHelloEndpoint() throws IOException {
        URL url = new URL(SERVER_URL + "/App/hello?name=Juan");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        assertEquals(200, conn.getResponseCode());
        assertEquals("Hello Juan", readResponse(conn));
    }

    @Test
    void testPiEndpoint() throws IOException {
        URL url = new URL(SERVER_URL + "/App/pi");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        assertEquals(200, conn.getResponseCode());
        assertEquals(String.valueOf(Math.PI), readResponse(conn));
    }

    @Test
    void testStaticFileServing() throws IOException {
        // Se asume que en el directorio de archivos estáticos existe un index.html con contenido HTML
        URL url = new URL(SERVER_URL + "/App/index.html");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        assertEquals(200, conn.getResponseCode());
        String response = readResponse(conn);
        assertTrue(response.contains("<html>") || response.contains("<!DOCTYPE html>"));
    }

    private String readResponse(HttpURLConnection conn) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        return response.toString();
    }
}