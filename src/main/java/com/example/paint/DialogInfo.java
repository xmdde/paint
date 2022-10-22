package com.example.paint;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Klasa obslugujaca okno dialogowe z informacjami o programie
 */
public class DialogInfo extends Dialog {

    DialogInfo(){

        setTitle("Info");
        setContentText("Pain(t)\nProgram przeznaczony jest do rysowania podstawowych figur geometrycznych i edytowania ich.\n Autor: Justyna Ziemichód");

        ButtonType close = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

        getDialogPane().getButtonTypes().add(close);
        getDialogPane().setMinWidth(300);
        getDialogPane().setMinHeight(130);
    }
}
