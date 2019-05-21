package figure;

import interfaces.IEditable;
import interfaces.ISelectable;
import interfaces.ISerializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;

public class Line extends Figure implements ISelectable, IEditable, ISerializable {

    public Line() { className = "LINE"; }

    @Override
    public void drawAction(GraphicsContext gc) {
        gc.setStroke(borderColor);
        gc.strokeLine(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y);
    }

    @Override
    public void selectAction(GraphicsContext gc) {
        double lineWidth = gc.getLineWidth();
        gc.setLineWidth(7);
        gc.setStroke(Color.GREENYELLOW);
        gc.strokeLine(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y);
        gc.setLineWidth(lineWidth);
    }

    @Override
    public boolean isSelected(Point2D.Double point) {
        return (((point.x <= firstPoint.x && point.x >= secondPoint.x) || (point.x >= firstPoint.x && point.x <= secondPoint.x)) &&
                ((point.y <= firstPoint.y && point.y >= secondPoint.y) || (point.y >= firstPoint.y && point.y <= secondPoint.y)));
    }
}
