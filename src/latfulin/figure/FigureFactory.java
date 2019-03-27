package latfulin.figure;

import jdk.nashorn.internal.ir.ReturnNode;

public class FigureFactory {
    private static FigureFactory instance;

    public static FigureFactory getInstance(){
        if(instance == null){
            instance = new FigureFactory();
        }
        return instance;
    }

    public Figure getFigure(String figureType){
        Figure figure = null;

        switch (figureType.toUpperCase()){
            case "LINE":{
                figure = new Line();
                break;
            }

            case "OVAL":{
                figure = new Oval();
                break;
            }

            case "RECTANGLE":{
                figure = new Rectangle();
                break;
            }

            case "TRIANGLE":{
                figure = new Triangle();
                break;
            }

        }

        return figure;

    }
}
