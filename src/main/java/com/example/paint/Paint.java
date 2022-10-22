package com.example.paint;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Scene;
/**
 * Klasa główna programu obsługującego proste figury geometryczne.
 * @version 1.0
 * @author Justyna Ziemichód
 */
public class Paint extends Application {
    /**
     * Funkcja rozpoczynajaca prace programu.
     * @param stage główne okno programu
     */
    @Override
    public void start(final Stage stage) {

        BorderPane pane = new BorderPane();
        Pane area = new Pane();
        MyMenu menuBar = new MyMenu();

        pane.setTop(menuBar);
        menuBar.getMenus().addAll(menuBar.menu1, menuBar.menu2, menuBar.menu3);
        pane.setCenter(area);

        Scene scene = new Scene(pane, 1200, 850);
        DialogInfo dialoginfo = new DialogInfo();
        Instruction instruction = new Instruction();

        menuBar.info.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                dialoginfo.showAndWait();
            }
        });

        menuBar.instrukcja.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                instruction.showAndWait();
                }
        });

        menuBar.rectbtn.setOnAction(new EventHandler<>() {
            @Override

            public void handle(ActionEvent actionEvent) {

                MyRectangle rectangle = new MyRectangle(0, 0, 0, 0);

                area.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        if (rectangle.getX() == 0) {

                            rectangle.setX(event.getX());
                            rectangle.setY(event.getY());
                            // Ustala wartosci pomocnicze x, y aby zachowac punkt odniesienia przy wizualizacji w czasie rzeczywistym
                            rectangle.tmpX = event.getX();
                            rectangle.tmpY = event.getY();
                        }
                        //Drugie kliknięcie
                        else if (rectangle.getX() != 0 && !rectangle.isFinished) {
                            rectangle.isFinished = true;
                        }
                    }
                });

                area.setOnMouseMoved(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        //Po pierwszym kliknięciu tworzy wizualizacje prostokata w czasie rzeczywistym podczas poruszania kursorem
                        if (rectangle.getX() != 0 && !rectangle.isFinished) {
                            rectangle.setX(Math.min(rectangle.tmpX, event.getX()));
                            rectangle.setY(Math.min(rectangle.tmpY, event.getY()));
                            rectangle.setWidth(Math.abs(rectangle.tmpX - event.getX()));
                            rectangle.setHeight(Math.abs(rectangle.tmpY - event.getY()));
                        }
                    }
                });
                area.getChildren().add(rectangle);
            }
        });

        menuBar.circbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                MyCircle circle = new MyCircle(0, 0, 0);

                area.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        if (circle.getCenterX() == 0) {
                            circle.setCenterX(event.getX());
                            circle.setCenterY(event.getY());
                        }

                        else if (circle.getCenterX() != 0 && !circle.isFinished) {
                            circle.isFinished = true;
                        }
                    }
                });

                area.setOnMouseMoved(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        if (circle.getCenterX() != 0 && !circle.isFinished) {
                            circle.setRadius(Math.sqrt(Math.pow(circle.getCenterX() - event.getX(), 2) + Math.pow(circle.getCenterY() - event.getY(), 2)));
                        }
                    }
                });

                area.getChildren().add(circle);
            }
        });

        menuBar.trbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                MyTriangle triangle = new MyTriangle(new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
                Line line = new Line();

                area.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        // pierwsze kliknięcie
                        if (triangle.getPoints().get(0) == 0.0) {
                            double x = event.getX();
                            double y = event.getY();
                            line.setStartX(x);
                            line.setStartY(y);
                            line.setEndX(x);
                            line.setEndY(y);
                            triangle.getPoints().set(0, event.getX());
                            triangle.getPoints().set(1, event.getY());
                            area.getChildren().add(line);
                        }
                        // drugie kliknięcie
                        else if (triangle.getPoints().get(0) != 0.0 && triangle.getPoints().get(2) == 0.0) {
                            triangle.getPoints().set(2, event.getX());
                            triangle.getPoints().set(3, event.getY());
                            triangle.getPoints().set(4, event.getX());
                            triangle.getPoints().set(5, event.getY());
                            line.setStartX(0.0);
                            area.getChildren().remove(line);
                            area.getChildren().add(triangle);
                        }
                        // trzecie klikniecie
                        else if (triangle.getPoints().get(0) != 0.0 && triangle.getPoints().get(2) != 0.0 && line.getStartX() == 0.0) {
                            line.setStartX(1.0);
                            triangle.isFinished = true;
                        }
                    }
                });

                area.setOnMouseMoved(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        if (line.getStartX() != 0.0) {
                            line.setEndX(event.getX());
                            line.setEndY(event.getY());
                        }
                        //Sprawdza czy nastąpiło już drugie kliknięcie, jeśli tak, tworzy wizualizacje trójkąta w czasie rzeczywistym
                        else if (line.getStartX() == 0.0 && triangle.getPoints().get(2) != 0.0) {
                            triangle.getPoints().set(4, event.getX());
                            triangle.getPoints().set(5, event.getY());
                        }
                    }
                });
            }
        });

        stage.setTitle("Pain(t)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}