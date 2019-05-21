package figure;

import interfaces.ISerializable;

import java.util.ArrayList;
import java.util.List;

public class FigureSerializer {

    public String serialize(ArrayList<Figure> figures) {
        StringBuilder builder = new StringBuilder();

        figures.forEach(figure -> {
            if (figure instanceof ISerializable) {
                builder.append(((ISerializable) figure).serialize());
            }
        });
        return builder.toString();
    }

    public FigureList deserialize(List<String> rows) {
        FigureList stack = new FigureList();
        FigureFactory figureCreator = FigureFactory.getInstance();
        for(String row : rows) {
            String[] cols = row.split(";");
            String className = cols[0];
            figureCreator.setFigureType(className);
            try {
                Figure shape = figureCreator.getFigure();
                if(shape instanceof ISerializable)
                    if (((ISerializable) shape).deserialize(row)) {
                        stack.push(shape);
                    }
            } catch (Exception e) {
                System.out.print("Corrupted string with name " + className);
            }
        }
        return stack;
    }
}
