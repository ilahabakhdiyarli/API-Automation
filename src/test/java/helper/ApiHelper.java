package helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Config;

public class ApiHelper {

    private RequestSpecification given() {
        return RestAssured
        .given()
                .baseUri(Config.BASE_URL)
                .header("Accept", "application/json");
    }

    public Response get(String endpoint) {
        return given()
       .when()
                .get(endpoint)
       .then()
                .extract()
                .response();
    }
    public Response post(String endpoint, String body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response post(String endpoint, String body, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }
}