package latfulin.figure;

import java.awt.geom.Point2D;

public abstract class Figure implements IDrawable {
    Point2D.Double firstPoint;
    Point2D.Double secondPoint;

    public void setFirstPoint(Point2D.Double firstPoint){
        this.firstPoint = firstPoint;
    }

    public void setSecondPoint(Point2D.Double secondPoint) {
        this.secondPoint = secondPoint;
    }
}
