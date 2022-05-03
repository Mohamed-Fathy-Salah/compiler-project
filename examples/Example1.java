public class Example1 {

    private int length, width;

    public static void main(String[] args) {
        int x = 0;
        if (x == 0) {
            x++;
        } else {
            if (x == 1) {
                x--;
            }
            x++;
        }

        int n = 6;
        for (int i = 0 ;i< n;i++) {
            green(i, 5, 11);
            orange(i + 6, 5, 11);
            red(5 , 6, 11);
        }

    }

    public static void green (int i, int j, int k) {
        if (i >= 5 || j <= 5 || k < 11) {
            System.out.println("one");
        } else if (i < 5 || ( j < 5 && k > 5) || k < 11) {
            System.out.println("two");
        } else {
            System.out.println("three");
        }
    }

    public static void orange (int i, int j, int k) {
        if (i > 5 || j <= 5 || k < 11) {
            System.out.println("one");
        } else if (i < 5 || ( j < 5 && k > 5) || k < 11) {
            System.out.println("two");
        } else {
            System.out.println("three");
        }
    }

    public static void red (int i, int j, int k) {
        if (i > 5 || j <= 5 || k < 11) {
            System.out.println("one");
        } else if (i < 5 || ( j < 5 && k > 5) || k < 11) {
            System.out.println("two");
        } else {
            System.out.println("three");
        }
    }

    public int getLength() {
        int x = 0, y = 5;
        if (x > 1)
            x++;
        else {
            y++;
        }

        if (x == 0) {
            x--;
            if (y == 1)
                y++;
        } else
            y++;

        if (x == 0) {
            if (y != 0)
                return 0;
        }

        switch (x) {
            case 5:
                System.out.println("5");
                break;
            default:
                System.out.println("default");
        }
        return 0;

    }
}
