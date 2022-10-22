package com.example.paint;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Klasa obslugujaca okno dialogowe z instrukcją obsługi programu
 */
public class Instruction extends Dialog {
    Instruction(){

        setTitle("Instrukcja");
        setContentText("Z menu na pasku na górze należy wybrać pole Shapes i po rozwinięciu zaznaczyć pożądaną figurę.\n\n" +
                "Rysowanie koła:\nKliknięciem lewym przyciskiem myszy na ekran należy ustalić położenie środka koła, podczas poruszania kursorem pojawia się wizualizacja figury. Należy nacisnąć drugi raz, gdy osiągnie ona pożądany rozmiar.\n\n"+
                "Rysowanie prostokąta:\nKliknięciem lewym przyciskiem myszy na ekran należy ustalić położenie jednego z wierzchołków prostokąta, podczas poruszania kursorem pojawia się wizualizacja figury. Należy nacisnąć drugi raz, gdy osiągnie on pożądany rozmiar.\n\n"+
                "Rysowanie trójkąta:\nTrzema kniknięciami lewym przyciskiem myszy na ekran należy ustalić położenie wierzchołków trójkąta. W czasie tworzenia figury na bieżąco pojawia się jej wizualizacja.\n\n"+
                "Skalowanie utworzonych figur:\nPo najechaniu kursorem na wybraną figurę, należy poruszyć scrollem, aby zmienić jej rozmiar.\n\n"+
                "Pzesuwanie utworzonych figur:\nNaciskając wybraną figury lewym przyciskiem myszy, należy przesunąć ją w miejsce docelowe. Po puszczeniu przycisku zmieni ona swoje położenie.");

        ButtonType close = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

        getDialogPane().getButtonTypes().add(close);
        getDialogPane().setMinWidth(600);
        getDialogPane().setMinHeight(500);
    }
}
