public class Example8 {

    public static void main(String[] args) {
        int x = 10;
        int y = 5;
        int z = 2;
        while (x > 0){
            //green
            if (x > 5 || y == 5){
                //green
                System.out.println("green");
            }else if (y>5) {
                //red
                System.out.println("red");
            }

            if(x > 5 || y == 5 || z == 2){
                // orange
                System.out.println("orange");
            }
            x--;
        }
    }
}
