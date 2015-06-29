package com.stantonj.chattr;

import com.stantonj.chattr.View.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSException;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Created by Joey on 5/14/15.
 */
public class Driver extends Application{

     public static final ObservableList<String> htmlContents =
            FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> System.out.println("Hello World!"));

        StackPane root = new StackPane();
        //root.getChildren().add(btn);
        String s = "This is a <b>Sample</b> sentence";
        Label label = new Label(s);
        WebEngine eng = new WebEngine();

        htmlContents.addAll(
                "<html><body><div id='content'><b>1</b></div></body></html>",
                "<html><body><div id='content'><b>2</b></div></body></html>",
                "<html><body><div id='content'><b>3</b></div></body></html>",
                "<html><body><div id='content'><b>4</b></div></body></html>",
                "<html><body><div id='content'><b>5</b></div></body></html>",
                "<html><body><div id='content'><b>6</b></div></body></html>",
                "<html><body><div id='content'><b>7</b></div></body></html>",
                "<html><body><div id='content'><b>8</b></div></body></html>",
                "<html><body><div id='content'><b>9</b></div></body></html>",
                "<html><body><div id='content'><b>0</b></div></body></html>",
                "<html><body><div id='content'><b>a</b></div></body></html>",
                "<html><body><div id='content'><b>s</b></div></body></html>",
                "<html><body><div id='content'><b>f</b></div></body></html>",
                "<html><body><div id='content'><b>g</b></div></body></html>",
                "<html><body><div id='content'><b>h</b></div></body></html>",
                "<html><body><div id='content'><b>j</b></div></body></html>",
                "<html><body><div id='content'><b>k</b></div></body></html>",
                "<html><body><div id='content'><b>l</b></div></body></html>"
        );

        ListView<String> list = new ListView<>();
        list.setItems(htmlContents);
        list.setCellFactory(view->new htmlListCell());



        //WebView view = new WebView();
        //view.getEngine().loadContent(s);
        //root.getChildren().add(view);
        root.getChildren().add(list);

        final int[] i = {0};


        new Thread(()->{
            while(i[0] < 100) {
                htmlContents.add(String.format("<html><body><div id='content'><b>%s</b></div></body></html>", String.valueOf(i[0])));
                ++i[0];
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //list.scrollTo(htmlContents.size() - 1);

            }
        }).start();


*/

        primaryStage.setTitle("Chattr");
        MainWindow win = new MainWindow();

        win.start(primaryStage);

        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
