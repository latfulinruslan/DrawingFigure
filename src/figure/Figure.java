package figure;

import interfaces.IDrawable;

import javafx.scene.paint.Color;
import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Figure implements IDrawable {
    Point2D.Double firstPoint;
    Point2D.Double secondPoint;
    public Color borderColor;

    public void setFirstPoint(Point2D.Double firstPoint){
        this.firstPoint = firstPoint;
    }

    public void setSecondPoint(Point2D.Double secondPoint) {
        this.secondPoint = secondPoint;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }


}
