package figure;

import interfaces.IEditable;
import interfaces.ISelectable;
import interfaces.ISerializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;

public class Circle extends Figure implements ISelectable, IEditable, ISerializable {

    public Circle() { className = "Circle"; }

    @Override
    public void drawAction(GraphicsContext gc) {

        Point2D.Double pointOne;
        Point2D.Double pointTwo;
        double dy;
        double dx;
        double radius;

        dx = secondPoint.x - firstPoint.x;
        dy = secondPoint.y - firstPoint.y;

        if ((firstPoint.x < secondPoint.x)) {
            pointOne = firstPoint;
        } else {
            pointOne = secondPoint;
        }

        if (firstPoint.y < secondPoint.y) {
            pointTwo = firstPoint;
        } else {
            pointTwo = secondPoint;
        }

        radius = Math.hypot(dx, dy);

        gc.setStroke(borderColor);
        gc.strokeOval(pointOne.x, pointTwo.y, radius , radius);

        gc.setFill(fillingColor);
        gc.fillOval(pointOne.x, pointTwo.y, radius, radius);
    }

    @Override
    public void selectAction(GraphicsContext gc) {
        double lineWidth = gc.getLineWidth();
        gc.setLineWidth(7);
        gc.setStroke(Color.GREENYELLOW);


        Point2D.Double pointOne;
        Point2D.Double pointTwo;
        double dy;
        double dx;
        double radius;

        dx = secondPoint.x - firstPoint.x;
        dy = secondPoint.y - firstPoint.y;

        if ((firstPoint.x < secondPoint.x)) {
            pointOne = firstPoint;
        } else {
            pointOne = secondPoint;
        }

        if (firstPoint.y < secondPoint.y) {
            pointTwo = firstPoint;
        } else {
            pointTwo = secondPoint;
        }

        radius = Math.hypot(dx, dy);

        gc.strokeOval(pointOne.x, pointTwo.y, radius , radius);

        gc.setLineWidth(lineWidth);
    }

}
