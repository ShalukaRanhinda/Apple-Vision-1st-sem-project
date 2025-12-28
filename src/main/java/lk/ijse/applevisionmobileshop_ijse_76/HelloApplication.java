package lk.ijse.applevisionmobileshop_ijse_76;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
     Parent load = fxmlLoader.load();
     Scene scene = new Scene(load);
     stage.setTitle("Apple Vision Mobile Shop");
     stage.setScene(scene);
     stage.setMinHeight(700);
     stage.setMinWidth(500);
     stage.show();
    }

    public static void main(String[] args){launch(args);}


}