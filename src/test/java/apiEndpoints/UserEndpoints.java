package apiEndpoints;

import apiPayload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class UserEndpoints {
//    public static Response createUser(User userpayload) {
//        Response res = given()
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .body(userpayload)
//
//                .when()
//                .post(Routes.post_url);
//        return res;
//    }

    public static Response getUser(String username) {
        Response res = given()
                .pathParam("username",username)

                .when()
                .get(Routes.get_url);
        Assert.assertEquals(res.statusCode(),200);
        return res;


    }

    public static Response updateUser(User payload,String username) {
        Response res=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when()
                .put(Routes.put_url);
        Assert.assertEquals(res.statusCode(),202);
                return res;
    }

    public static Response deleteUser(String username) {
        Response res = given()
                .pathParam("username", username)

                .when()
                .delete(Routes.delete_url);
        if(res.statusCode()==204)
        {
            System.out.println("data created is deleted");
        }
        else
        {
            System.out.println("created data is not deleted");
        }
        return res;

    }
}
