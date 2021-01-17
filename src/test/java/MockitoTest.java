import ro.mta.se.lab.JsonParser;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {


    public static void main ( String[] args)throws IOException {
        //  create mock
        JsonParser test1 = mock(JsonParser.class);

        // define return value for method getUniqueId()
        when(test1.jsonParser("RU","Moscow")).thenReturn("Nume oras: Moscow\n" +
                "Temperatura: 252.48 K\n" +
                "Umiditate: 84.0%\n" +
                "Vant: viteza-2.0, grade-330.0.\n");

        // use mock in test....
        System.out.println(test1.jsonParser("RU","Moscow"));
    }

}