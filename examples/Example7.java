import java.io.IOException;

public class Example7 {
    int x = 5;

    public static void main(String[] args) throws IOException {
        int x = 6;
        switch (x) {
            case 5:
                x++;
                System.out.println(6);
                System.out.println(7);
                System.out.println(8);
                System.out.println(9);
                System.out.println(0);

                break;

            case 6:
                x++;
                System.out.println(7);
                break;

            case 8: {
                x++;
                System.out.println(8);
                break;
            }

            default: {
                System.out.println("not found");
            }

        }

        foo(5);

        for (int i = 0 ;i<5 || (i < 5 && i == 0);i++)
            System.out.println("blah");

        int y = 4;
        int z = 2;
        x = 5;
        if (y == 4 || y == 3 || y == 2)
            if (x == 5 || x == 2)
                if (z == 3) {
                    System.out.println("all");
                }
                else
                    System.out.print("last else");

    }

    public static void foo(int i) {
        if (i > 5) {
            System.out.println("bigger");
            return;
        } else {
            System.out.println("smaller");
            int x = blah();
            if (x == 0) {
                System.out.println("zero");
            }
        }
    }

    public static int blah() {
        System.out.println("inside blah");
        return 0;
    }
}
