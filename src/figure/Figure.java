package figure;

import interfaces.IDrawable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.geom.Point2D;

public abstract class Figure implements IDrawable {
    public Point2D.Double firstPoint;
    public Point2D.Double secondPoint;
    public Color borderColor = Color.BLACK, fillingColor;

    public void setFirstPoint(Point2D.Double firstPoint){
        this.firstPoint = firstPoint;
    }

    public void setSecondPoint(Point2D.Double secondPoint) {
        this.secondPoint = secondPoint;
    }

    public void setFillingColor(Color fillingColor) {
        this.fillingColor = fillingColor;
    }

    public void viewSelectedPoints(GraphicsContext gc) {
        gc.setFill(Color.CORAL);
        gc.fillOval(firstPoint.x - 6,firstPoint.y - 6,13,13);
        gc.fillOval(secondPoint.x - 6,secondPoint.y - 6,13,13);
    }

    public void move(Double deltaX, Double deltaY) {
        firstPoint.x += deltaX;
        firstPoint.y += deltaY;
        secondPoint.x += deltaX;
        secondPoint.y += deltaY;
    }


}
