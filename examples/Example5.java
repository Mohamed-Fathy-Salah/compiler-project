import java.io.IOException;

public class Example5 {
    public static void main(String[] args) throws Exception{
        int [] x = {1,2,3,4,5,6,7,8};
        int y=0;
        int z=2;
        if(y == 0)
        	System.out.println(x[z + 1]);

        else{
            y++;
        	if (y > 5) {
                y--;
                if (z!=0)
                    System.out.println(x[z]);
            }
            else
                System.out.print("hi");
        }

        for(int i=0;i<x.length;i++)
        for(int j=0;j<x.length;j++)
        for(int k=0;k<x.length;k++){
        	System.out.println(x[i]);
        }
    y=0;
        z=2;
        while(y >= 0){
            if(y == 0 ){
                do{
                    z--;
                    y--;
                }while(z>0);
            }
        }

        try{
            y++;
            try{
                y--;
            }catch(Exception ex){
                z+= 10;
            }
        } catch (Exception ex){
            System.out.println("why");
        }finally {
           try{
              if(z==0)
                  y--;
           } finally {
               System.out.println("finally");
           }
        }
    }

    private int foo () {
        return 0;
    }
}
