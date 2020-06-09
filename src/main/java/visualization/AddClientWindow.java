package visualization;

import dbAccess.DbMediator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddClientWindow {

    public static void display(DbMediator dbMediator){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        GridPane.setConstraints(firstNameField, 0, 0);

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        GridPane.setConstraints(lastNameField, 2, 0);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        GridPane.setConstraints(phoneField, 0, 1);

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        GridPane.setConstraints(emailField, 2, 1);

        TextField countryField = new TextField();
        countryField.setPromptText("Country");
        GridPane.setConstraints(countryField, 0, 2);

        TextField cityField = new TextField();
        cityField.setPromptText("City");
        GridPane.setConstraints(cityField, 2, 2);

        TextField streetField = new TextField();
        streetField.setPromptText("Street");
        GridPane.setConstraints(streetField, 0, 3);

        TextField zipCodeField = new TextField();
        zipCodeField.setPromptText("Zip Code");
        GridPane.setConstraints(zipCodeField, 2, 3);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            addClient(dbMediator, firstNameField.getText(), lastNameField.getText(),
                    phoneField.getText(), emailField.getText(), countryField.getText(), cityField.getText(),
                    streetField.getText(), zipCodeField.getText());
            window.close();
        });
        GridPane.setConstraints(addButton, 0, 5);


        Button exitButton = new Button("Menu");
        exitButton.setOnAction(e -> window.close());
        GridPane.setConstraints(exitButton, 2, 5);


        gridPane.getChildren().addAll(firstNameField, lastNameField, phoneField, emailField, countryField, cityField,
                streetField, zipCodeField, addButton, exitButton);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void addClient(DbMediator dbm, String firstName, String lastName,
                                  String phoneNumber, String email, String country, String city, String street,
                                  String zipCode) {
        try {
            int clientID = dbm.addClient(firstName, lastName, phoneNumber, email, country, city, street, zipCode);
            SuccessWindow.display("SUCCESS!", "Added Client. ID: " + clientID);
        } catch (Exception e) {
            SuccessWindow.display("FAIL!", e.getMessage());
        }
    }
}
