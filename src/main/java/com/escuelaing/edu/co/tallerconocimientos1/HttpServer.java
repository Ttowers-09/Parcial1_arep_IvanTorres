/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.escuelaing.edu.co.tallerconocimientos1;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

/**
 *
 * @author ivan.forero-t
 */
public class HttpServer {

    private static final String RESOURCE_PATH = "C:/Users/ivan.forero-t/Downloads/Parcial1_arep_IvanTorres/public/resources";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 36000.");
            System.exit(1);
        }

        Socket clientSocket = null;

        boolean bandera = true;
        while (bandera) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine, path = null;
            boolean firstLine = true;

            while ((inputLine = in.readLine()) != null) {
                if (firstLine) {
                    path = inputLine.split(" ")[1];
                    System.out.println("Path: " + path);
                    firstLine = false;
                }
                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) {
                    break;
                }
                String cleanPath = path;
                if (path.contains("=")) {
                    String key = path.split("=")[1];
                    String response = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/plain\r\n\r\n"
                            + key;
                    out.println(response);


                }if (path.equalsIgnoreCase("/favicon.ico")) {
                    String response = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: text/html\r\n\r\n"
                            + "<h1>Favicon request ignored</h1>";
                    out.println(response);

                }else {
                    serveStaticFile(cleanPath, clientSocket.getOutputStream(), out);
                }
                
            }

            //clientSocket.close();
        }
        
    }

     private static void serveStaticFile(String path, OutputStream rawOut, PrintWriter out) throws IOException {
        String resourcePath = path.equals("/") ? "/index.html" : path;

        Path filePath = Paths.get(RESOURCE_PATH + resourcePath);

        if (Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            if (mimeType == null) mimeType = "application/octet-stream";

            byte[] fileData = Files.readAllBytes(filePath);

            out.print("HTTP/1.1 200 OK\r\n");
            out.print("Content-Type: " + mimeType + "\r\n");
            out.print("Content-Length: " + fileData.length + "\r\n");
            out.print("\r\n");
            out.flush();

            rawOut.write(fileData);
            rawOut.flush();

        } else {
            String response = "HTTP/1.1 404 Not Found\r\n"
                    + "Content-Type: text/html\r\n\r\n"
                    + "<h1>404 Not Found</h1>";
            out.println(response);
        }
    }
}
