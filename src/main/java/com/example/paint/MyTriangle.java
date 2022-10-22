package com.example.paint;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;

/**
 * Klasa obslugujaca trojkaty.
 */
public class MyTriangle extends Polygon{
    /**
     * Zmienna okreslająca czy trójkąt jest skończony
     */
    public boolean isFinished = false;
    /**
     * Zmienne uzywane do sciagniecia wspolrzednych eventu MOUSE_PRESSED przy przesuwaniu
     */
    public double clickedX;
    public double clickedY;

    /**
     * Konstruktor trójkątów
     * @param points tablica współrzędnych kolejnych wierzchołków trójkąta (x1, y1, x2, y2, x4, y3)
     */
    public MyTriangle(double[] points) {
        super(points);
        setStroke(Color.BLACK);
        setFill(Color.LAVENDER);
        setOnMousePressed(new MyTriangleEventHandler());
        setOnMouseDragged(new MyTriangleEventHandler());
        setOnScroll(new MyTriangleScrollHandler());
    }
    /**
     * Sprawdza czy punkt (x,y) nalezy do trojkata
     * @param x wspolrzedna x sprawdzanego punktu
     * @param y wspolrzedna y sprawdzanego punktu
     * @return zwraca wartosc bollean true gdy punkt nalezy do trojkata, false, gdy nie
     */
    public boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x,y);
    }
    /**
     * Zwiększa wspolrzedna x pierwszego wierzchołka trojkata o parametr x
     * @param x wartosc, o ktora zwiekszana jest wspolrzedna x punktu
     */
    public void addX1(double x) {
        getPoints().set(0, getPoints().get(0)+x);
    }
    /**
     * Zwiększa wspolrzedna y pierwszego wierzchołka trojkata o parametr y
     * @param y wartosc, o ktora zwiekszana jest wspolrzedna y punktu
     */
    public void addY1(double y) {
        getPoints().set(1, getPoints().get(1)+y);
    }
    /**
     * Zwiększa wspolrzedna x drugiego wierzchołka trojkata o parametr x
     * @param x wartosc, o ktora zwiekszana jest wspolrzedna x punktu
     */
    public void addX2(double x) {
        getPoints().set(2, getPoints().get(2)+x);
    }
    /**
     * Zwiększa wspolrzedna y drugiego wierzchołka trojkata o parametr y
     * @param y wartosc, o ktora zwiekszana jest wspolrzedna y punktu
     */
    public void addY2(double y) {
        getPoints().set(3, getPoints().get(3)+y);
    }
    /**
     * Zwiększa wspolrzedna x trzeciego wierzchołka trojkata o parametr x
     * @param x wartosc, o ktora zwiekszana jest wspolrzedna x punktu
     */
    public void addX3(double x) {
        getPoints().set(4, getPoints().get(4)+x);
    }
    /**
     * Zwiększa wspolrzedna y trzeciego wierzchołka trojkata o parametr y
     * @param y wartosc, o ktora zwiekszana jest wspolrzedna y punktu
     */
    public void addY3(double y) {
        getPoints().set(5, getPoints().get(5)+y);
    }

    /**
     * Skaluje odleglosc x1x2
     * @param x Stosunek odleglosci x1x2 po/przed
     */
    public void scaleX2(double x) {
        getPoints().set(2, getPoints().get(2)+(getPoints().get(2)-getPoints().get(0)) * x);
    }

    /**
     * Skaluje odleglosc y1y2
     * @param y Stosunek odleglosci y1y2 po/przed
     */
    public void scaleY2(double y) {
        getPoints().set(3, getPoints().get(3)+(getPoints().get(3)-getPoints().get(1)) * y);
    }

    /**
     * Skaluje odleglosc x1x3
     * @param x Stosunek odleglosci x1x3 po/przed
     */
    public void scaleX3(double x) {
        getPoints().set(4, getPoints().get(4) + ( getPoints().get(4)-getPoints().get(0)) * x);
    }

    /**
     * Skaluje odleglosc y1y3
     * @param y Stosunek odleglosci y1y3 po/przed
     */
    public void scaleY3(double y) {
        getPoints().set(5, getPoints().get(5) + (getPoints().get(5)-getPoints().get(1)) * y);
    }
}

/**
 * Klasa odpowiadajaca za przesuwania trojkata.
 */
class MyTriangleEventHandler implements EventHandler<MouseEvent>{
    /**
     * Trojkat, na ktorym operuje
     */
    MyTriangle triangle;
    /**
     * Funkcja zmieniajaca polozenie trojkata przy przesuwaniu po kliknięciu na niego.
     * @param event event przesuniecia myszka
     */
    private void doMove(MouseEvent event) {

        double dx = event.getX() - triangle.clickedX;
        double dy = event.getY() - triangle.clickedY;

        if (triangle.isHit(triangle.clickedX, triangle.clickedY)) {
            triangle.addX1(dx);
            triangle.addY1(dy);
            triangle.addX2(dx);
            triangle.addY2(dy);
            triangle.addX3(dx);
            triangle.addY3(dy);
        }
        /**
         * Zmiana wspolrzednych klikniecia
         */
        triangle.clickedX += dx;
        triangle.clickedY += dy;
    }

    @Override
    public void handle(MouseEvent event) {

        triangle = (MyTriangle) event.getSource();
        /**
         * Dla wcisnietego lewego przycisk myszy sciaga wspolrzedne kursora
         */
        if (event.getEventType()==MouseEvent.MOUSE_PRESSED && triangle.isFinished){
            triangle.clickedX = event.getX();
            triangle.clickedY = event.getY();
        }
        /**
         * Dla przesuniecia myszki z wcisnietym lewym przyciskiem wywoluje doMove
         */
        if (event.getEventType()==MouseEvent.MOUSE_DRAGGED && triangle.isFinished){
            doMove(event);
        }
    }
}
/**
 * Klasa odpowiadajaca za skalowanie trojkata po najechanie na niego kursorem.
 */
class MyTriangleScrollHandler implements EventHandler<ScrollEvent>{
    /**
     * Trojkat, na ktorym operuje
     */
    MyTriangle triangle;
    /**
     * Wspolczynnik, o jaki bedzie skalowana figura
     */
    double ratio;
    /**
     * Funkcja skalujaca trojkat po najechaniu na niego.
     * @param e event uzycia scrolla
     */
    private void doScale(ScrollEvent e) {

        double x = e.getX();
        double y = e.getY();

        if (triangle.isHit(x, y)) {

            if(e.getDeltaY() < 0) {
                ratio = -0.04;
            }
            else {
                ratio = 0.04;
            }

            triangle.scaleX2(ratio);
            triangle.scaleY2(ratio);
            triangle.scaleX3(ratio);
            triangle.scaleY3(ratio);
        }
    }
    @Override
    public void handle(ScrollEvent event) {

        triangle = (MyTriangle) event.getSource();

        if (event.getEventType()==ScrollEvent.SCROLL && triangle.isFinished) {
            doScale(event);
        }
    }

}
