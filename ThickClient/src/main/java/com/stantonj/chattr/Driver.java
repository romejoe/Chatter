package com.stantonj.chattr;

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

    public class htmlListCell extends ListCell<String> {

        WebView v = new WebView();

        private void adjustHeight() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Object result = v.getEngine().executeScript(
                                "$(document.getElementById('content')).height()");
                        if (result instanceof Integer) {
                            Integer i = (Integer) result;
                            double height = new Double(i);
                            height = height + 20;
                            v.setPrefHeight(height);
                        }
                    } catch (JSException e) {
                        // not important
                    }
                }
            });
        }

        public htmlListCell() {
            String jquerySrc = "";
            try {
                InputStream is = this.getClass().getResourceAsStream("/com/stantonj/chattr/jquery-2.1.4.min.js");


                jquerySrc = IOUtils.toString(is);
            } catch (IOException e) {
                e.printStackTrace();
            }

            v.getEngine().executeScript(jquerySrc);

            v.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {

                @Override
                public void changed(ObservableValue<? extends Worker.State> arg0, Worker.State oldState, Worker.State newState) {
                    if (newState == Worker.State.SUCCEEDED) {
                        adjustHeight();
                    }
                }
            });
        }


        @Override
        public void updateItem(String contents, boolean empty) {
            // don't omit this!!!
            super.updateItem(contents, empty);

            if (empty) {
                setGraphic(null);
            } else {
                v.getEngine().loadContent(contents);
                adjustHeight();
               /* Double height = Double.valueOf(v.getEngine().executeScript(
                                "var body = document.body,\n" +
                                        "    html = document.documentElement;\n" +
                                        "\n" +
                                        "Math.max( body.scrollHeight, body.offsetHeight, \n" +
                                        "                       html.clientHeight, html.scrollHeight, html.offsetHeight );"
                        ).toString().replace("px", "")
                );
                v.setPrefHeight(height + 10);*/
                // update controller and ui as necessary
                this.setGraphic(v);

            }
        }
    }

    public static final ObservableList<String> htmlContents =
            FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn = new Button();
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

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
