package visualization;

import dbAccess.DbMediator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddClientRemarksWindow {
    public static void display(DbMediator dbMediator){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        TextField clientIDField = new TextField();
        clientIDField.setPromptText("Client ID");
        GridPane.setConstraints(clientIDField, 0, 0);

        TextField remarksField = new TextField();
        remarksField.setPromptText("Remarks");
        GridPane.setConstraints(remarksField, 2, 0, 3, 1);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            addClientRemarks(dbMediator,  Integer.parseInt(clientIDField.getText()), remarksField.getText());
            window.close();
        });
        GridPane.setConstraints(addButton, 0, 5);

        Button exitButton = new Button("Menu");
        exitButton.setOnAction(e -> window.close());
        GridPane.setConstraints(exitButton, 2, 5);

        gridPane.getChildren().addAll(clientIDField, remarksField, addButton, exitButton);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void addClientRemarks(DbMediator dbm, int clientID, String remarks) {
        try {
            dbm.addClientRemarks(clientID, remarks);
            SuccessWindow.display("SUCCESS!", "Added Client's Remarks ");
        } catch (Exception e) {
            SuccessWindow.display("FAIL!", e.getMessage());
        }
    }
}
