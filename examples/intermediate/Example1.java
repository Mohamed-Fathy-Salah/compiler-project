public class Example1{

private int length,width;

    public static void main(String[] args) {
		FileWrite.Singleton().append(0);

    		FileWrite.Singleton().write("examples/blocks/Example1_blocks.txt");
}
public int getLength(){
		FileWrite.Singleton().append(1);

    int x = 0,y=5;
    if (x>1)
        {
		FileWrite.Singleton().append(2);
x++;}
    else {
		FileWrite.Singleton().append(3);

        y++;
    }

    if (x<0) {
		FileWrite.Singleton().append(4);

        x--;
        if(y==1)
            {
		FileWrite.Singleton().append(5);
y++;}
    }
    else
        {
		FileWrite.Singleton().append(6);
y++;}

    if(x==2) {
		FileWrite.Singleton().append(7);

        if (y!=0)
            {
		FileWrite.Singleton().append(8);
return 0;}
    }

    switch (x) {
        case 5 :
            { 

		FileWrite.Singleton().append(9);
System.out.println("5");
            break;
 }
        default:
            { 

		FileWrite.Singleton().append(10);
System.out.println("default");
 }
    }
    return 0;

}
}
