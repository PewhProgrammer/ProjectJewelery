package com.Gui;/**
 * Created by Thinh-Laptop on 13.09.2016.
 */

import com.Head.GameControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GraphicalGUI extends Application {

    public static GameControl ctrl ;
    public static Controller mController ;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setCtrl(GameControl ctrl1){
        ctrl = ctrl1;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        AnchorPane root1 = loader.load(getClass().getResource("sample.fxml").openStream());
        Controller cont = loader.getController();
        mController = cont ;

        Parameters parameters = getParameters() ;

        Scene scene = new Scene(root1,  800, 600);
        //scene.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());

        primaryStage.setTitle("Project Jewelry");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene) ;
        primaryStage.show();
        cont.initialize(primaryStage);
    }

    public static Controller getController(){
        return mController ;
    }
}
