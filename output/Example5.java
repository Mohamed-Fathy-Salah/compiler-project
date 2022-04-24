public 
static 
void main (String[] args) throws Exception {
	FileWrite.getInstance().write(0);
	if (x == 0) {
		FileWrite.getInstance().write(1);
		System.out.println(x[i+1]);
	}
	else {
		FileWrite.getInstance().write(2);
		x++;
		if (y > 5) {
			FileWrite.getInstance().write(3);
			y--;
			if (z) {
				FileWrite.getInstance().write(4);
				System.out.println(x[z]);
			}
		}
		else {
			FileWrite.getInstance().write(5);
			System.out.print("hi");
		}
	}
	for (int i=0;i<x.length;x++) {
		FileWrite.getInstance().write(6);
		for (int i=0;i<x.length;x++) {
			FileWrite.getInstance().write(7);
			for (int i=0;i<x.length;x++) {
				FileWrite.getInstance().write(8);
				System.out.println(x[i]);
			}
		}
	}
	while ((x == 0)) {
		FileWrite.getInstance().write(9);
		if (y == 0 ) {
			FileWrite.getInstance().write(10);
			do {
				FileWrite.getInstance().write(11);
				x++;
			} while((true));
		}
	}
	try{x++;try{y--;}catch(Exceptionex){z+=10;}}catch(Exceptionex){System.out.println("why");}finally{try{if(x==0)x--;}finally{System.out.println("finally");}}
	x++;
	try{y--;}catch(Exceptionex){z+=10;}
	y--;
	z+=10;
	System.out.println("why");
	try{if(x==0)x--;}finally{System.out.println("finally");}
	if (x==0) {
		FileWrite.getInstance().write(12);
		x--;
	}
	System.out.println("finally");
	if (x) {
		FileWrite.getInstance().write(13);
		x++;
	}
	else {
		FileWrite.getInstance().write(14);
		y++;
	}
	FileWrite.Singleton().write("out/runblocks.txt");
}
private 
void foo () {
	FileWrite.getInstance().write(15);
}
