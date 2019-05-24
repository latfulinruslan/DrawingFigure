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
        for(String row : rows) {
            String[] cols = row.split(";");
            String className = cols[0];

            try {
                Figure figure = FigureFactory.getFigure(className);
                if(figure instanceof ISerializable) {
                    if (((ISerializable) figure).deserialize(row)) {
                        stack.push(figure);
                    }
                }
            } catch (Exception e) {
                System.out.print("No such figure:");
            }
        }
        return stack;
    }
}
