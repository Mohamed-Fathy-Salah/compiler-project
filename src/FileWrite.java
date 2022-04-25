import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

class FileWrite {
    private final StringBuilder str;
    private static FileWrite single_instance = null;

    private FileWrite() { str = new StringBuilder(); }

    public static FileWrite Singleton() {
        if (single_instance == null) {
            single_instance = new FileWrite();
        }
        return single_instance;
    }

    public void append (String str) { this.str.append(str); }

    public void append (Integer i) { this.str.append(i.toString()); }

    public void write (String path) {
        write(path, this.str.toString());
    }

    public void write (String path, String str) {
        try {
            FileOutputStream outputFile = new FileOutputStream(path, true);
            BufferedOutputStream buffer = new BufferedOutputStream(outputFile);
            buffer.write(str.getBytes());
            buffer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}