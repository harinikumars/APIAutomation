package swaggerexercise.CreateUsersTests;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import swaggerexercise.pojo.CreateUserRequestPayload;
import swaggerexercise.pojo.ReadUserResponse;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserCreationPetStore {

    @Test
    public void verifySingleUserCreation(){
        CreateUserRequestPayload user1 = new CreateUserRequestPayload();

        user1.setId("100");
        user1.setUsername("testfairy");
        user1.setFirstName("test");
        user1.setLastName("fairy");
        user1.setEmail("testfairy@gmail.com");
        user1.setPassword("fairtester");
        user1.setPhone("7638922378");
        user1.setUserStatus(1);

        ReadUserResponse responseBodyParsing =
                given().
                        contentType(ContentType.JSON).
                        body(user1).
                        log().
                        body().
                when().
                        post("https://petstore.swagger.io/v2/user").
                        as(ReadUserResponse.class);

        responseBodyParsing.printUserCreationResponseBody();

        Assert.assertEquals(200, responseBodyParsing.getCode());
        Assert.assertEquals("unknown", responseBodyParsing.getType());
    }
    @Test
    public void verifyMultipleUsersCreation() {
        List<CreateUserRequestPayload> userCreationArray = new ArrayList<>();

        CreateUserRequestPayload user1 = new CreateUserRequestPayload();
        CreateUserRequestPayload user2 = new CreateUserRequestPayload();

        user1.setId("301");
        user1.setUsername("intestinghsk1");
        user1.setFirstName("intesting17");
        user1.setLastName("inhsk11");
        user1.setEmail("testinhsk11@gmail.com");
        user1.setPassword("finetester");
        user1.setPhone("7638922378");
        user1.setUserStatus(1);

        user2.setId("302");
        user2.setUsername("intestinghsk2");
        user2.setFirstName("intesting27");
        user2.setLastName("inhsk12");
        user2.setEmail("testinhsk12@gmail.com");
        user2.setPassword("finetester");
        user2.setPhone("7638932378");
        user2.setUserStatus(1);

        userCreationArray.add(user1);
        userCreationArray.add(user2);

        ReadUserResponse responseBody =
                given().
                        contentType(ContentType.JSON).
                        body(userCreationArray).
                        log().
                        body().
                        when().
                        post("https://petstore.swagger.io/v2/user/createWithArray").
                        as(ReadUserResponse.class);

        responseBody.printUserCreationResponseBody();

        Assert.assertEquals(200, responseBody.getCode());
        Assert.assertEquals("unknown", responseBody.getType());
    }
}
