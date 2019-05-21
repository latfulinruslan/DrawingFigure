package figure;

import interfaces.IDrawable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.geom.Point2D;

public abstract class Figure implements IDrawable {
    public Point2D.Double firstPoint;
    public Point2D.Double secondPoint;
    protected Color borderColor = Color.BLACK, fillingColor;
    protected String className;

    public void setFirstPoint(Point2D.Double firstPoint){
        this.firstPoint = firstPoint;
    }

    public void setSecondPoint(Point2D.Double secondPoint) {
        this.secondPoint = secondPoint;
    }

    public void setFillingColor(Color fillingColor) {
        this.fillingColor = fillingColor;
    }

    public void viewSelectedPoints(GraphicsContext gc) {
        gc.setFill(Color.CORAL);
        gc.fillOval(firstPoint.x - 6,firstPoint.y - 6,13,13);
        gc.fillOval(secondPoint.x - 6,secondPoint.y - 6,13,13);
    }

    public void move(Double deltaX, Double deltaY) {
        firstPoint.x += deltaX;
        firstPoint.y += deltaY;
        secondPoint.x += deltaX;
        secondPoint.y += deltaY;
    }

    public String serialize() {
        return className + ';' + firstPoint.x + ',' + firstPoint.y + ';' + secondPoint.x + ',' + secondPoint.y + ';' + fillingColor.toString() + '\n';
    }

    public boolean deserialize(String properties) {
        String[] cols = properties.split(";");
        try {
            className = cols[0];

            String[] firstPoints = cols[1].split(",");
            firstPoint = new Point2D.Double(Double.parseDouble(firstPoints[0]), Double.parseDouble(firstPoints[1]));

            String[] secondPoints = cols[2].split(",");
            secondPoint = new Point2D.Double(Double.parseDouble(secondPoints[0]), Double.parseDouble(secondPoints[1]));


            fillingColor = new Color(0.0,0.0,0.0,1);
            fillingColor = Color.web(cols[3]);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
