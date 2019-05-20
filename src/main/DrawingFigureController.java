package main;

import figure.Mode;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import figure.Figure;
import figure.FigureFactory;

import java.awt.geom.Point2D;

import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import figure.FigureList;

public class DrawingFigureController {

    @FXML
    private Canvas canvas;

    @FXML
    private Label coordinatesLabel;

    private Figure currentFigure;
    private FigureList figureStack = new FigureList();
    private FigureList redoStack = new FigureList();
    private Figure popedFigure;
    private FigureFactory figureCreator = FigureFactory.getInstance();
    private GraphicsContext gc;
    private Mode mode = Mode.getInstance();


    public void initialize(){
        gc = canvas.getGraphicsContext2D();
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
        figureCreator.setFigureType("Line");
    }


    public void figureButtonClicked(MouseEvent event){
        mode.drawMode = true;
        figureCreator.setFigureType(((Button) event.getSource()).getId());
    }

    public void mousePressed(MouseEvent event){
        Point2D.Double  currentPoint = new Point2D.Double(event.getX(), event.getY());
        if (mode.drawMode) {
            currentFigure = figureCreator.getFigure();
            currentFigure.setFirstPoint(currentPoint);
        } else {
            figureStack.select(gc, currentPoint);
        }

    }

    public void mouseDragged(MouseEvent event){
        currentFigure.setSecondPoint(new Point2D.Double(event.getX(), event.getY()));
        clearFigure();
        currentFigure.drawAction(gc);
        figureStack.drawAction(gc);

        coordinatesLabel.setText(event.getX() + ":" + event.getY());
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        if (mode.drawMode) {
            currentFigure.setSecondPoint(new Point2D.Double(mouseEvent.getX(), mouseEvent.getY()));
            currentFigure.drawAction(gc);
            figureStack.push(currentFigure);
            currentFigure = null;
            redoStack.popAll();
        }
    }

    private void clearFigure() {
        gc.clearRect(1, 1, canvas.getWidth() - 1.5, canvas.getHeight() - 1.5);
    }

    public void undoButtonClicked(){
        popedFigure = figureStack.pop();
        if (popedFigure != null){
            redoStack.push(popedFigure);
        }

        clearFigure();
        figureStack.drawAction(gc);
    }

    public void redoButtonClicked(){
        popedFigure = redoStack.pop();
        if (popedFigure != null){
            figureStack.push(popedFigure);
        }

        clearFigure();
        figureStack.drawAction(gc);
    }

    public void modeButtonClicked(){
        mode.drawMode = false;

        figureStack.drawAction(gc);
    }
}
