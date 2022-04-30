public class ColorHelper {
    private static ColorHelper instance = null;
    private boolean[][] arr;

    private ColorHelper() {
        arr = new boolean[10][];
    }

    public static ColorHelper getInstance() {
        if (instance == null)
            instance = new ColorHelper();
        return instance;
    }

    public void eval(int blockNumber, boolean[] expr) {
        if (arr.length <= blockNumber) {
            boolean[][] newArr = new boolean[blockNumber * 2][];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        if (arr[blockNumber] == null)
            arr[blockNumber] = expr.clone();
        else
            for (int i = 0; i < expr.length; i++) {
                if (arr[blockNumber][i] ^ expr[i]) {
                    for (int j = i; j < expr.length; j++)
                        arr[blockNumber][j] = false;
                    return;
                }
            }
    }

    public String getOrange() {
        StringBuilder str = new StringBuilder();
//        int cnt = 0;
        // TODO : is the logic correct ? or just eval the first element
        for (int i = 0; i < arr.length; i++) {
            boolean tmp = false;
            if (arr[i] != null && arr[i].length > 1)
                for (int j = 0; j < arr[i].length - 1; j++)
                    tmp |= arr[i][j];
            if (tmp) {
                str.append("\n").append(i);
//                cnt++;
            }
        }
//        str.insert(0, "\n" + cnt);
        return str.toString();
    }
}
