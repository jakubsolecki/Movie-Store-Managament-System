package visualization;

import dbAccess.DbMediator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {

    private Stage window;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        DbMediator dbm = DbMediator.getInstance();

        window = stage;
        window.setTitle("Movie Store Management System");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label titleLabel = new Label("Movie Store Management System");
        titleLabel.setAlignment(Pos.CENTER);
        GridPane.setConstraints(titleLabel, 0, 0, 5, 1);

        Button addClientButton = new Button("Add Client");
        addClientButton.setOnAction(e -> AddClientWindow.display(dbm));
        GridPane.setConstraints(addClientButton, 1, 1);

        Button addMovieButton = new Button("Add Movie");
        GridPane.setConstraints(addMovieButton, 3, 1);

        Button addClientRemarksButton = new Button("Add Client Remarks");
        GridPane.setConstraints(addClientRemarksButton, 1, 2);

        Button loanMovieButton = new Button("Loan Movie");
        GridPane.setConstraints(loanMovieButton, 3, 2);

        Button returnMovieButton = new Button("Return Movie");
        GridPane.setConstraints(returnMovieButton, 1, 3);

        Button showClientInfoButton = new Button("Show Client Info");
        GridPane.setConstraints(showClientInfoButton, 3, 3);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> window.close());
        GridPane.setConstraints(exitButton, 2, 5);

        gridPane.getChildren().addAll(titleLabel, addClientButton, addMovieButton,
                addClientRemarksButton, loanMovieButton, returnMovieButton, showClientInfoButton, exitButton);

        Scene scene = new Scene(gridPane, 400, 200);
        window.setScene(scene);
        window.show();
    }
}
