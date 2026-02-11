package com.myapp;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.myapp.user.UserPublisher;
import com.myapp.user.UserSoapClient;
import com.myapp.user.UserDataStore;
import com.myapp.user.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws IOException {
        // STEP 1: Start the SOAP Web Service (Task 1) on port 9090
        UserPublisher.startService();

        // STEP 2: Start the Web UI server on port 8080
        HttpServer webServer = HttpServer.create(new InetSocketAddress(8080), 0);

        // Route "/" → serves the HTML dashboard
        webServer.createContext("/", new DashboardHandler());

        // Route "/api/users" → handles CRUD API calls (which use the SOAP client internally)
        webServer.createContext("/api/users", new UserApiHandler());

        webServer.setExecutor(null);
        webServer.start();
        System.out.println("Web Dashboard running at: http://localhost:8080");
    }

    // ========== Serves the HTML page ==========
    static class DashboardHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = getDashboardHtml();
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }

    // ========== Handles API requests from the Web UI ==========
    // Each request internally calls UserSoapClient (which sends manual SOAP XML)
    static class UserApiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String jsonResponse = "";
            int status = 200;

            try {
                if ("GET".equalsIgnoreCase(method)) {
                    // Return all users as JSON (for the table display)
                    ArrayList<User> users = UserDataStore.getAllUsers();
                    jsonResponse = "[" + users.stream()
                            .map(u -> String.format(
                                    "{\"id\":%d,\"username\":\"%s\",\"password\":\"%s\"}",
                                    u.getId(), u.getUsername(), u.getPassword()))
                            .collect(Collectors.joining(",")) + "]";

                } else if ("POST".equalsIgnoreCase(method)) {
                    // CREATE - calls SOAP service via manual client
                    String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                    String username = getJsonValue(body, "username");
                    int id = Integer.parseInt(getJsonValue(body, "id"));
                    String password = getJsonValue(body, "password");

                    String result = UserSoapClient.createUser(username, id, password);
                    jsonResponse = "{\"message\":\"" + result + "\"}";

                } else if ("PUT".equalsIgnoreCase(method)) {
                    // UPDATE - calls SOAP service via manual client
                    String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                    int id = Integer.parseInt(getJsonValue(body, "id"));
                    String username = getJsonValue(body, "username");
                    String password = getJsonValue(body, "password");

                    String result = UserSoapClient.updateUser(id, username, password);
                    jsonResponse = "{\"message\":\"" + result + "\"}";

                } else if ("DELETE".equalsIgnoreCase(method)) {
                    // DELETE - calls SOAP service via manual client
                    String query = exchange.getRequestURI().getQuery();
                    int id = Integer.parseInt(query.split("=")[1]);

                    String result = UserSoapClient.deleteUser(id);
                    jsonResponse = "{\"message\":\"" + result + "\"}";
                }

            } catch (Exception e) {
                e.printStackTrace();
                status = 500;
                jsonResponse = "{\"error\":\"" + e.getMessage() + "\"}";
            }

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            byte[] bytes = jsonResponse.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(status, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }

    // Simple JSON field parser using regex
    private static String getJsonValue(String json, String key) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\"" + key + "\":\"?(.*?)\"?[,}]");
        java.util.regex.Matcher m = p.matcher(json);
        return m.find() ? m.group(1).trim() : "";
    }

    // ========== THE WEB UI HTML ==========
    private static String getDashboardHtml() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>User Management System</title>\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap\" rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "        :root {\n" +
                "            --primary: #2563eb;\n" +
                "            --primary-hover: #1d4ed8;\n" +
                "            --danger: #dc2626;\n" +
                "            --bg: #f0f2f5;\n" +
                "            --card: #ffffff;\n" +
                "            --text: #1a1a1a;\n" +
                "            --muted: #6b7280;\n" +
                "            --border: #e5e7eb;\n" +
                "        }\n" +
                "        * { box-sizing: border-box; margin: 0; padding: 0; }\n" +
                "        body {\n" +
                "            font-family: 'Inter', sans-serif;\n" +
                "            background: var(--bg); color: var(--text);\n" +
                "            padding: 40px 20px;\n" +
                "            display: flex; flex-direction: column; align-items: center;\n" +
                "        }\n" +
                "        .container { width: 100%; max-width: 900px; }\n" +
                "        h1 { font-size: 1.75rem; margin-bottom: 24px; text-align: center; }\n" +
                "        .card {\n" +
                "            background: var(--card); border-radius: 16px; padding: 24px;\n" +
                "            box-shadow: 0 1px 20px rgba(0,0,0,0.05); margin-bottom: 24px;\n" +
                "        }\n" +
                "        .form-grid {\n" +
                "            display: grid; grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));\n" +
                "            gap: 16px; margin-bottom: 20px;\n" +
                "        }\n" +
                "        .input-group { display: flex; flex-direction: column; gap: 6px; }\n" +
                "        label { font-size: 0.85rem; font-weight: 500; padding-left: 4px; }\n" +
                "        input {\n" +
                "            border: 2px solid var(--border); border-radius: 10px;\n" +
                "            padding: 10px 12px; font-size: 0.95rem;\n" +
                "        }\n" +
                "        input:focus { border-color: var(--primary); outline: none; }\n" +
                "        .btn {\n" +
                "            background: var(--primary); color: white; border: none;\n" +
                "            padding: 10px 22px; border-radius: 20px; font-weight: 600;\n" +
                "            cursor: pointer; font-size: 0.9rem;\n" +
                "        }\n" +
                "        .btn:hover { background: var(--primary-hover); }\n" +
                "        .btn-clear { background: #e5e7eb; color: var(--text); }\n" +
                "        .btn-clear:hover { background: #d1d5db; }\n" +
                "        table { width: 100%; border-collapse: collapse; }\n" +
                "        th {\n" +
                "            text-align: left; padding: 12px 16px; color: var(--muted);\n" +
                "            font-size: 0.8rem; text-transform: uppercase;\n" +
                "            border-bottom: 1px solid var(--border);\n" +
                "        }\n" +
                "        td { padding: 14px 16px; border-bottom: 1px solid var(--border); }\n" +
                "        .edit-btn { color: #2563eb; cursor:pointer; background:none; border:none; font-weight:600; }\n" +
                "        .del-btn { color: #dc2626; cursor:pointer; background:none; border:none; font-weight:600; }\n" +
                "        .edit-btn:hover, .del-btn:hover { text-decoration: underline; }\n" +
                "        .msg { padding:12px; border-radius:10px; margin-bottom:16px; display:none;\n" +
                "               background:#eff6ff; color:#1d4ed8; font-size:0.9rem; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h1>User Management System</h1>\n" +
                "        <div id=\"msgBox\" class=\"msg\"></div>\n" +
                "        <div class=\"card\">\n" +
                "            <div class=\"form-grid\">\n" +
                "                <div class=\"input-group\">\n" +
                "                    <label>User ID</label>\n" +
                "                    <input type=\"number\" id=\"userId\" placeholder=\"Enter ID\">\n" +
                "                </div>\n" +
                "                <div class=\"input-group\">\n" +
                "                    <label>Username</label>\n" +
                "                    <input type=\"text\" id=\"username\" placeholder=\"Enter username\">\n" +
                "                </div>\n" +
                "                <div class=\"input-group\">\n" +
                "                    <label>Password</label>\n" +
                "                    <input type=\"text\" id=\"password\" placeholder=\"Enter password\">\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div style=\"display:flex; gap:10px;\">\n" +
                "                <button class=\"btn\" onclick=\"saveUser()\" id=\"saveBtn\">Create User</button>\n" +
                "                <button class=\"btn btn-clear\" onclick=\"clearForm()\">Clear</button>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"card\" style=\"padding: 8px 0;\">\n" +
                "            <table>\n" +
                "                <thead><tr>\n" +
                "                    <th style=\"padding-left:20px\">ID</th>\n" +
                "                    <th>Username</th>\n" +
                "                    <th>Password</th>\n" +
                "                    <th style=\"padding-right:20px\">Actions</th>\n" +
                "                </tr></thead>\n" +
                "                <tbody id=\"userTable\"></tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <script>\n" +
                "        let editMode = false;\n" +
                "        function showMsg(text) {\n" +
                "            const box = document.getElementById('msgBox');\n" +
                "            box.innerText = text; box.style.display = 'block';\n" +
                "            setTimeout(() => box.style.display = 'none', 3000);\n" +
                "        }\n" +
                "        async function loadUsers() {\n" +
                "            const res = await fetch('/api/users');\n" +
                "            const data = await res.json();\n" +
                "            document.getElementById('userTable').innerHTML = data.map(u => `\n" +
                "                <tr>\n" +
                "                    <td style=\"padding-left:20px;color:var(--muted)\">#${u.id}</td>\n" +
                "                    <td style=\"font-weight:600\">${u.username}</td>\n" +
                "                    <td>${u.password}</td>\n" +
                "                    <td style=\"padding-right:20px\">\n" +
                "                        <button class=\"edit-btn\" onclick='startEdit(${JSON.stringify(u)})'>Edit</button>\n" +
                "                        <button class=\"del-btn\" onclick=\"deleteUser(${u.id})\">Delete</button>\n" +
                "                    </td>\n" +
                "                </tr>`).join('');\n" +
                "        }\n" +
                "        async function saveUser() {\n" +
                "            const id = document.getElementById('userId').value;\n" +
                "            const username = document.getElementById('username').value;\n" +
                "            const password = document.getElementById('password').value;\n" +
                "            if (!id || !username || !password) { showMsg('Please fill all fields'); return; }\n" +
                "            let result;\n" +
                "            if (editMode) {\n" +
                "                result = await fetch('/api/users', {\n" +
                "                    method:'PUT', body: JSON.stringify({id, username, password})\n" +
                "                });\n" +
                "            } else {\n" +
                "                result = await fetch('/api/users', {\n" +
                "                    method:'POST', body: JSON.stringify({id, username, password})\n" +
                "                });\n" +
                "            }\n" +
                "            const data = await result.json();\n" +
                "            showMsg(data.message || data.error);\n" +
                "            clearForm(); loadUsers();\n" +
                "        }\n" +
                "        async function deleteUser(id) {\n" +
                "            if (!confirm('Delete user #' + id + '?')) return;\n" +
                "            const res = await fetch(`/api/users?id=${id}`, {method:'DELETE'});\n" +
                "            const data = await res.json();\n" +
                "            showMsg(data.message); loadUsers();\n" +
                "        }\n" +
                "        function startEdit(u) {\n" +
                "            document.getElementById('userId').value = u.id;\n" +
                "            document.getElementById('userId').readOnly = true;\n" +
                "            document.getElementById('username').value = u.username;\n" +
                "            document.getElementById('password').value = u.password;\n" +
                "            document.getElementById('saveBtn').innerText = 'Update User';\n" +
                "            editMode = true;\n" +
                "        }\n" +
                "        function clearForm() {\n" +
                "            document.getElementById('userId').value = '';\n" +
                "            document.getElementById('userId').readOnly = false;\n" +
                "            document.getElementById('username').value = '';\n" +
                "            document.getElementById('password').value = '';\n" +
                "            document.getElementById('saveBtn').innerText = 'Create User';\n" +
                "            editMode = false;\n" +
                "        }\n" +
                "        loadUsers();\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>;\n";
    }
}