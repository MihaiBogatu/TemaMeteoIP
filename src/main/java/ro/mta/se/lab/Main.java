package ro.mta.se.lab;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        String[][] file = ReadFile.readFile(getClass().getClassLoader().getResource("in.txt").getFile()); //Read data from file


        primaryStage.setTitle("Weather!");

        // create comboBoxes for country list and city list
        ComboBox countryCB = new ComboBox();
        ComboBox cityCB = new ComboBox();
        for (int i=1;i< file.length;i++)
            if ( !countryCB.getItems().contains(file[i][4]))
                countryCB.getItems().add(file[i][4]);

        for (int i=1;i<file.length;i++)
            if ( !cityCB.getItems().contains(file[i][1]))
                cityCB.getItems().add(file[i][1]);

        countryCB.setOnAction(new EventHandler<ActionEvent>() {

            // handle country comboBox event
            @Override
            public void handle(ActionEvent event) {
                cityCB.getItems().clear();
                String countryCode = countryCB.getValue().toString();
                for (int i=1;i<file.length;i++)
                    if(file[i][4].equals(countryCode) )
                        cityCB.getItems().add(file[i][1]);
            }


        });

        // create a button to handle requests
        Button btn = new Button();
        btn.setText("Get weather!");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            // handle button for requests
            @Override
            public void handle(ActionEvent event) {
                String country = countryCB.getValue().toString();
                String city = cityCB.getValue().toString();
                if ( !city.equals("City") && !country.equals("Country")) {
                    try {
                        var jp = new JsonParser();
                        String res = jp.jsonParser(country,city);
                        System.out.println(res);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        VBox root = new VBox();
        HBox hBox = new HBox();

        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);

        hBox.setSpacing(50);
        hBox.setAlignment(Pos.CENTER);

        hBox.getChildren().add(countryCB);
        hBox.getChildren().add(cityCB);
        cityCB.setValue("City");
        countryCB.setValue("Country");

        root.getChildren().add(hBox);
        root.getChildren().add(btn);

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

}