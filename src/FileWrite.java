import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

class FileWrite {
    private final StringBuilder str;
    private static FileWrite instance = null;

    private FileWrite() { str = new StringBuilder(); }

    public static FileWrite getInstance() {
        if (instance == null)
            instance = new FileWrite();
        return instance;
    }

    public void append (String str) { this.str.append(str); }

    public void append (Integer i) { this.str.append(i.toString()).append("\n"); }

    public void write (String path) {
        write(path, this.str.toString());
    }

    public void write (String path, String str) {
        try {
            FileOutputStream outputFile = new FileOutputStream(path, false);
            BufferedOutputStream buffer = new BufferedOutputStream(outputFile);
            buffer.write(str.getBytes());
            buffer.flush();
            buffer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}