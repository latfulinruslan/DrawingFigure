package figure;

public class FigureFactory {
    private static FigureFactory instance;
    public String figureType;
    private Figure currentFigure;

    public static FigureFactory getInstance(){
        if(instance == null){
            instance = new FigureFactory();
        }
        return instance;
    }

    public void setFigureType(String figureType) {
        this.figureType = figureType;
    }

    public  Figure getFigure(){
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

            case "CIRCLE":{
                figure = new Circle();
                break;
            }

            case "SQUARE":{
                figure = new Square();
                break;
            }

        }
        this.currentFigure = figure;
        return figure;

    }
}
