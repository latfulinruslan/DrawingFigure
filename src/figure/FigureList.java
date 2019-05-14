package figure;

import interfaces.IDrawable;
import javafx.scene.canvas.GraphicsContext;

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

    @Override
    public void drawAction(GraphicsContext gc) {
        for (int i = 0; i < FigureArray.size() ; i++) {
            FigureArray.get(i).drawAction(gc);
        }
    }
}
