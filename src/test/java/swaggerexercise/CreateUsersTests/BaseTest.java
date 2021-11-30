package swaggerexercise.CreateUsersTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

public class BaseTest {
    public static String baseURL = "https://petstore.swagger.io/v2";
    public static String singleUserCreationEndPoint = "/user";
    public static String multipleUserCreationEndPoint = "/user/createWithArray";

    static RequestSpecification singleUserCreationSpec;
    static RequestSpecification multipleUserCreationSpec;

    @BeforeClass
    public static void setup() {
        RequestSpecBuilder createSingleUserReq = new RequestSpecBuilder();

        createSingleUserReq.setBaseUri(baseURL);
        createSingleUserReq.setBasePath(singleUserCreationEndPoint);
        createSingleUserReq.setContentType(ContentType.JSON);

        singleUserCreationSpec = createSingleUserReq.build();

        RequestSpecBuilder multipleUserReq = new RequestSpecBuilder();

        multipleUserReq.setBaseUri(baseURL);
        multipleUserReq.setBasePath(multipleUserCreationEndPoint);
        multipleUserReq.setContentType(ContentType.JSON);

        multipleUserCreationSpec = multipleUserReq.build();
    }
}
