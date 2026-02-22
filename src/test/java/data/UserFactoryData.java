package data;

import org.testng.annotations.DataProvider;
import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Random;

public class UserFactoryData {
    
    @DataProvider(name = "userFactoryData")
    public static Object[][] getFactoryData() 
    {
        Faker faker = new Faker(new Locale("en-IND")); // Using en-IND locale for more realistic Indian names/data if needed, or just en-US
        Random random = new Random();

        // Generate 3 random users
        Object[][] data = new Object[2][4]; // 3 users, 4 fields each

        for (int i = 0; i < 2; i++) {
            String name = faker.name().fullName();
            String emailBase = faker.name().firstName().toLowerCase(); // Use first name as base for email
            String gender = random.nextBoolean() ? "male" : "female";
            String status = random.nextBoolean() ? "active" : "inactive";

            data[i][0] = name;
            data[i][1] = emailBase; // This will be used to construct unique email in BasicAPITests
            data[i][2] = gender;
            data[i][3] = status;
        }
        return data;
    }
}
