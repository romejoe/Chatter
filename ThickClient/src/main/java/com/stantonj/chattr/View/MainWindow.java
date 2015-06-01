package com.stantonj.chattr.View;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

/**
 * Created by Joey on 5/30/15.
 */
public class MainWindow extends Scene {
    public MainWindow(Parent root) {
        super(root);
    }

    public MainWindow(Parent root, double width, double height) {
        super(root, width, height);
    }

    public MainWindow(Parent root, Paint fill) {
        super(root, fill);
    }

    public MainWindow(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public MainWindow(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    public MainWindow(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }
}
