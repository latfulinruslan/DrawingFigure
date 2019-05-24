package figure;

import interfaces.IEditable;
import interfaces.ISelectable;
import interfaces.ISerializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Line extends Figure implements ISelectable, IEditable, ISerializable {

    public Line() { className = "Line"; }

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
}
