import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileWrite {
    private StringBuilder str;
    private static FileWrite single_instance = null;

    private FileWrite() { str = new StringBuilder(); }

    public static FileWrite Singleton() {
        if (single_instance == null) {
            single_instance = new FileWrite();
        }
        return single_instance;
    }

    public void append (String str) { this.str.append(str); }

    public void write (String path) {
        try {
            Files.write(Paths.get(path), str.toString().getBytes());
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}