package swaggerexercise.CreateUsersTests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import swaggerexercise.pojo.CreateUserRequestPayload;
import swaggerexercise.pojo.ReadUserResponse;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@RunWith(DataProviderRunner.class)
public class UserCreationPetStore extends BaseTest {
    @DataProvider
    public static Object[] providePayloadSingleUserCreation() {
        CreateUserRequestPayload user = new CreateUserRequestPayload(100, "testfairy1", "test1", "fairy1", "testfairy1@gmail.com", "fairtester1", "7638322378", 1);

        return new Object[]{user};
    }

    @DataProvider
    public static Object[][] providePayloadMultipleUserCreation() {

        List<CreateUserRequestPayload> userCreationArray1 = new ArrayList<>();
        List<CreateUserRequestPayload> userCreationArray2 = new ArrayList<>();

        CreateUserRequestPayload user2 = new CreateUserRequestPayload(101, "testfairy2", "test2", "fairy2", "testfairy2@gmail.com", "fairtester2", "7638422378", 1);
        CreateUserRequestPayload user3 = new CreateUserRequestPayload(102, "testfairy3", "test3", "fairy3", "testfairy3@gmail.com", "fairtester3", "7638522378", null);
        CreateUserRequestPayload user4 = new CreateUserRequestPayload(102, "testfairy3", "test3", "fairy3", "testfairy3@gmail.com", "fairtester3", "7638522378", 0);

        userCreationArray1.add(user2);
        userCreationArray1.add(user3);

        userCreationArray2.add(user2);
        userCreationArray2.add(user4);

        return new Object[][]{{userCreationArray1, 200}, {userCreationArray2, 200}};
    }

    @Test
    @UseDataProvider("providePayloadSingleUserCreation")
    public void verifySingleUserCreation(CreateUserRequestPayload user) {

        ReadUserResponse responseBodyParsing =
                given().
                        spec(singleUserCreationSpec).
                        body(user).
                        log().
                        body().
                        when().
                        post().
                        as(ReadUserResponse.class);

        responseBodyParsing.printUserCreationResponseBody();

        Assert.assertEquals(200, responseBodyParsing.getCode());
        Assert.assertEquals("unknown", responseBodyParsing.getType());
    }

    @Test
    @UseDataProvider("providePayloadSingleUserCreation")
    public void verifyErrorInvalidURL(CreateUserRequestPayload user) {

        given().
                contentType(ContentType.JSON).
                body(user).
                log().
                body().
                when().
                post("https://petstore.swagger.io/v1/user").
                then().assertThat().statusCode(404);
    }

    @Test
    public void verifyErrorInvalidContentType() {

        String jsonPayload = "{\n" +
                "    \"id\": 0,\n" +
                "    \"username\": \"testfairy\",\n" +
                "    \"firstName\": \"test\",\n" +
                "    \"lastName\": \"fairy\",\n" +
                "    \"email\": \"testfairy@gmail.com\",\n" +
                "    \"password\": \"fairtester\",\n" +
                "    \"phone\": \"7638922378\",\n" +
                "    \"userStatus\": 0\n" +
                "}";
        given().
                contentType(ContentType.XML).
                body(jsonPayload).
                log().
                body().
                when().
                post("https://petstore.swagger.io/v2/user").
                then().assertThat().statusCode(400);
    }

    @Test
    public void verifyErrorUnSupportedMediaType() {

        String jsonPayload = "{\n" +
                "    \"id\": 0,\n" +
                "    \"username\": \"testfairy\",\n" +
                "    \"firstName\": \"test\",\n" +
                "    \"lastName\": \"fairy\",\n" +
                "    \"email\": \"testfairy@gmail.com\",\n" +
                "    \"password\": \"fairtester\",\n" +
                "    \"phone\": \"7638922378\",\n" +
                "    \"userStatus\": 0\n" +
                "}";
        given().
                contentType(ContentType.TEXT).
                body(jsonPayload).
                log().
                body().
                when().
                post("https://petstore.swagger.io/v2/user").
                then().assertThat().statusCode(415);
    }

    @Test
    public void verifyErrorInvalidUserStatus() {
        String jsonPayload = "{\n" +
                "    \"id\": 0,\n" +
                "    \"username\": \"testfairy\",\n" +
                "    \"firstName\": \"test\",\n" +
                "    \"lastName\": \"fairy\",\n" +
                "    \"email\": \"testfairy@gmail.com\",\n" +
                "    \"password\": \"fairtester\",\n" +
                "    \"phone\": \"7638922378\",\n" +
                "    \"userStatus\": true\n" +
                "}";
        given().
                spec(singleUserCreationSpec).
                body(jsonPayload).
                log().
                body().
                when().
                post().
                then().assertThat().statusCode(500);
    }

    @Test
    public void verifyErrorInvalidId() {
        String jsonPayload = "{\n" +
                "    \"id\": \"abc\",\n" +
                "    \"username\": \"testfairy\",\n" +
                "    \"firstName\": \"test\",\n" +
                "    \"lastName\": \"fairy\",\n" +
                "    \"email\": \"testfairy@gmail.com\",\n" +
                "    \"password\": \"fairtester\",\n" +
                "    \"phone\": \"7638922378\",\n" +
                "    \"userStatus\": true\n" +
                "}";
        given().
                spec(singleUserCreationSpec).
                body(jsonPayload).
                log().
                body().
                when().
                post().
                then().assertThat().statusCode(500);
    }

    @Test
    @UseDataProvider("providePayloadSingleUserCreation")
    public void verifyAllowMethodsGet(CreateUserRequestPayload user) {

        given().
                spec(singleUserCreationSpec).
                body(user).
                log().
                body().
                when().
                get().
                then().assertThat().statusCode(405);
    }

    @Test
    @UseDataProvider("providePayloadSingleUserCreation")
    public void verifyAllowMethodsPut(CreateUserRequestPayload user) {

        given().
                spec(singleUserCreationSpec).
                body(user).
                log().
                body().
                when().
                put().
                then().assertThat().statusCode(405);
    }

    @Test
    @UseDataProvider("providePayloadSingleUserCreation")
    public void verifyAllowMethodsDelete(CreateUserRequestPayload user) {

        given().
                spec(singleUserCreationSpec).
                body(user).
                log().
                body().
                when().
                delete().
                then().assertThat().statusCode(405);
    }

    @Test
    @UseDataProvider("providePayloadMultipleUserCreation")
    public void verifyMultipleUsersCreation(List userCreationArray, int statusCode) {

        ReadUserResponse responseBody =
                given().
                        spec(multipleUserCreationSpec).
                        body(userCreationArray).
                        log().
                        body().
                        when().
                        post().
                        as(ReadUserResponse.class);

        responseBody.printUserCreationResponseBody();

        Assert.assertEquals(statusCode, responseBody.getCode());
        Assert.assertEquals("unknown", responseBody.getType());
    }

    @Test
    @UseDataProvider("providePayloadMultipleUserCreation")
    public void verifyMultipleUsersCreationWithSingleUserCreationEndPoint(List userCreationArray, int statusCode) {

        ReadUserResponse responseBody =
                given().
                        spec(singleUserCreationSpec).
                        body(userCreationArray).
                        log().
                        body().
                        when().
                        post().
                        as(ReadUserResponse.class);

        responseBody.printUserCreationResponseBody();

        Assert.assertEquals(500, responseBody.getCode());
        Assert.assertEquals("unknown", responseBody.getType());
    }

    //(description = "Invalid URL getting success instead of 404 Not Found")
    @Test
    @UseDataProvider("providePayloadMultipleUserCreation")
    public void verifyMultipleUsersCreationInvalidURL(List userCreationArray, int statusCode) {

        ReadUserResponse responseBody =
                given().
                        spec(multipleUserCreationSpec).
                        body(userCreationArray).
                        log().
                        body().
                        when().
                        post().
                        as(ReadUserResponse.class);

        responseBody.printUserCreationResponseBody();

        Assert.assertEquals(404, responseBody.getCode());
        Assert.assertEquals("unknown", responseBody.getType());
    }
}
