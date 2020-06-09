package visualization;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Address;
import model.Client;

public class ClientInfo {
    public static void display(Client client){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(500);
        window.setMinHeight(200);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label nameLabel = new Label(String.format("NAME: %s %s", client.getFirstName(), client.getLastName()));
        nameLabel.setAlignment(Pos.CENTER);
        GridPane.setConstraints(nameLabel, 0, 0, 5, 1);

        Label phoneLabel = new Label(String.format("PHONE: %s", client.getPhone()));
        phoneLabel.setAlignment(Pos.CENTER);
        GridPane.setConstraints(phoneLabel, 0, 1, 5, 1);

        Label emailLabel = new Label(String.format("EMAIL: %s", client.getEmail()));
        emailLabel.setAlignment(Pos.CENTER);
        GridPane.setConstraints(emailLabel, 0, 2, 5, 1);

        Address address = client.getAddress();
        Label addressLabel = new Label(String.format("COUNTRY: %s, CITY: %s, STREET: %s, ZIP-CODE: %s",
                address.getCountry(), address.getCity(), address.getStreet(), address.getZip()));
        addressLabel.setAlignment(Pos.CENTER);
        GridPane.setConstraints(addressLabel, 0, 3, 5, 1);

        Button exitButton = new Button("Menu");
        exitButton.setOnAction(e -> window.close());
        GridPane.setConstraints(exitButton, 2, 5);

        gridPane.getChildren().addAll(nameLabel, phoneLabel, emailLabel, addressLabel, exitButton);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.showAndWait();
    }
}
