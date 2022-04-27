import java.io.IOException;

public class Example7 {
    int x =5;
    public static void main(String[] args) throws IOException {
		FileWrite.Singleton().append(0);

        int x =6;
        switch(x){
            case 5:
                { 

		FileWrite.Singleton().append(1);
x++;
                System.out.println(6);
                System.out.println(7);
                System.out.println(8);
                System.out.println(9);
                System.out.println(0);

                break;
 }

            case 6:
                { 

		FileWrite.Singleton().append(2);
x++;
                System.out.println(7);
                break;
 }

            case 8:{
		FileWrite.Singleton().append(3);

                x++;
                System.out.println(8);
                break;}

            default:{
		FileWrite.Singleton().append(4);

                System.out.println("not found");}

        }

        foo(5);

    		FileWrite.Singleton().write("examples/blocks/Example7_blocks.txt");
}
    public static void foo (int i) {
		FileWrite.Singleton().append(5);

        if ( i > 5) {
		FileWrite.Singleton().append(6);

            System.out.println("bigger");
            return ;
        }else {
		FileWrite.Singleton().append(7);

            System.out.println("smaller");
            int x = blah();
            if ( x == 0){
		FileWrite.Singleton().append(8);

                System.out.println("zero");
            }
        }
    }
    public static int blah () {
		FileWrite.Singleton().append(9);

        System.out.println("inside blah");
        return 0;
    }
    }
