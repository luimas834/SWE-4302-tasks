package com.myapp.user;

import java.util.ArrayList;

/**
 * UserDataStore - In-memory storage using ArrayList<User>
 *
 * As per the assignment requirement:
 * "All user objects are stored in an ArrayList<User> that acts as an in-memory data store."
 */
public class UserDataStore {

    // THE ArrayList that stores all users (exactly as the assignment asks)
    private static final ArrayList<User> userList = new ArrayList<>();

    // Add some sample users so the system isn't empty at startup
    static {
        userList.add(new User("admin", 1, "admin123"));
        userList.add(new User("john_doe", 2, "pass456"));
        userList.add(new User("jane_smith", 3, "secure789"));
    }

    // Returns the entire ArrayList
    public static ArrayList<User> getAllUsers() {
        return userList;
    }

    // Search the ArrayList for a user with the given ID
    public static User findUserById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // not found
    }

    // Add a new user to the ArrayList
    public static void addUser(User user) {
        userList.add(user);
    }

    // Remove a user from the ArrayList by ID
    public static boolean removeUser(int id) {
        // We loop through and find the matching user, then remove it
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                userList.remove(i);
                return true;
            }
        }
        return false; // not found
    }
}