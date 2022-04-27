import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "Example1";
        String fileNameExt = fileName + ".java";

        CharStream input = CharStreams.fromFileName("examples/" + fileNameExt);
        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);

        ParseTree tree = parser.compilationUnit();
        ParseTreeWalker walker = new ParseTreeWalker();

        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);

        walker.walk(new IntermediateCodeGenerator(rewriter, fileName), tree);

        // write file
        // TODO : write at run time not when program finishes
        FileWrite.Singleton().write("out/" + fileNameExt, rewriter.getText());

        // run file
        System.out.println( executeBashCommand("javac -d out/ -cp src/ out/" + fileNameExt));
        System.out.println( executeBashCommand("java -cp out " + fileName));
    }

    public static boolean executeBashCommand(String command) {
        boolean success = false;
        System.out.println("Executing BASH command:\n   " + command);
        Runtime r = Runtime.getRuntime();
        String[] commands = {"bash", "-c", command};
        try {
            Process p = r.exec(commands);
            success = true;
        } catch (Exception e) {
            System.err.println("Failed to execute bash with command: " + command);
            e.printStackTrace();
        }
        return success;
    }
}