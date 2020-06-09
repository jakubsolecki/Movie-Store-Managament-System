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
import model.Movie;

public class LoanMovieWindow {
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

        Button loanButton = new Button("Loan");
        loanButton.setOnAction(e -> {
            loanMovie(dbMediator,  Integer.parseInt(clientIDField.getText()), movieTitleField.getText());
            window.close();
        });
        GridPane.setConstraints(loanButton, 0, 5);

        Button exitButton = new Button("Menu");
        exitButton.setOnAction(e -> window.close());
        GridPane.setConstraints(exitButton, 2, 5);

        gridPane.getChildren().addAll(clientIDField, movieTitleField, loanButton, exitButton);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void loanMovie(DbMediator dbm, int clientID, String movieTitle) {
        try {
            Movie movie = dbm.getMovieByTitle(movieTitle);
            Client client = dbm.getClient(clientID);
            int loanID = dbm.loanMovie(clientID, movie.getMovieID());
            SuccessWindow.display("SUCCESS!", String.format("Loaned %s to %s %s for %s",
                    movieTitle, client.getFirstName(), client.getLastName(), movie.getPricePerUnit()));
        } catch (Exception e) {
            SuccessWindow.display("FAIL!", e.getMessage());
        }
    }
}
