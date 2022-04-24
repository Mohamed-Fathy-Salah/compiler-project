import java.io.IOException;

public class Test {
    public static void main(String[] args) throws Exception{
        int [] x = {1,2,3,4,5,6,7,8};

        if(x == 0)
        	System.out.println(x[i + 1]);

        else{
            x++;
        	if (y > 5) {
                y--;
                if (z)
                    System.out.println(x[z]);
            }
            else
                System.out.print("hi");
        }

        for(int i=0;i<x.length;x++)
        for(int i=0;i<x.length;x++)
        for(int i=0;i<x.length;x++){
        	System.out.println(x[i]);
        }

        while(x == 0){
            if(y == 0 ){
                do{
                    x++;
                }while(true);
            }
        }

        try{
            x++;
            try{
                y--;
            }catch(Exception ex){
                z+= 10;
            }
        } catch (Exception ex){
            System.out.println("why");
        }finally {
           try{
              if(x==0)
                  x--;
           } finally {
               System.out.println("finally");
           }
        }
    }

    private int foo () {
        return 0;
    }
}
