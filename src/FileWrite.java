import java.io.FileOutputStream;

/**
 * this class is used to write to a file.
 * It is responsible for creating an object while making sure that only single object gets created.
 */
class FileWrite {
    private final StringBuilder str;
    private static FileWrite instance = null;

    private FileWrite() { str = new StringBuilder(); }

    /**
     * Checks if already there is an instance will be returned otherwise a new one will be created
     * to make sure that only one exists.
     * @return instance of class
     */
    public static FileWrite getInstance() {
        if (instance == null)
            instance = new FileWrite();
        return instance;
    }

    /**
     * Appends a substring to the string to be written in the file
     * @param str the string to be appended
     */
    public void append (String str) { this.str.append(str); }

    /**
     * covert integer to string and Append it to the string to be written in the file
     * @param i the integer to be appended
     */
    public void append (Integer i) { this.str.append(i.toString()).append("\n"); }

    /**
     * this method writes the string to the specified file.
     * @param path the file path
     */
    public void write (String path) {
        write(path, this.str.toString());
    }

    /**
     * this method writes the string to the specified file.
     * @param path the file path
     * @param str the string to be written
     */
    public void write (String path, String str) {
        try {
            FileOutputStream outputFile = new FileOutputStream(path, false);
            outputFile.write(str.getBytes());
            outputFile.flush();
            outputFile.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
