package figure;

import interfaces.IEditable;
import interfaces.ISelectable;
import interfaces.ISerializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



import java.awt.geom.Point2D;

public class Triangle extends Figure implements ISelectable, IEditable, ISerializable {

    public Triangle() { className = "TRIANGLE"; }

    @Override
    public void drawAction(GraphicsContext gc) {
        double width = Math.abs(secondPoint.x - firstPoint.x);
        double height = Math.abs(secondPoint.y - firstPoint.y);

        if (firstPoint.y > secondPoint.y){
            height = -height;
        }

        gc.setStroke(borderColor);
        gc.strokePolygon(new double[]{firstPoint.x, firstPoint.x + width, firstPoint.x - width},
                         new double[]{firstPoint.y, firstPoint.y + height, firstPoint.y + height},
                        3);

        gc.setFill(fillingColor);
        gc.fillPolygon(new double[]{firstPoint.x, firstPoint.x + width, firstPoint.x - width},
                new double[]{firstPoint.y, firstPoint.y + height, firstPoint.y + height},
                3);
    }

    @Override
    public void selectAction(GraphicsContext gc) {
        double lineWidth = gc.getLineWidth();
        gc.setLineWidth(7);
        gc.setStroke(Color.GREENYELLOW);

        double width = Math.abs(secondPoint.x - firstPoint.x);
        double height = Math.abs(secondPoint.y - firstPoint.y);

        if (firstPoint.y > secondPoint.y){
            height = -height;
        }

        gc.strokePolygon(new double[]{firstPoint.x, firstPoint.x + width, firstPoint.x - width},
                new double[]{firstPoint.y, firstPoint.y + height, firstPoint.y + height},
                3);

        gc.setLineWidth(lineWidth);
    }

    @Override
    public boolean isSelected(Point2D.Double point) {
        return (((point.x <= firstPoint.x && point.x >= secondPoint.x) || (point.x >= firstPoint.x && point.x <= secondPoint.x)) &&
                ((point.y <= firstPoint.y && point.y >= secondPoint.y) || (point.y >= firstPoint.y && point.y <= secondPoint.y)));
    }
}
