package latfulin.figure;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Figure {
    @Override
    public void drawAction(GraphicsContext gc) {
        gc.strokeRect(firstPoint.x, firstPoint.y, Math.abs(secondPoint.x - firstPoint.x), Math.abs(secondPoint.y - firstPoint.y));
    }
}
