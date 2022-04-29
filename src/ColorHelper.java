import java.util.ArrayList;

public class ColorHelper {
    private static ColorHelper instance = null;
    private ArrayList<ArrayList<Boolean>> arr;

    private ColorHelper() {
    }

    public static ColorHelper getInstance() {
        if (instance == null)
            instance = new ColorHelper();
        return instance;
    }

    public void eval(int blockNumber, boolean[] expr) {
    }

    public String getOrange() {
        StringBuilder str = new StringBuilder();

        return str.toString();
    }
}
