package latfulin.figure;

import javafx.scene.canvas.GraphicsContext;

public class Line extends Figure {
    @Override
    public void drawAction(GraphicsContext gc) {
        gc.strokeLine(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y);
    }
}
