package visualization;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SuccessWindow {
    public static void display(String answer, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(200);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Label answerLabel = new Label(answer);
        answerLabel.setAlignment(Pos.CENTER);

        Label messageLabel = new Label(message);
        messageLabel.setAlignment(Pos.CENTER);

        Button exitButton = new Button("OK");
        exitButton.setOnAction(e -> window.close());
        exitButton.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(answerLabel, messageLabel, exitButton);

        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }
}
