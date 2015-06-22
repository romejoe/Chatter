package com.stantonj.chattr.Experimental;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.control.ListCell;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Joey on 6/20/15.
 */
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

