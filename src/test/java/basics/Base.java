package basics;

import java.io.InputStream;
import java.util.Properties;
import io.restassured.RestAssured;

public class Base {
    
    public static String token;
    static String baseURI = "https://gorest.co.in/public/v2";
    
    public Base() {
        RestAssured.baseURI = baseURI;
    }
    
    static {
        System.out.println("=== Loading Token Configuration ===");
        
        Properties props = new Properties();
        InputStream input = null;
        
        try {
            input = Base.class.getClassLoader().getResourceAsStream("config.properties");
            
            if (input == null) {
                System.err.println("ERROR: config.properties NOT FOUND!");
                System.err.println("Expected location: src/test/resources/config.properties");
                throw new RuntimeException("config.properties missing!");
            }
            
            props.load(input);
            token = props.getProperty("gorest.token");
            
            if (token == null || token.trim().isEmpty()) {
                System.err.println("ERROR: gorest.token property is missing or empty!");
                throw new RuntimeException("gorest.token not configured!");
            }
            
            System.out.println("✓ Token loaded successfully: " + token.substring(0, 10) + "...");
            
        } 
        catch (Exception e) 
        {
            System.err.println("FATAL: Token loading failed!");
            e.printStackTrace();
            throw new RuntimeException("Cannot proceed without token", e);
        } 
        finally 
        {
            if (input != null) 
            {
                try 
                {
                    input.close();
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
