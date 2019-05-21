package figure;

import interfaces.IEditable;
import interfaces.ISelectable;
import interfaces.ISerializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;

public class Rectangle extends Figure implements ISelectable, IEditable, ISerializable {

    public Rectangle() { className = "RECTANGLE"; }

    @Override
    public void drawAction(GraphicsContext gc) {
        double x;
        double y;

        if (firstPoint.y < secondPoint.y) {
            y = firstPoint.y;
        } else {
            y = secondPoint.y;
        }

        if (firstPoint.x < secondPoint.x) {
            x = firstPoint.x;
        } else {
            x = secondPoint.x;
        }

        gc.setStroke(borderColor);
        gc.strokeRect(x, y, Math.abs(secondPoint.x - firstPoint.x), Math.abs(secondPoint.y - firstPoint.y));

        gc.setFill(fillingColor);
        gc.fillRect(x, y, Math.abs(secondPoint.x - firstPoint.x), Math.abs(secondPoint.y - firstPoint.y));
    }

    @Override
    public void selectAction(GraphicsContext gc) {
        double lineWidth = gc.getLineWidth();
        gc.setLineWidth(7);
        gc.setStroke(Color.GREENYELLOW);

        double x;
        double y;

        if (firstPoint.y < secondPoint.y) {
            y = firstPoint.y;
        } else {
            y = secondPoint.y;
        }

        if (firstPoint.x < secondPoint.x) {
            x = firstPoint.x;
        } else {
            x = secondPoint.x;
        }

        gc.strokeRect(x, y, Math.abs(secondPoint.x - firstPoint.x), Math.abs(secondPoint.y - firstPoint.y));

        gc.setLineWidth(lineWidth);
    }

    @Override
    public boolean isSelected(Point2D.Double point) {
        return (((point.x <= firstPoint.x && point.x >= secondPoint.x) || (point.x >= firstPoint.x && point.x <= secondPoint.x)) &&
                ((point.y <= firstPoint.y && point.y >= secondPoint.y) || (point.y >= firstPoint.y && point.y <= secondPoint.y)));
    }
}
