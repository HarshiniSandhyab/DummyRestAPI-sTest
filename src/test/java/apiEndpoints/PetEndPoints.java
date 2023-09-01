package apiEndpoints;

import apiPayload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PetEndPoints {

//    public static  Response postreq(Pet payload,ITestContext context)
//    {
//        int id;
//       given()
//               .contentType(ContentType.JSON)
//               .accept(ContentType.JSON)
//               .body(payload)


//        .when()
//            .post(Routes.petPost_url)
//
//               .then()
//               .log().all().extract().jsonPath().get("id");
//    }
    public static Response getreq(int petid)
    {
        Response res=given()
                .pathParam("petId",petid)

                .when()
                .get(Routes.petGet_url);
        Assert.assertEquals(res.statusCode(),200);
                return res;
    }
     public static Response putreq(Pet pet)
    {
       Response res= given()
               .contentType(ContentType.JSON)
               //.pathParam("petId",petid)
               .accept(ContentType.JSON)
               .body(pet)
               .when()
            .put(Routes.petPut_url);
        Assert.assertEquals(res.statusCode(),200);
            return res;
    }
    public static Response deletereq(int petid)
    {
        Response res= given()
                .pathParam("petId",petid)
                .when()
                .delete(Routes.petDelete_url);
        int status = res.statusCode();
        System.out.println("status is"+status);
                return res;
    }
}
