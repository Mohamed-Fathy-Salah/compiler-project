import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "Example7";
        String fileNameExt = fileName + ".java";
        String outputFolder = "examples/intermediate/";

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
        FileWrite.Singleton().write(outputFolder + fileNameExt, rewriter.getText());

        // run file
        Runtime r= Runtime.getRuntime();
        r.exec(new String[]{"javac", "-d", outputFolder, "-cp", "src/", outputFolder + fileNameExt});
        r.exec(new String[]{"java", "-cp", outputFolder, fileName});
    }
}