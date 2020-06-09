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
import model.Loan;

public class ReturnMovieWindow {
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

        TextField movieTitleField = new TextField();
        movieTitleField.setPromptText("Movie Title");
        GridPane.setConstraints(movieTitleField, 2, 0);

        Button returnButton = new Button("Return");
        returnButton.setOnAction(e -> {
            returnMovie(dbMediator,  Integer.parseInt(clientIDField.getText()), movieTitleField.getText());
            window.close();
        });
        GridPane.setConstraints(returnButton, 0, 5);

        Button exitButton = new Button("Menu");
        exitButton.setOnAction(e -> window.close());
        GridPane.setConstraints(exitButton, 2, 5);

        gridPane.getChildren().addAll(clientIDField, movieTitleField, returnButton, exitButton);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void returnMovie(DbMediator dbm, int clientID, String movieTitle) {
        try {
            Client client = dbm.getClient(clientID);
            Loan loan = dbm.getLoan(clientID, movieTitle);
            double fine = dbm.returnMovie(loan.getLoanID(), null);
            SuccessWindow.display("SUCCESS!", String.format("Returned %s by %s %s.\n Fine: %s  ",
                    movieTitle, client.getFirstName(), client.getLastName(), fine));
        } catch (Exception e) {
            SuccessWindow.display("FAIL!", e.getMessage());
        }
    }
}
