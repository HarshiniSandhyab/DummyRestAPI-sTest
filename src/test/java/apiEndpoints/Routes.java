package apiEndpoints;

import org.testng.annotations.Test;

public class Routes {
    public static String BaseUrl = "https://petstore.swagger.io/v2";
    public static String post_url = BaseUrl +"/user";
    public static String get_url = BaseUrl +"/user{username}";
    public static String put_url = BaseUrl + "/user{username}";
    public static String delete_url = BaseUrl+ "/user{username}";
    public static String petPost_url = BaseUrl + "/pet";
    public static String petGet_url = BaseUrl +"/pet/{petId}";
    public static String petPut_url = BaseUrl +"/pet";
    public static String petDelete_url = BaseUrl +"/pet/{petId}";


}
