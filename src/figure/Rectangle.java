package figure;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Figure {
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


        gc.strokeRect(x, y, Math.abs(secondPoint.x - firstPoint.x), Math.abs(secondPoint.y - firstPoint.y));
    }
}
