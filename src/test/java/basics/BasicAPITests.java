package basics;

import io.restassured.response.Response;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class BasicAPITests extends Base {
    
    private final String testName;
    private final String emailBase;
    private final String testGender;
    private final String testStatus;
    private int createdUserId;
    
 //    @Factory CONSTRUCTOR - receives data row
    @Factory(dataProvider = "userFactoryData", dataProviderClass = data.UserFactoryData.class)
    public BasicAPITests(String name, String emailBase, String gender, String status) {
        this.testName = name;
        this.emailBase = emailBase;
        this.testGender = gender;
        this.testStatus = status;
    }
    
    @Test(groups = "smoke",priority=1)
    public void createUser() {
        String uniqueEmail = emailBase + System.currentTimeMillis() + "@example.com";
        
        User newUser = new User();
        newUser.setName(testName);
        newUser.setEmail(uniqueEmail);
        newUser.setGender(testGender);
        newUser.setStatus(testStatus);
        
        Response response = given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + Base.token)
            .body(newUser)
        .when().post("/users");
        
        response.then().statusCode(201).log().body();
        createdUserId = response.jsonPath().getInt("id");
    }
    
        
    @Test(groups = "smoke",dependsOnMethods="createUser",priority=2)
    public void updateUser() {
    	
    	System.out.println(this.testName);
    	System.out.println(this.testGender);
    	System.out.println(this.emailBase);

    	String uniqueEmail = emailBase + System.currentTimeMillis() + "@example.com";
    	User updatedUser = new User();
        updatedUser.setName(testName);
        updatedUser.setGender(testGender);
        updatedUser.setEmail(uniqueEmail);
        updatedUser.setStatus(testStatus);
        
        Response response = given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + Base.token)
            .body(updatedUser)
            .when().log().all().put("/users/"+createdUserId);
        
        response.then().statusCode(200).log().body();
    }
    
    @Test(groups = "smoke", dependsOnMethods = "createUser",priority=3)
    public void deleteUser() {
        given()
            .header("Authorization", "Bearer " + Base.token)
        .when().delete("/users/" + createdUserId)
        .then().statusCode(204);
        
    }
    
    
    @Test(groups = "smoke", dependsOnMethods = "deleteUser",priority=4)
    public void verifyUserDeleted() {
        given()
            .header("Authorization", "Bearer " + Base.token)
        .when().get("/users/" + createdUserId)
        .then().statusCode(404);
    }
}
