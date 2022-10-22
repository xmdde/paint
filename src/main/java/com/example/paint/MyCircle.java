package com.example.paint;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Klasa Mobsługująca koła dziedzicząca po klasie Circle.
 */
public class MyCircle extends Circle {
    /**
     * Zmienna okreslajaca czy koło jest już skończone - czy ustalono współrzędne środka i promień.
     */
    public boolean isFinished = false;
    /**
     * Zmienne uzywane do sciagniecia wspolrzednych eventu MOUSE_PRESSED przy przesuwaniu.
     */
    public double clickedX;
    public double clickedY;

    /**
     * Konstruktor obiektów klasy MyCircle.
     * @param x współrzędna x środka koła
     * @param y współrzędna y środka koła
     * @param radius długość promienia koła
     */
    public MyCircle(double x, double y, double radius) {
        super(x, y, radius);
        setStroke(Color.BLACK);
        setFill(Color.IVORY);
        setOnMousePressed(new JFXCircleEventHandler());
        setOnMouseDragged(new JFXCircleEventHandler());
        setOnScroll(new JFXCircleScrollHandler());
    }
    /**
     * Sprawdza czy punkt (x,y) nalezy do koła.
     * @param x wspolrzedna x sprawdzanego punktu
     * @param y wspolrzedna y sprawdzanego punktu
     * @return zwraca wartosc bollean true, gdy punkt nalezy do koła, false, gdy nie
     */
    public boolean isHit(double x, double y) {
        return getBoundsInLocal().contains(x, y);
    }
    /**
     * Zwiększa wspolrzedna x środka koła o parametr x.
     * @param x wartosc, o ktora zwiekszana jest wspolrzedna x punktu
     */
    public void addX(double x) {
        this.setCenterX(this.getCenterX() + x);
    }
    /**
     * Zwiększa wspolrzedna y środka koła o parametr y.
     * @param y wartosc, o ktora zwiekszana jest wspolrzedna y punktu
     */
    public void addY(double y) {
        this.setCenterY(this.getCenterY() + y);
    }
    /**
     * Zwieksza dlugosc promienia o parametr r
     * @param r wartosc dodawana do dlugosci promienia
     */
    public void addRadius(double r) {
        this.setRadius(this.getRadius() + r);
    }
}

/**
 * Klasa obslugujaca skalowanie kola
 */
class JFXCircleScrollHandler implements EventHandler<ScrollEvent>{
    /**
     * Koło, na którym operuje.
     */
    MyCircle circle;
    /**
     * Funkcja skalująca koło.
     * @param e event użycia scrolla
     */
    private void doScale(ScrollEvent e) {
        double x = e.getX();
        double y = e.getY();

        if (circle.isHit(x, y)) {
            circle.addRadius(e.getDeltaY() * 0.2);
        }
    }

    @Override
    public void handle(ScrollEvent event) {

        circle = (MyCircle) event.getSource();

        if (event.getEventType() == ScrollEvent.SCROLL && circle.isFinished) {
            doScale(event);
        }
    }
}
/**
 * Klasa odpowiadajaca za przesuwanie kola.
 */
class JFXCircleEventHandler implements EventHandler<MouseEvent> {
    /**
     * Koło, na którym operuje
     */
    MyCircle circle;
    /**
     * Funkcja zmieniajaca polozenie koła
     * @param event event przeesuniecia myszka
     */
    private void doMove(MouseEvent event) {

        double dx = event.getX() - circle.clickedX;
        double dy = event.getY() - circle.clickedY;

        if (circle.isHit(circle.clickedX, circle.clickedY) && circle.isFinished) {
            circle.addX(dx);
            circle.addY(dy);
        }
        circle.clickedX += dx;
        circle.clickedY += dy;
    }

    @Override
    public void handle(MouseEvent event) {

        circle = (MyCircle) event.getSource();

        if (event.getEventType() == MouseEvent.MOUSE_PRESSED && circle.isFinished) {
            circle.clickedX = event.getX();
            circle.clickedY = event.getY();
        }

        if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            doMove(event);
        }
    }
}