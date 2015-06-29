package com.stantonj.Chattr.CLI;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

import static com.googlecode.lanterna.terminal.Terminal.*;
import static com.googlecode.lanterna.terminal.Terminal.Color.*;

/**
 * Created by Joey on 5/18/15.
 */
public class Driver {

    public static void main(String[] args) throws InterruptedException {
        GUIScreen textGUI = TerminalFacade.createGUIScreen();
        if(textGUI == null) {
            System.err.println("Couldn't allocate a terminal!");
            return;
        }
        textGUI.getScreen().startScreen();

        LoginWindow win = new LoginWindow();
        textGUI.showWindow(win, GUIScreen.Position.CENTER);
        //Do GUI logic here
        Thread.sleep(100000);

        textGUI.getScreen().stopScreen();
    }
}
