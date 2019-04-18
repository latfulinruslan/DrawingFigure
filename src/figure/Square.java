package figure;

import javafx.scene.canvas.GraphicsContext;

public class Square extends Figure {

    @Override
    public void drawAction(GraphicsContext gc) {
        double x;
        double y;
        double dy;
        double dx;
        double delta;
        double deltaX;
        double deltaY;




        deltaX = (firstPoint.x + secondPoint.x) / 2;
        deltaY = (firstPoint.y + secondPoint.y) / 2;

        y = firstPoint.y < secondPoint.y ? firstPoint.y : secondPoint.y;
        x = firstPoint.x < secondPoint.x ? firstPoint.x : secondPoint.x;
//        if (firstPoint.y < secondPoint.y) {
//            y = firstPoint.y;
//        } else {
//            y = secondPoint.y;
//        }

//        if (firstPoint.x < secondPoint.x) {
//            x = firstPoint.x;
//        } else {
//            x = secondPoint.x;
//        }

        dx = deltaX - x;
        dy = deltaY - y;



        delta = (Math.hypot(dx, dy));

        delta = Math.sqrt( delta * delta);


        gc.strokePolygon(new double[]{deltaX - delta, deltaX + delta, deltaX + delta, deltaX - delta},
                        new double[]{deltaY - delta, deltaY - delta, deltaY + delta, deltaY + delta},
                        4);
    }
}
