if (x == 0) {
	FileWrite.getInstance().write(0);
	System.out.println(x[i+1]);
}
else {
	FileWrite.getInstance().write(1);
	x++;
	if (y > 5) {
		FileWrite.getInstance().write(2);
		y--;
		if (z) {
			FileWrite.getInstance().write(3);
			System.out.println(x[z]);
		}
	}
	else {
		FileWrite.getInstance().write(4);
		System.out.print("hi");
	}
}
for (int i=0;i<x.length;x++) {
	FileWrite.getInstance().write(5);
	for (int i=0;i<x.length;x++) {
		FileWrite.getInstance().write(6);
		for (int i=0;i<x.length;x++) {
			FileWrite.getInstance().write(7);
			System.out.println(x[i]);
		}
	}
}
while ((x == 0)) {
	FileWrite.getInstance().write(8);
	if (y == 0 ) {
		FileWrite.getInstance().write(9);
		do {
			FileWrite.getInstance().write(10);
			x++;
		} while((true));
	}
}
try {
	FileWrite.getInstance().write(11);
	x++;
	try {
		FileWrite.getInstance().write(12);
		y--;
	}
	catch (Exception ex) {
		FileWrite.getInstance().write(13);
		z+=10;
	}
}
catch (Exception ex) {
	FileWrite.getInstance().write(14);
	System.out.println("why");
}
finally {
	FileWrite.getInstance().write(15);
	try {
		FileWrite.getInstance().write(16);
		if (x==0) {
			FileWrite.getInstance().write(17);
			x--;
		}
	}
	finally {
		FileWrite.getInstance().write(18);
		System.out.println("finally");
	}
}
