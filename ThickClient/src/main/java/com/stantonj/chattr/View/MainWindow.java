package com.stantonj.chattr.View;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Joey on 5/30/15.
 */
public class MainWindow{

    public void start(Stage primaryStage) throws Exception {
        BorderPane rootPane = new BorderPane();
        ChatAreaGroupTab chatty = new ChatAreaGroupTab();
        ChattrTab tab1 = new ChattrTab();
        ChattrTab tab2 = new ChattrTab();

        tab1.setText("tab1");
        tab1.setContent(new Label("Hello from tab1"));

        tab2.setText("tab2");
        tab2.setContent(new Label("Hello from tab2"));

        chatty.getTabs().add(tab1);
        chatty.getTabs().add(tab2);

        rootPane.centerProperty().set(chatty);

        Scene scene = new Scene(rootPane);

        primaryStage.setScene(scene);
    }

}
