public class Example6 {
    public static void main(String[] args) throws Exception{
    int x=5;
    int y=4;
    int z=3;

    int [] arr = {1,2,3,4,5,6,7,8,9,10};

    if (x<=5)
        System.out.println("yes");
    if(y==4)
        if (x==5)
            if(z==3)
                System.out.println("all");
            else 
                System.out.print("last else");

    for (int i=0;i<x;i++)
        for (int j=0;j<y;j++)
            for (int k=0;k<z;k++)
                System.out.print(arr[i]);

    while(x<10)
        x++;
    do
        y++;
    while (y<6);

    if(y<4)
        System.out.println("NO");
    else
        System.out.println("yes");
}}
