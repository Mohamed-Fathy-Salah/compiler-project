/**
 *
 */
public class ColorHelper {
    private static ColorHelper instance = null;
    private final static int ORANGE = 1;
    private final static int GREEN = 2;
    private int[] arr;

    /**
     *
     */
    private ColorHelper() {
        arr = new int[10];
    }

    /**
     *
     */
    public static ColorHelper getInstance() {
        if (instance == null)
            instance = new ColorHelper();
        return instance;
    }

    private int getType(boolean[] expr) {
        for (int i = 0;i<expr.length - 1;i++)
            if (expr[i])
                return ORANGE;
        return GREEN;
    }

    /**
     *
     */
    public void eval(int blockNumber, boolean[] expr) {
        if (arr.length <= blockNumber) {
            int[] newArr = new int[blockNumber * 2];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        if (getType(expr) == GREEN)
            arr[blockNumber] = GREEN;
        else if (arr[blockNumber] != GREEN)
            arr[blockNumber] = ORANGE;
    }

    /**
     *
     */
    public String getOrange() {
        StringBuilder str = new StringBuilder();
//        int cnt = 0;
        // TODO : is the logic correct ? or just eval the first element
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ORANGE) {
                str.append("\n").append(i);
//                cnt++;
            }
        }
//        str.insert(0, "\n" + cnt);
        return str.toString();
    }
}
