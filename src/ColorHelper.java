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
            for (int i = 0; i < expr.length; i++)
                arr[blockNumber][i] &= expr[i];
    }

    public String getOrange() {
        StringBuilder str = new StringBuilder();
        int cnt = 0;
        // TODO : is the logic correct ? or just eval the first element
        for (int i = 0; i < arr.length; i++) {
            boolean tmp = false;
            if (arr[i] != null && arr[i].length > 1)
                tmp = arr[i][0];
//                for (boolean j : arr[i])
//                    tmp |= j;
            if (tmp) {
                str.append("\n").append(i);
                cnt++;
            }
        }
//        str.insert(0, "\n" + cnt);
        return str.toString();
    }
}
