package figure;

public class Mode {
    public static Mode instance = null;
    private Mode() {drawMode = false;}
    public boolean drawMode;
    public static Mode getInstance() {
        if (instance == null) {
            instance = new Mode();
        }
        return instance;
    }

}
