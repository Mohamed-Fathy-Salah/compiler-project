public class Example4{
    public static void main(String[] args) {
        int [] x = {1,2,3,4,5,6,7,8};
        
        int i=0;
        while(i<=x.length -1)
        {
        switch(x[i])
        {
         case 1:
         	{System.out.println(1);
         	break;}
        case 3:
         	{System.out.println(3);
         	break;} 	
 	default:
        	{System.out.println("error");
         	break;} 
        }
        i++;
        
       
        

    }
}}
