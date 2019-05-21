package figure;

import java.awt.geom.Point2D;

public class Mode {
    public static Mode instance = null;
    private Mode() {drawMode = false;}
    public boolean drawMode;
    public Figure editingFigure = null;
    public Point2D.Double selectPoint = null;
    public static Mode getInstance() {
        if (instance == null) {
            instance = new Mode();
        }
        return instance;
    }

}
