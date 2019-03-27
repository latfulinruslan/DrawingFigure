package latfulin.figure;

import javafx.scene.canvas.GraphicsContext;

public class Oval extends  Figure {
    @Override
    public void drawAction(GraphicsContext gc) {
        gc.strokeOval(firstPoint.x, firstPoint.y, Math.abs(secondPoint.x - firstPoint.x), Math.abs(secondPoint.y - firstPoint.y));
    }
}
