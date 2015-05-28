package com.stantonj.Chattr.CLI;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.PasswordBox;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by Joey on 5/20/15.
 */
public class LoginWindow extends Window {
    TextBox UsernameInput;
    PasswordBox PasswordInput;
    public LoginWindow() {
        super("Login");
        UsernameInput = new TextBox();
        PasswordInput = new PasswordBox();
        addComponent(new Label("User name:"));
        addComponent(UsernameInput);
        addComponent(new Label("Password:"));
        addComponent(PasswordInput);
        addComponent(new Button("Login", () -> {
            System.out.println("Well hi there");
            System.out.println(UsernameInput.getText());
            System.out.println(PasswordInput.getText());
        }));
    }
}
