package latfulin.figure;

import javafx.scene.canvas.GraphicsContext;

public class Triangle extends Figure {
    @Override
    public void drawAction(GraphicsContext gc) {
        double width = Math.abs(secondPoint.x - firstPoint.x);
        double height = Math.abs(secondPoint.y - firstPoint.y);

        gc.strokePolygon(new double[]{firstPoint.x, firstPoint.x + width, firstPoint.x - width},
                         new double[]{firstPoint.y, firstPoint.y + height, firstPoint.y + height},
                        3);
    }
}
