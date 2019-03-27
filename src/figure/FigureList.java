package figure;

import javafx.scene.canvas.GraphicsContext;

public class FigureList implements IDrawable{
    private Figure[] FigureArray;
    private int pointer;

    public FigureList() {
        FigureArray = new Figure[100];
        pointer = 0;
    }

    public void push(Figure shape) {

        FigureArray[pointer++] = shape;
    }

    public void pop() {
        if (pointer > 0){
            pointer = pointer - 1;
        } else {
            pointer = 0;
        }
    }

    @Override
    public void drawAction(GraphicsContext gc) {
        for (int i = 0; i < pointer; i++) {
            FigureArray[i].drawAction(gc);
        }
    }
}
