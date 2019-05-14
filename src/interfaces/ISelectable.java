package interfaces;

import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Point2D;

public interface ISelectable {
    void selectAction(GraphicsContext gc);
    boolean isSelected(Point2D.Double point);
}
