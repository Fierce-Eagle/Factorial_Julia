package com.factorial.swirl_factorial;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import static com.factorial.swirl_factorial.JuliaFractal.DrawFractal;

public class Controller {

    private GraphicsContext graphicsContext;
    AnimationTimer loop;
    double a = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField cImText;

    @FXML
    private TextField cReText;

    @FXML
    private Canvas canvas;

    @FXML
    private Button drawButton;

    @FXML
    private TextField zoomText;

    @FXML
    private Button startAnimButton;

    @FXML
    private Button stopAnimButton;

    @FXML
    void animButtonClick(MouseEvent event) {
        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                a += 0.005;
                animation(a);
                if(a >= 2) {
                    a = 0;
                }
            }
        };
        loop.start();

    }

    void animation(double a) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        JuliaFractal.cIm = 0.7885 * Math.cos(a * Math.PI);
        JuliaFractal.cRe = 0.7885 * Math.sin(a * Math.PI);
        DrawFractal((int) canvas.getWidth(), (int) canvas.getHeight(), graphicsContext);
    }

    @FXML
    void stopAnimButtonClick(MouseEvent event) {
        loop.stop();
        cReText.clear();
        cImText.clear();
        cReText.appendText(String.valueOf(JuliaFractal.cRe));
        cImText.appendText(String.valueOf(JuliaFractal.cIm));
    }

    @FXML
    void drawButtonClick(MouseEvent event) {
        a = 0;
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        JuliaFractal.cRe = Double.parseDouble(cReText.getText());
        JuliaFractal.cIm = Double.parseDouble(cImText.getText());
        JuliaFractal.zoom = Double.parseDouble(zoomText.getText());
        DrawFractal((int) canvas.getWidth(), (int) canvas.getHeight(), graphicsContext);
    }

    @FXML
    void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(10);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //вызываем функцию рисования фрактала
        JuliaFractal.cRe = -0.70176;
        JuliaFractal.cIm = -0.3842;
        JuliaFractal.zoom = 1;
        cReText.appendText(String.valueOf(JuliaFractal.cRe));
        cImText.appendText(String.valueOf(JuliaFractal.cIm));
        zoomText.appendText(String.valueOf(JuliaFractal.zoom));

        DrawFractal((int) canvas.getWidth(), (int) canvas.getHeight(), graphicsContext);
    }

}
