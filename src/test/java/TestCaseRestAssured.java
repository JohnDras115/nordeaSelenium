

import io.restassured.response.Response;

import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestCaseRestAssured {
    static Logger log = Logger.getLogger(String.valueOf(TestCaseRestAssured.class));


    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/user";

    }

    @Test
    public void createUserRestAssured() throws IOException {
        String firstName = "John";
        String lastName = "Dras" + RandomStringUtils.random(8, true, false);
        String userName = String.format("%s%s", firstName, lastName);

        User user = new User();
        user.setId("0");
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail("johndras@text.com");
        user.setPassword("password");
        user.setPhone("48665338022");
        user.setUserStatus("0");


        //CREATE user
        Response response =  given()
                .contentType("application/json")
                .baseUri(RestAssured.baseURI)
                .body(user)
                .when()
                .post()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());


        // GET created user
        User responseUser = given()
                .contentType("application/json")
                .baseUri(RestAssured.baseURI)
                .basePath("/" + userName)
                .body(user)
                .when()
                .get()
                .as(User.class);
        assertTrue(responseUser.getUsername().equals(userName));


        //UPDATE phone of user
        // authentication by token doesn't work yet, sorry.
        user.setPhone("4899999998");
        String accessToken="";
        response = given()
                //.header("Authorization", "Bearer " + accessToken)  //todo
                 .auth()
                //.basic("test", "abc123")
                .oauth2(accessToken)
                .contentType("application/json")
                .baseUri(RestAssured.baseURI)
                .basePath("/" + userName)
                .body(user)
                .when()
                .put()
                .then()
                .extract().response();

        // assertTrue(responseUser.getPhone().equals(user.getPhone()));

    }
}


