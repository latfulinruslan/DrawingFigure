package figure;

import interfaces.IDrawable;
import interfaces.IEditable;
import interfaces.ISelectable;
import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class FigureList implements IDrawable {
    private ArrayList<Figure> FigureArray;
    private Mode mode = Mode.getInstance();
    //private int currentPoint;
    //private int maxSize;

    public FigureList() {
        FigureArray = new ArrayList<>();
        //currentPoint = 0;
        //maxSize = 0;
    }

    public void push(Figure figure) {

        FigureArray.add(figure);
    }

    public Figure pop() {
        int size = FigureArray.size();
        Figure returnFigure = null;
        if (size != 0){
            returnFigure = FigureArray.remove(size - 1);
        }
        return returnFigure;

    }

    public void popAll(){
        FigureArray.clear();
    }

    public void select(GraphicsContext gc, Point2D.Double point) {
        for (int i = FigureArray.size() - 1; i >= 0; i--) {
            Figure figure = FigureArray.get(i);
            if (figure instanceof ISelectable) {
                if (((ISelectable) figure).isSelected(point)) {
                    ((ISelectable) figure).selectAction(gc);
                    if (figure instanceof IEditable) {
                        figure.viewSelectedPoints(gc);
                        mode.editingFigure = figure;
                        mode.selectPoint = point;
                    } else {
                        mode.editingFigure = null;
                        mode.selectPoint = null;
                    }
                    return;
                }
            }
        }
        mode.editingFigure = null;
        mode.selectPoint = null;
    }

    @Override
    public void drawAction(GraphicsContext gc) {
        for (int i = 0; i < FigureArray.size() ; i++) {
            FigureArray.get(i).drawAction(gc);
        }
    }
}
