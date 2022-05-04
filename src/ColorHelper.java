/**
 * this class handles code coloring, after every statement use the function eval()
 * that will update the states of the blocks, then at the end of the main use the
 * function getOrange() that will return all orange blocks.
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
     * @return singleton instance of {@link ColorHelper}
     */
    public static ColorHelper getInstance() {
        if (instance == null)
            instance = new ColorHelper();
        return instance;
    }

    /**
     * checks if any element is true except the last element
     * then the type is orange else the type is green.
     * @param expr array of expressions of type boolean.
     * @return the type of the expression (orange, green).
     */
    private int getType(boolean[] expr) {
        for (int i = 0;i<expr.length - 1;i++)
            if (expr[i])
                return ORANGE;
        return GREEN;
    }

    /**
     * if expr is green then that block will be green always.
     * else if the block wasn't green then assign orange to it.
     * @param blockNumber the block number in the IR code.
     * @param expr array of expressions of type boolean.
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
     * @return string containing all orange blocks (block on each line).
     */
    public String getOrange() {
        StringBuilder str = new StringBuilder();
//        int cnt = 0;
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
