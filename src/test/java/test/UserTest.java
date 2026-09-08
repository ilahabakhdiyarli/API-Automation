package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest extends BaseTest {


    private String uniqueEmail() {
        return "test_" + System.currentTimeMillis() + "@mail.com";
    }

    private String createUser(String name, String email) {
        return given()
                .spec(authSpec(adminToken))
                .body("{ \"name\": \"" + name + "\", \"email\": \"" + email + "\" }")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().path("id");
    }

    @Test(description = "Siyahının strukturu")
    public void task01_getAllUsers_structure() {
        given()
                .spec(publicSpec())
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0].id", notNullValue())
                .body("[0].name", notNullValue())
                .body("[0].email", notNullValue());
    }

    @Test(description = "Tək istifadəçi — mövcud olan")
    public void task02_getExistingUser_matchesList() {
        Response list =
        given()
                 .spec(publicSpec())
        .when()
                .get("/users")
        .then()
                .statusCode(200)
                .extract().response();

        String id          = list.path("[0].id");
        String nameInList  = list.path("[0].name");
        String emailInList = list.path("[0].email");

        given()
                .spec(publicSpec())
                .pathParam("id", 1)
        .when()
                .get("/users/{id}")
        .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(nameInList))
                .body("email", equalTo(emailInList));
    }

    @Test(description = "Tək istifadəçi — mövcud olmayan")
    public void task02_getNonExistingUser_returns404() {
        given()
                .spec(publicSpec())
                .pathParam("id", 999999)
        .when()
                .get("/users/{id}")
        .then()
                .statusCode(404);
    }

    @Test(description = "Yeni istifadəçi yaratmaq")
    public void task03_createUser_success() {
        String email = uniqueEmail();

        given()
                .spec(authSpec(adminToken))
                .body("{ \"name\": \"Ilaha Test\", \"email\": \"" + email + "\" }")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Ilaha Test"))
                .body("email", equalTo(email))
                .body("id", notNullValue());
    }

    @Test(description = "Yeni istifadəçi yaratmaq2")
    public void task03_createUser_missingField() {
        given()
                .spec(authSpec(adminToken))
                .body("{ \"name\": \"No Email\" }")
                .when()
                .post("/users")
                .then()
                .statusCode(400);
    }


    @Test(description = "Mövcud istifadəçini yeniləmək")
    public void task04_updateUser_applied() {
        String id = createUser("Update Me", uniqueEmail());

        given()
                .spec(authSpec(adminToken))
                .pathParam("id", id)
                .body("{ \"name\": \"Updated Name\", \"email\": \"" + uniqueEmail() + "\" }")
                .when()
                .put("/users/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated Name"));

        given()
                .spec(publicSpec())
                .pathParam("id", id)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated Name"));
    }

    @Test(description = "Mövcud istifadəçini yeniləmək")
    public void task04_updateNonExisting_returns404() {
        given()
                .spec(authSpec(adminToken))
                .pathParam("id", 999999)
                .body("{ \"name\": \"Ghost\" }")
                .when()
                .put("/users/{id}")
                .then()
                .statusCode(404);
    }

    @Test(description = "İstifadəçini silmək")
    public void task05_deleteUser_success() {
        String id = createUser("Delete Me", uniqueEmail());

        given()
                .spec(authSpec(adminToken))
                .pathParam("id", id)
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }

    @Test(description = "İstifadəçini silmək2")
    public void task05_deletedUser_notFound() {
        String id = createUser("Ghost User", uniqueEmail());

        given().spec(authSpec(adminToken)).pathParam("id", id)
                .when().delete("/users/{id}")
                .then().statusCode(anyOf(is(200), is(204)));

        given()
                .spec(publicSpec())
                .pathParam("id", id)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(404);
    }

    @Test(description = "Bonus — tam axın")
    public void task06_fullFlow() {
        String email = uniqueEmail();

        String id = createUser("E2E User", email);

        given().spec(publicSpec()).pathParam("id", id)
                .when().get("/users/{id}")
                .then().statusCode(200).body("name", equalTo("E2E User"));

        given().spec(authSpec(adminToken)).pathParam("id", id)
                .body("{ \"name\": \"E2E Updated\", \"email\": \"" + email + "\" }")
                .when().put("/users/{id}")
                .then().statusCode(200);

        given().spec(publicSpec()).pathParam("id", id)
                .when().get("/users/{id}")
                .then().statusCode(200).body("name", equalTo("E2E Updated"));

        given().spec(authSpec(adminToken)).pathParam("id", id)
                .when().delete("/users/{id}")
                .then().statusCode(anyOf(is(200), is(204)));

        given().spec(publicSpec()).pathParam("id", id)
                .when().get("/users/{id}")
                .then().statusCode(404);
    }
}