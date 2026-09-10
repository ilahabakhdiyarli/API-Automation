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
}