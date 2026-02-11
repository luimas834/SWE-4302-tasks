package com.myapp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * TASK 2, Part 2: Manual HTTP Client (Java-Based WSDL Client)
 *
 * This class:
 *   - Manually constructs SOAP XML request messages
 *   - Establishes HTTP connections using HttpURLConnection
 *   - Sends SOAP requests to the web service endpoint
 *   - Receives and parses SOAP XML responses
 *
 * NO automatic SOAP client generation tools (like wsimport) are used.
 */
public class SoapHttpClient {

    /**
     * Sends a SOAP XML request to the given endpoint using HttpURLConnection.
     *
     * @param endpoint   the URL of the SOAP service
     * @param soapAction the SOAPAction header value
     * @param envelope   the complete SOAP XML envelope
     * @return the raw XML response from the server
     */
    public static String sendRequest(String endpoint, String soapAction, String envelope) {
        try {
            // Step 1: Create an HTTP connection to the SOAP endpoint
            URL url = URI.create(endpoint).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Step 2: Configure HTTP headers for SOAP
            connection.setRequestMethod("POST");                                    // SOAP uses POST
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8"); // Sending XML
            connection.setRequestProperty("SOAPAction", soapAction);                // Which operation
            connection.setDoOutput(true);                                           // We're sending data

            // Step 3: Write the SOAP XML envelope to the request body
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] requestBytes = envelope.getBytes(StandardCharsets.UTF_8);
                outputStream.write(requestBytes, 0, requestBytes.length);
            }

            // Step 4: Read the SOAP XML response
            int responseCode = connection.getResponseCode();
            InputStream inputStream = (responseCode >= 200 && responseCode < 300)
                    ? connection.getInputStream()
                    : connection.getErrorStream();

            StringBuilder responseBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line).append("\n");
                }
            }

            connection.disconnect();
            return responseBuilder.toString();

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Wraps a SOAP body inside a standard SOAP envelope.
     *
     * SOAP messages MUST have this structure:
     *   <?xml version="1.0"?>
     *   <soap:Envelope xmlns:soap="...">
     *     <soap:Header/>
     *     <soap:Body>
     *       ... operation XML goes here ...
     *     </soap:Body>
     *   </soap:Envelope>
     */
    public static String wrapInEnvelope(String soapBody) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Header/>\n" +
                "  <soap:Body>\n" +
                "    " + soapBody + "\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
    }
}