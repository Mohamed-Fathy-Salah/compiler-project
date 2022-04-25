import java.io.IOException;

public class Example7 {
    int x =5;
    public static void main(String[] args) throws IOException {
        int x =6;
        switch(x){
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

            case 8:{
                x++;
                System.out.println(8);
                break;}

            default:{
                System.out.println("not found");}

        }


    }
    }
