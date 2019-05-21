package interfaces;

import javafx.scene.canvas.GraphicsContext;

public interface IEditable {
    void viewSelectedPoints(GraphicsContext gc);
    void move(Double deltaX, Double deltaY);
}
