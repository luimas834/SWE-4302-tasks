package com.myapp.user;

import jakarta.jws.WebService;

/**
 * TASK 1: SOAP Web Service Implementation
 *
 * This class implements the 4 web methods (Create, Retrieve, Update, Delete).
 * Each method operates on the shared ArrayList<User> in UserDataStore.
 */
@WebService(
        endpointInterface = "com.myapp.user.UserServiceInterface",
        targetNamespace = "http://user.myapp.com/"
)
public class UserServiceImpl implements UserServiceInterface {

    // ===== 1. CREATE USER =====
    // Input: username, id, password
    // Operation: Create a new User object and add it to the ArrayList
    // Output: Confirmation message
    @Override
    public String createUser(String username, int id, String password) {
        // Check if a user with this ID already exists
        if (UserDataStore.findUserById(id) != null) {
            return "Error: User with ID " + id + " already exists.";
        }
        // Create a new User object and add to the ArrayList
        User newUser = new User(username, id, password);
        UserDataStore.addUser(newUser);
        return "User created successfully. ID: " + id + ", Username: " + username;
    }

    // ===== 2. RETRIEVE USER =====
    // Input: id
    // Operation: Search the ArrayList for a user with the given ID
    // Output: User details or an error message if not found
    @Override
    public String retrieveUser(int id) {
        User found = UserDataStore.findUserById(id);
        if (found != null) {
            return "User found - ID: " + found.getId()
                    + ", Username: " + found.getUsername()
                    + ", Password: " + found.getPassword();
        }
        return "Error: User with ID " + id + " not found.";
    }

    // ===== 3. UPDATE USER =====
    // Input: id, updated username, updated password
    // Operation: Update the corresponding User object in the ArrayList
    // Output: Update status message
    @Override
    public String updateUser(int id, String username, String password) {
        User existing = UserDataStore.findUserById(id);
        if (existing != null) {
            existing.setUsername(username);
            existing.setPassword(password);
            return "User updated successfully. ID: " + id
                    + ", New Username: " + username;
        }
        return "Error: User with ID " + id + " not found. Cannot update.";
    }

    // ===== 4. DELETE USER =====
    // Input: id
    // Operation: Remove the matching User object from the ArrayList
    // Output: Deletion confirmation message
    @Override
    public String deleteUser(int id) {
        boolean removed = UserDataStore.removeUser(id);
        if (removed) {
            return "User with ID " + id + " deleted successfully.";
        }
        return "Error: User with ID " + id + " not found. Cannot delete.";
    }
}