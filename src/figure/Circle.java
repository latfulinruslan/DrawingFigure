package figure;

import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Point2D;

public class Circle extends Figure {
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

        gc.strokeOval(pointOne.x, pointTwo.y, radius , radius);
    }
}
