package visualization;

import dbAccess.DbMediator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;

public class showClientInfoWindow {
    public static void display(DbMediator dbMediator){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMinHeight(200);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        TextField clientIDField = new TextField();
        clientIDField.setPromptText("Client ID");
        GridPane.setConstraints(clientIDField, 0, 0);

        Button showButton = new Button("Show");
        showButton.setOnAction(e -> {
            showClientInfo(dbMediator,  Integer.parseInt(clientIDField.getText()));
            window.close();
        });
        GridPane.setConstraints(showButton, 0, 5);

        Button exitButton = new Button("Menu");
        exitButton.setOnAction(e -> window.close());
        GridPane.setConstraints(exitButton, 2, 5);

        gridPane.getChildren().addAll(clientIDField, showButton, exitButton);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void showClientInfo(DbMediator dbm, int clientID) {
        try {
            Client client = dbm.getClient(clientID);
            ClientInfo.display(client);
        } catch (Exception e) {
            SuccessWindow.display("FAIL!", e.getMessage());
        }
    }
}
