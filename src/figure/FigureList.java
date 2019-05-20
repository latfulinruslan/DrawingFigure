package figure;

import interfaces.IDrawable;
import interfaces.ISelectable;
import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class FigureList implements IDrawable {
    private ArrayList<Figure> FigureArray;

    public FigureList() {
        FigureArray = new ArrayList<>();
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
                }
            }
        }
    }

    @Override
    public void drawAction(GraphicsContext gc) {
        for (int i = 0; i < FigureArray.size() ; i++) {
            FigureArray.get(i).drawAction(gc);
        }
    }
}
