import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileWrite {
    private Path path;
    private static FileWrite single_instance = null;
 
    private FileWrite() {
        this.path = Paths.get("out.txt");
    }
 
    public static FileWrite Singleton() {
        if (single_instance == null) {
            single_instance = new FileWrite();
        }
        return single_instance;
    }

    public void write (String str) {
        try {
            Files.write(this.path, str.getBytes());
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
