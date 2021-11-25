package breakoutexercise.sampleexercise;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import breakoutexercise.pojoforreqresbo.CreateUserRequestBody;
import breakoutexercise.pojoforreqresbo.ReadUserResponseBody;
import static io.restassured.RestAssured.given;

public class CreateUser {
    @Test
    public void createUserAndValidateResponse(){
        CreateUserRequestBody userCreation = new CreateUserRequestBody();
        userCreation.setName("Harini");
        userCreation.setJob("SE");

        ReadUserResponseBody response = given().contentType(ContentType.JSON).body(userCreation).log().body().
                when().
                post("https://reqres.in/api/users").as(ReadUserResponseBody.class);

        response.printUserCreationResponseBody();
        Assert.assertEquals("Harini",response.getName());
        Assert.assertEquals("SE",response.getJob());
    }
}
