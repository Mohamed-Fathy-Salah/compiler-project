import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
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
        // TODO : wait until file is created
        FileWrite.Singleton().write("out/" + fileNameExt, rewriter.getText());

        // run file
        // TODO : not working
        Process process = Runtime.getRuntime()
                .exec(new String[]{
                        "javac", "-d", "out", "-cp", "src", "out/" + fileNameExt, "&&",
                        "cd", "out", "&&",
                        "java", fileName
                });

        System.out.println(process.getErrorStream());
        System.out.println("done");
    }
}