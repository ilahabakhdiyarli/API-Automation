package utils;                                 // "mən utils qovluğundayam" deyir

import base.BaseTest;                          // BaseTest-dəki email/parol sabitlərini götürmək üçün
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class TokenManager {


    private static String login(String email, String password) {
        String body = "{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }";

        return given()
                .baseUri(BaseTest.BASE_URI)      // hara sorğu atacağını deyir
                .contentType(ContentType.JSON)   // "JSON göndərirəm"
                .body(body)                      // email+parol bədəni
                .when()
                .post("/auth/login")             // login endpoint-i
                .then()
                .statusCode(200)                 // uğurlu login gözləyirik
                .extract().path("token");        // cavabdan "token" açarını çıxar
    }


    public static String adminToken() {
        return login(BaseTest.ADMIN_EMAIL, BaseTest.ADMIN_PASSWORD);
    }

    public static String userToken() {
        return login(BaseTest.USER_EMAIL, BaseTest.USER_PASSWORD);
    }
}