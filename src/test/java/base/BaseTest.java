package base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utils.TokenManager;

public abstract class BaseTest {

    public static final String BASE_URI =
            System.getProperty("baseUri", "https://api.anarabbas.com");

    public static final long MAX_TIME_MS = 3000L;

    public static final String ADMIN_EMAIL    = "admin@test.com";
    public static final String ADMIN_PASSWORD = "Admin1234";
    public static final String USER_EMAIL     = "user1@test.com";
    public static final String USER_PASSWORD  = "User1234";

    protected String adminToken;
    protected String userToken;

    @BeforeSuite(alwaysRun = true)
    public void globalSetup() {
        RestAssured.baseURI = BASE_URI;
        // Test fail olanda tam sorğu + cavabı konsola yazsın
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        System.out.println("---------------------------------------------");
        System.out.println("Api auto - base URI: " + RestAssured.baseURI);
    }

    @BeforeClass(alwaysRun = true)
    public void authenticate() {
        adminToken = TokenManager.adminToken();
        userToken  = TokenManager.userToken();
    }

    protected RequestSpecification publicSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    protected RequestSpecification authSpec(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }
}