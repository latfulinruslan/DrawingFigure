package main;

import figure.Mode;
import interfaces.IEditable;
import interfaces.ISelectable;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
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

    @FXML
    private ColorPicker fillingPicker;

    private Figure currentFigure;
    private FigureList figureStack = new FigureList();
    private FigureList redoStack = new FigureList();
    private Figure popedFigure;
    private FigureFactory figureCreator = FigureFactory.getInstance();
    private GraphicsContext gc;
    private Mode mode = Mode.getInstance();


    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setLineWidth(3);
        fillingPicker.setValue(Color.TRANSPARENT);
        figureCreator.setFigureType("Line");
    }

    public void figureButtonClicked(MouseEvent event){
        clearFigure();
        figureStack.drawAction(gc);
        mode.drawMode = true;
        figureCreator.setFigureType(((Button) event.getSource()).getId());
    }

    public void mousePressed(MouseEvent event){
        Point2D.Double  currentPoint = new Point2D.Double(event.getX(), event.getY());
        if (mode.drawMode) {
            currentFigure = figureCreator.getFigure();
            currentFigure.setFillingColor(fillingPicker.getValue());
            currentFigure.setFirstPoint(currentPoint);
        } else {
            clearFigure();
            figureStack.drawAction(gc);
            figureStack.select(gc, currentPoint);
        }
    }

    public void mouseDragged(MouseEvent event){
        Point2D.Double point = new Point2D.Double(event.getX(), event.getY());
        if (mode.drawMode) {
            clearFigure();
            figureStack.drawAction(gc);
            currentFigure.setSecondPoint(point);
            currentFigure.drawAction(gc);
        } else {
            if (mode.editingFigure != null) {
                if (Math.abs(mode.editingFigure.firstPoint.x - mode.selectPoint.x) < 15 && Math.abs(mode.editingFigure.firstPoint.y - mode.selectPoint.y) < 15) {
                    mode.editingFigure.setFirstPoint(point);
                } else if (Math.abs(mode.editingFigure.secondPoint.x - mode.selectPoint.x) < 15 && Math.abs(mode.editingFigure.secondPoint.y - mode.selectPoint.y) < 15) {
                    mode.editingFigure.setSecondPoint(point);
                } else {
                    ((IEditable) mode.editingFigure).move(point.x - mode.selectPoint.x, point.y - mode.selectPoint.y);
                }
                clearFigure();
                figureStack.drawAction(gc);
                ((ISelectable)mode.editingFigure).selectAction(gc);
                ((IEditable) mode.editingFigure).viewSelectedPoints(gc);
                mode.selectPoint = point;
            }
        }
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

    public void modeButtonClicked() {
        clearFigure();
        figureStack.drawAction(gc);
        mode.drawMode = false;
    }

    public void colorPickerWasHidden() {
        if (!mode.drawMode) {
            mode.editingFigure.setFillingColor(fillingPicker.getValue());
        }
    }
}
