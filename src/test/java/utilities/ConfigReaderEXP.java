package utilities;  // Organizes utility classes together

// Import required Java I/O classes
import java.io.FileInputStream;  // Reads data from files
import java.io.IOException;  // Handles input/output exceptions
import java.util.Properties;  // Manages key-value configuration pairs

public class ConfigReaderEXP {
    // Static Properties object to store configuration values
    public static Properties properties;

    // Static initialization block - runs when class is loaded
    static {
        String path = "configuration.properties";  // Path to config file

        try {
            // Create input stream to read the file
            FileInputStream fis = new FileInputStream(path);

            // Initialize Properties object
            properties = new Properties();

            // Load properties from the file
            properties.load(fis);

            // Close the input stream
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();  // Print stack trace if file reading fails
        }
    }

    // Method to retrieve property values by key
    public static String getProperty(String key) {
        return properties.getProperty(key);  // Returns value or null if key doesn't exist
    }
}

/*
Key Points:

Uses Java's Properties class to manage configuration

Loads properties file only once when class is loaded

Provides static access to configuration values

File path is relative to project root

Simple error handling with printStackTrace
 */