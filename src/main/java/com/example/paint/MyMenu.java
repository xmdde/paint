package com.example.paint;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * Klasa obslugująca menu
 */
public class MyMenu extends MenuBar {
    /**
     * Tworzy pozycje widoczne na pasku menu
     */
    Menu menu1 = new Menu("Info");
    Menu menu2 = new Menu("Shapes");
    Menu menu3 = new Menu("Instruction");
    /**
     * Tworzy pozycje ukazujące się po rozwinięciu odpowiednich elementów menu
     */
    MenuItem circbtn = new MenuItem("Circle");
    MenuItem rectbtn = new MenuItem("Rectangle");
    MenuItem trbtn = new MenuItem("Triangle");
    MenuItem info = new MenuItem("Info");
    MenuItem instrukcja = new MenuItem("Instruction");

    /**
     * Konstruktor menu
     */
    MyMenu() {
        menu1.getItems().addAll(info);
        menu2.getItems().addAll(circbtn, rectbtn, trbtn);
        menu3.getItems().addAll(instrukcja);
    }

}
