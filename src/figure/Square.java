package figure;

import interfaces.ISelectable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;

public class Square extends Figure implements ISelectable {

    @Override
    public void drawAction(GraphicsContext gc) {
        double x;
        double y;
        double dy;
        double dx;
        double delta;
        double deltaX;
        double deltaY;

        deltaX = (firstPoint.x + secondPoint.x) / 2;
        deltaY = (firstPoint.y + secondPoint.y) / 2;

        y = firstPoint.y < secondPoint.y ? firstPoint.y : secondPoint.y;
        x = firstPoint.x < secondPoint.x ? firstPoint.x : secondPoint.x;

        dx = deltaX - x;
        dy = deltaY - y;

        delta = (Math.hypot(dx, dy));
        delta = Math.sqrt( delta * delta);

        gc.strokePolygon(new double[]{deltaX - delta, deltaX + delta, deltaX + delta, deltaX - delta},
                        new double[]{deltaY - delta, deltaY - delta, deltaY + delta, deltaY + delta},
                        4);
    }

    @Override
    public void selectAction(GraphicsContext gc) {
        double lineWidth = gc.getLineWidth();
        gc.setLineWidth(7);
        gc.setStroke(Color.GREENYELLOW);

        double x;
        double y;
        double dy;
        double dx;
        double delta;
        double deltaX;
        double deltaY;

        deltaX = (firstPoint.x + secondPoint.x) / 2;
        deltaY = (firstPoint.y + secondPoint.y) / 2;

        y = firstPoint.y < secondPoint.y ? firstPoint.y : secondPoint.y;
        x = firstPoint.x < secondPoint.x ? firstPoint.x : secondPoint.x;

        dx = deltaX - x;
        dy = deltaY - y;

        delta = (Math.hypot(dx, dy));
        delta = Math.sqrt( delta * delta);

        gc.strokePolygon(new double[]{deltaX - delta, deltaX + delta, deltaX + delta, deltaX - delta},
                new double[]{deltaY - delta, deltaY - delta, deltaY + delta, deltaY + delta},
                4);

        gc.setLineWidth(lineWidth);
    }

    @Override
    public boolean isSelected(Point2D.Double point) {
        return (((point.x <= firstPoint.x && point.x >= secondPoint.x) || (point.x >= firstPoint.x && point.x <= secondPoint.x)) &&
                ((point.y <= firstPoint.y && point.y >= secondPoint.y) || (point.y >= firstPoint.y && point.y <= secondPoint.y)));
    }
}
