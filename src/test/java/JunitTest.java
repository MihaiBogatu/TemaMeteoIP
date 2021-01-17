import org.json.JSONObject;
import org.junit.Test;
import ro.mta.se.lab.JsonParser;

import java.io.IOException;
import java.net.URL;

import static junit.framework.TestCase.assertEquals;

public class JunitTest {
    @Test
    // verify city name after Http request
    public void test_cityName() throws IOException {
        JSONObject obj1 = new JSONObject(JsonParser.URLReader(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + "Moscow" + "&appid=b60c405c93cbab2f48d524870b8b0733")));
        assertEquals(obj1.getString("name"),"Moscow");
    }

    @Test
    // verify country name after Http request
    public void test_countryName() throws IOException {
        JSONObject obj1 = new JSONObject(JsonParser.URLReader(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + "London" + "&appid=b60c405c93cbab2f48d524870b8b0733")));
        assertEquals(obj1.getJSONObject("sys").getString("country"),"GB");
    }

}
