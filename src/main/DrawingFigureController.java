package main;

import figure.*;
import interfaces.IEditable;
import interfaces.ISelectable;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class DrawingFigureController {

    @FXML
    private Canvas canvas;

    @FXML
    private Label coordinatesLabel;

    @FXML
    private ColorPicker fillingPicker;

    @FXML
    private HBox hBox;

    private Figure currentFigure;
    private FigureList figureStack = new FigureList();
    private FigureList redoStack = new FigureList();
    private Figure popedFigure;
    private GraphicsContext gc;
    private Mode mode = Mode.getInstance();
    private String currentFigureType;




    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setLineWidth(3);
        fillingPicker.setValue(Color.TRANSPARENT);
        currentFigureType = "Line";
        configureButtons();
    }

    public void figureButtonClicked(Event event) {
        clearFigure();
        figureStack.drawAction(gc);
        mode.drawMode = true;
        currentFigureType = ((Button)event.getSource()).getId();
        System.out.print(currentFigureType);
    }

    public void mousePressed(MouseEvent event){
        Point2D.Double  currentPoint = new Point2D.Double(event.getX(), event.getY());
        if (mode.drawMode) {
            currentFigure = FigureFactory.getFigure(currentFigureType);
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

    private void showWindow(Exception e) {
        Alert window = new Alert(Alert.AlertType.ERROR);
        window.setTitle("Error");
        window.setHeaderText("Error:");
        window.setContentText(e.getMessage());

        window.showAndWait();
    }

    public void saveButtonClicked() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Save");
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            String directoryPath = selectedDirectory.getPath();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            String format = currentDate + ".txt";

            String path = directoryPath + "/" + format;

            File newfile = new File(path);
            try {
                if (newfile.createNewFile()) {
                    ArrayList<Figure> figures = figureStack.getStack();

                    FigureSerializer serializer = new FigureSerializer();
                    String serializedFigures = serializer.serialize(figures);
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
                        bw.write(serializedFigures);
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            } catch (IOException e) {
                showWindow(new RuntimeException("Invalid path"));
            } catch (Exception e) {
                showWindow(new Exception(e));
            }
        }
    }

    public void openButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                List<String> rows = Files.readAllLines(Paths.get(file.getPath()));

                FigureSerializer serializer = new FigureSerializer();

                figureStack.popAll();
                redoStack.popAll();
                figureStack = serializer.deserialize(rows);

                if(figureStack.isEmpty()) {
                    showWindow(new RuntimeException("Invalid or empty file"));
                } else {
                    clearFigure();
                    figureStack.drawAction(gc);
                }
            } catch (IOException e) {
                showWindow(new RuntimeException("Invalid or empty file"));
            } catch (Exception e) {
                showWindow(new Exception(e));
            }
        }
    }

    private void configureButtons() {
        List<Class<Figure>> figureInGame = FigureFactory.getFigures();
        hBox.setSpacing(20);
        String pathToIcons = "file:/Users/latfulinruslan/Desktop/DrawingFigure/src/icons/";

        for (Class<Figure> figure:figureInGame) {
            Button button = new Button();
            String id = figure.getName();

            if (id.substring(7).equals("Figure")) {
                continue;
            }

            id = id.substring(7);
            String url = pathToIcons + id + ".png";
            Image image = new Image(url);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);

            button.setId(id);
            button.setGraphic(imageView);
            button.setOnAction(event -> figureButtonClicked(event));
            hBox.getChildren().add(button);
        }
    }

}