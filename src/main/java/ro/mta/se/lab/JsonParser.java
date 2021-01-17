package ro.mta.se.lab;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class JsonParser {
    private static String apiKey = "b60c405c93cbab2f48d524870b8b0733";


    public String jsonParser(String country, String city) throws IOException {

        // access link in string format
        String link = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&appid=" + apiKey;

        // convert link to url
        String return_string = URLReader(new URL(link));
        JSONObject jsonObject = new JSONObject(return_string);

        // get name, temp, wind from jsonObject
        String res = "Nume oras: " + jsonObject.getString("name");

        Double temp = jsonObject.getJSONObject("main").getDouble("temp");
        Double humidity = jsonObject.getJSONObject("main").getDouble("humidity");
        res = res + "\nTemperatura: " + temp.toString() + " K\nUmiditate: " + humidity.toString();

        Double wind_speed = jsonObject.getJSONObject("wind").getDouble("speed");
        Double wind_deg = jsonObject.getJSONObject("wind").getDouble("deg");
        res = res + "%\nVant: viteza-" + wind_speed + ", grade-" + wind_deg + ".\n";

        return res;
    }

    public static String URLReader(URL url) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;

        // return data from url in string format
        InputStream in = url.openStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } finally {
            in.close();
        }

        return sb.toString();
    }
}