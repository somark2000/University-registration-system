package lab3;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * this class creates a new window
 */
public class AlertBox {
    /**
     * @param title is the title of the scene
     * @param message is the text which will appear on the screen
     */
    public static void dispaly(String title,String message){
        Stage windoq=new Stage();
        windoq.initModality(Modality.APPLICATION_MODAL);
        windoq.setTitle(title);
        windoq.setMaxWidth(250);
        Text text=new Text(message);
        Button closeButton=new Button("OK");
        closeButton.setOnAction(actionEvent -> windoq.close()) ;
        VBox layout=new VBox(10);
        layout.getChildren().addAll(text,closeButton);
        Scene scene=new Scene(layout);
        windoq.setScene(scene);
        windoq.showAndWait();
    }
}
