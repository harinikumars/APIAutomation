package breakoutexercise.sampleexercise;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(DataProviderRunner.class)
public class GetStationDataOn15thApril {

@DataProvider
public static Object[][] provideStationCodeAndName() {
    return new Object[][]{{"RICH", "Richmond"},
            {"LAKE", "Lake Merritt"},
            {"NCON", "North Concord/Martinez"},
            {"COLS", "Coliseum"}
    };
}

    @Test
    @UseDataProvider("provideStationCodeAndName")
            public void stationInformation(String orig, String name) {
        String cmd = "etd";
        String key = "MW9S-E7SL-26DU-VV8V";

        given().queryParam("orig",orig).queryParam("cmd",cmd).queryParam("key",key).
                when().get("https://api.bart.gov/api/etd.aspx").
                then().assertThat().body("root.station[0].name",equalTo(name)).log().all();
    }
}
