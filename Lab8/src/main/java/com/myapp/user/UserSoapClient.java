package com.myapp.user;

import com.myapp.SoapHttpClient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TASK 2: SOAP Client that manually constructs XML for all 4 operations.
 *
 * Each method:
 *   1. Builds a SOAP XML body by hand (no auto-generation!)
 *   2. Wraps it in a SOAP envelope
 *   3. Sends it via SoapHttpClient (which uses HttpURLConnection)
 *   4. Parses the XML response to extract the result
 */
public class UserSoapClient {

    private static final String ENDPOINT = "http://localhost:9090/userservice";
    private static final String NS = "http://user.myapp.com/";

    // ===== OPERATION 1: CREATE USER =====
    public static String createUser(String username, int id, String password) {
        // Manually construct the SOAP XML body
        String body = "<ns:createUser xmlns:ns=\"" + NS + "\">\n" +
                "  <username>" + username + "</username>\n" +
                "  <id>" + id + "</id>\n" +
                "  <password>" + password + "</password>\n" +
                "</ns:createUser>";

        String envelope = SoapHttpClient.wrapInEnvelope(body);
        String response = SoapHttpClient.sendRequest(ENDPOINT, NS + "createUser", envelope);
        return extractReturnValue(response);
    }

    // ===== OPERATION 2: RETRIEVE USER =====
    public static String retrieveUser(int id) {
        String body = "<ns:retrieveUser xmlns:ns=\"" + NS + "\">\n" +
                "  <id>" + id + "</id>\n" +
                "</ns:retrieveUser>";

        String envelope = SoapHttpClient.wrapInEnvelope(body);
        String response = SoapHttpClient.sendRequest(ENDPOINT, NS + "retrieveUser", envelope);
        return extractReturnValue(response);
    }

    // ===== OPERATION 3: UPDATE USER =====
    public static String updateUser(int id, String username, String password) {
        String body = "<ns:updateUser xmlns:ns=\"" + NS + "\">\n" +
                "  <id>" + id + "</id>\n" +
                "  <username>" + username + "</username>\n" +
                "  <password>" + password + "</password>\n" +
                "</ns:updateUser>";

        String envelope = SoapHttpClient.wrapInEnvelope(body);
        String response = SoapHttpClient.sendRequest(ENDPOINT, NS + "updateUser", envelope);
        return extractReturnValue(response);
    }

    // ===== OPERATION 4: DELETE USER =====
    public static String deleteUser(int id) {
        String body = "<ns:deleteUser xmlns:ns=\"" + NS + "\">\n" +
                "  <id>" + id + "</id>\n" +
                "</ns:deleteUser>";

        String envelope = SoapHttpClient.wrapInEnvelope(body);
        String response = SoapHttpClient.sendRequest(ENDPOINT, NS + "deleteUser", envelope);
        return extractReturnValue(response);
    }

    /**
     * Extracts the <return>...</return> value from the SOAP XML response.
     *
     * The SOAP response looks like:
     *   <soap:Envelope>
     *     <soap:Body>
     *       <ns2:createUserResponse>
     *         <return>User created successfully. ID: 5, Username: alice</return>
     *       </ns2:createUserResponse>
     *     </soap:Body>
     *   </soap:Envelope>
     *
     * We use regex to pull out the text inside <return>...</return>
     */
    private static String extractReturnValue(String xml) {
        // Handles optional namespace prefix like <ns2:return> or just <return>
        Pattern pattern = Pattern.compile("<(?:\\w+:)?return>(.*?)</(?:\\w+:)?return>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(xml);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "Error: Could not parse response.";
    }
}