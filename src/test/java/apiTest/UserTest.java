package apiTest;

import apiEndpoints.Routes;
import apiEndpoints.UserEndpoints;
import apiPayload.User;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;

public class UserTest {
    private Faker faker;
   private User userpayload;
   String username;

@BeforeTest
    public void addValues(){
    faker=new Faker();
    userpayload=new User();
        userpayload.setId(faker.idNumber().hashCode());
        userpayload.setUsername(faker.name().username());
        userpayload.setFirstname(faker.name().firstName());
        userpayload.setLastname(faker.name().lastName());
        userpayload.setEmail(faker.internet().safeEmailAddress());
        userpayload.setPassword(faker.internet().password(5,10));
        userpayload.setPhone(faker.phoneNumber().cellPhone());
    }

@Test(priority =1)
    public void testPostUser(ITestContext context){
          Response res=given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(userpayload)
                    .when()
            .post(Routes.post_url);
     username = res.then().log().all().extract().jsonPath().get("username");
        context.setAttribute("username",username);
    System.out.println("username"+username);
        assertEquals(res.statusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUser(ITestContext context){
    String username= (String) context.getAttribute("username");
    UserEndpoints.updateUser(userpayload,username);
    //Response res=UserEndpoints.updateUser(userpayload, username);
       // res.then().log().all();
    //assertEquals(res.statusCode(),202);
    }
   @Test(priority = 2)
   public void testGetUser(ITestContext context){
    String username= (String) context.getAttribute("username");
    UserEndpoints.getUser(username);
    }
   // @Test(priority = 4)
//    public void testDeleteUser(ITestContext context)
//    {
//        String username= (String) context.getAttribute("username");
//        Response res=UserEndpoints.deleteUser(username);
//        res.then().log().all();
//        Assert.assertEquals(res.statusCode(),204);
//
//    }
}
