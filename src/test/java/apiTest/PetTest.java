package apiTest;

import apiEndpoints.PetEndPoints;
import apiEndpoints.Routes;
import apiPayload.Pet;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static java.util.concurrent.TimeUnit.DAYS;


public class PetTest {
    private Pet pet;
    private Faker faker;

    @BeforeTest
    public void giveValues() {
         pet = new Pet();
         faker = new Faker();
        pet.setId(faker.idNumber().hashCode());
        pet.setShipDate(faker.date().past(365, DAYS));

    }
   @Test(priority = 1)
    public void testPostPet(ITestContext context) {
       Response res=given()
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
               .body(pet)
               .when()
               .post(Routes.petPost_url);
              int id=res.then().log().all().extract().jsonPath().get("id");
               context.setAttribute("id",id);
       System.out.println("id is"+id);
               Assert.assertEquals(res.statusCode(),200);
   }
@Test(priority = 2)
  public void testGetPet(ITestContext context) {
    int id = (int) context.getAttribute("id");
    if (id != 0) {
        PetEndPoints.getreq(id);
        System.out.println(id);
    } else {
        System.out.println("ID attribute not found or is null");
    }

}


@Test(priority = 3)
  public void testPutPet(ITestContext context) {
    int id = (int) context.getAttribute("id");
    //System.out.println("while put "+id);
    pet.setStatus("i have placed successfully");
    PetEndPoints.putreq(pet);
    }

//    @Test(priority=4)
//    public void testDeletePet(ITestContext context) {
//        int id= (int) context.getAttribute("id");
//        Response res = PetEndPoints.deletereq(id);
//        res.then().log().all();
//        //Assert.assertEquals(res.statusCode(), 204);
//    }

}
