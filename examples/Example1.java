public class Example1{

private int length,width;

public int getLength(){
    int x = 0,y=5;

    if (x>1)
        x++;
    else {
        y++;
    }

    if (x<0) {
        x--;
        if(y==1)
            y++;
    }
    else
        y++;

    if(x==2) {
        if (y!=0)
            return 0;
    }

    switch (x) {
        case 5 :
            System.out.println("5");
            break;
        default:
            System.out.println("default");
    }
    return 0;

}
}
