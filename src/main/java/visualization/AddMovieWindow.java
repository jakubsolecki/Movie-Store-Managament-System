package visualization;

import dbAccess.DbMediator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddMovieWindow {
    public static void display(DbMediator dbMediator){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        GridPane.setConstraints(titleField, 0, 0);

        TextField directorField = new TextField();
        directorField.setPromptText("Director");
        GridPane.setConstraints(directorField, 2, 0);

        TextField scriptField = new TextField();
        scriptField.setPromptText("Script");
        GridPane.setConstraints(scriptField, 0, 1);

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");
        GridPane.setConstraints(descriptionField, 2, 1);

        TextField unitsInStockField = new TextField();
        unitsInStockField.setPromptText("Units In Stock");
        GridPane.setConstraints(unitsInStockField, 0, 2);

        TextField pricePerUnitField = new TextField();
        pricePerUnitField.setPromptText("Price Per Unit");
        GridPane.setConstraints(pricePerUnitField, 2, 2);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            addMovie(dbMediator, titleField.getText(), directorField.getText(), scriptField.getText(),
                    descriptionField.getText(), Integer.parseInt(unitsInStockField.getText()),
                            Double.parseDouble(pricePerUnitField.getText()));
            window.close();
        });
        GridPane.setConstraints(addButton, 0, 5);

        Button exitButton = new Button("Menu");
        exitButton.setOnAction(e -> window.close());
        GridPane.setConstraints(exitButton, 2, 5);


        gridPane.getChildren().addAll(titleField, directorField, scriptField, descriptionField, unitsInStockField, pricePerUnitField,
                addButton, exitButton);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void addMovie(DbMediator dbm, String title,
                                 String director,
                                 String script,
                                 String description,
                                 int unitsInStock,
                                 double pricePerUnit) {
        try {
            int movieID = dbm.addMovie(title, director, script, description, unitsInStock, pricePerUnit);
            SuccessWindow.display("SUCCESS!", "Added Movie. ID: " + movieID);
        } catch (Exception e) {
            SuccessWindow.display("FAIL!", e.getMessage());
        }
    }
}
