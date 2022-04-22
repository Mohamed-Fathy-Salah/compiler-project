import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

class FileWrite {
    private static FileWrite single_instance = null;
    PrintWriter out;


    private FileWrite() {
        String fileName = "output/example3.java";
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
        }catch (Exception ex){}
    }

    public static FileWrite getInstance() {
        if (single_instance == null) {
            single_instance = new FileWrite();
        }
        return single_instance;
    }

    public void write (String str) { out.println(str); }

    public void write (Integer x) { write(x.toString()); }
}
