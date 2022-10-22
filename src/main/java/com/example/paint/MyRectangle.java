package com.example.paint;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Klasa obslugujaca prostokaty.
 */
public class MyRectangle extends Rectangle {
    /**
     * Zmienna okreslająca czy prostokat jest skończony.
     */
    public boolean isFinished = false;
    /**
     * Zmienne uzywane do sciagniecia wspolrzednych eventu MOUSE_PRESSED przy przesuwaniu.
     */
    public double clickedX;
    public double clickedY;
    /**
     * Zmienne pomocnicze umozliwiajace zachowanie punktu odniesienia przy wizualizacji figury.
     */
    public double tmpX;
    public double tmpY;

    /**
     * Konstruktor obiektów klasy prostokąt.
     * @param x współrzędna x lewego górego wierzchołka
     * @param y współrzędna y lewego górego wierzchołka
     * @param width szerokość prostokąta
     * @param height wysokość prostokąta
     */
    public MyRectangle(double x, double y, double width, double height){

        super(x, y, width, height);
        setStroke(Color.BLACK);
        setFill(Color.LIGHTBLUE);

        setOnMousePressed(new MyRectangleEventHandler());
        setOnMouseDragged(new MyRectangleEventHandler());
        setOnScroll(new MyRectangleScrollHandler());
    }
    /**
     * Sprawdza czy punkt (x,y) nalezy do prostokata
     * @param x wspolrzedna x sprawdzanego punktu
     * @param y wspolrzedna y sprawdzanego punktu
     * @return zwraca wartosc bollean prawda gdy punkt nalezy do prostokata, falsz gdy nie
     */
    public boolean isHit (double x, double y) {
        return getBoundsInLocal().contains(x,y);
    }

    /**
     * Zwiększa wspolrzedna x lewego górnego wierzchołka prostokąta o parametr x
     * @param x wartosc, o ktora zwiekszana jest wspolrzedna x punktu
     */
    public void addX (double x) {
        setX(getX()+x);
    }
    /**
     * Zwiększa wspolrzedna y lewego górnego wierzchołka prostokąta o parametr y
     * @param y wartosc, o ktora zwiekszana jest wspolrzedna y punktu
     */
    public void addY(double y) {
        setY(getY()+y);
    }
    /**
     * Zwieksza wartosc szerokosci prostokata o parametr w
     * @param w wartosc dodawana do szerokosci prostokata
     */
    public void addWidth (double w) {
        setWidth(getWidth()+w);
    }
    /**
     * Zwieksza wartosc wysokosci prostokata o parametr h
     * @param h Wartosc dodawana do wysokosci prostokata
     */
    public void addHeight (double h) {
        setHeight(getHeight()+h);
    }
}
/**
 * Klasa odpowiedzialna za przesuwanie prostokatow.
 */
class MyRectangleEventHandler implements EventHandler<MouseEvent> {
    /**
     * Prostokat, na ktorym operuje.
     */
    MyRectangle rectangle;
    /**
     * Funkcja zmieniajaca polozenie prostokata.
     * @param event event przeesuniecia myszka
     */
    private void doMove(MouseEvent event){

        double dx = event.getX() - rectangle.clickedX;
        double dy = event.getY() - rectangle.clickedY;

        if (rectangle.isHit(rectangle.clickedX, rectangle.clickedY) && rectangle.isFinished) {
            rectangle.addX(dx);
            rectangle.addY(dy);
        }

        rectangle.clickedX += dx;
        rectangle.clickedY += dy;
    }

    @Override
    public void handle(MouseEvent event) {

        rectangle = (MyRectangle) event.getSource();

        if (event.getEventType() == MouseEvent.MOUSE_PRESSED && rectangle.isFinished) {
            rectangle.clickedX = event.getX();
            rectangle.clickedY = event.getY();
        }

        if (event.getEventType() == MouseEvent.MOUSE_DRAGGED && rectangle.isFinished) {
            doMove(event);
        }
    }
}

/**
 * Klasa odpowiedzialna za skalowanie prostokatow.
 */
class MyRectangleScrollHandler implements EventHandler<ScrollEvent> {
    /**
     * Prostokat, na ktorym operuje
     */
    MyRectangle rectangle;

    /**
     * Funkcja skalująca prostokąt po najechaniu na niego kursorem.
     * @param e event użycia scrolla
     */
    private void doScale(ScrollEvent e) {

        if (rectangle.isHit(e.getX(), e.getY()) && rectangle.isFinished) {

            rectangle.addWidth(e.getDeltaY() * 0.2);
            rectangle.addHeight(e.getDeltaY() * 0.2);
        }
    }

    @Override
    public void handle(ScrollEvent event) {

        rectangle = (MyRectangle) event.getSource();

        if (event.getEventType() == ScrollEvent.SCROLL && rectangle.isFinished) {
            doScale(event);
        }
    }
}