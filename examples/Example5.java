public class Example5 {
    public static void main(String[] args) {
        int [] x = {1,2,3,4,5,6,7,8};
        int y=0;
        int z =2;

        if(x[0] == 0){
        	System.out.println(x[1]);
        }
        else{
            x[0]=1;
        	if (y > 5) {
                y--;
                if (z >0)
                    System.out.println(x[z]);
            }
            else
                System.out.print("hi");
        }

        for(int i=0;i<x.length;y++)
        for(int j=0;i<x.length;y++)
        for(int k=0;i<x.length;y++){
        	System.out.println(x[i]);
        }

        while(y == 0){
            if(z == 0 ){
                do{
                    y++;
                }while(true);
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
              if(y==0)
                  z--;
           } finally {
               System.out.println("finally");
           }
        }
    }
}
