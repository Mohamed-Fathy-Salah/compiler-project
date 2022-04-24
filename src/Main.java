import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "Example1.java";

        CharStream input = CharStreams.fromFileName("examples/" + fileName);
        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);

        ParseTree tree = parser.compilationUnit();
        ParseTreeWalker walker = new ParseTreeWalker();

        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);

        walker.walk(new IntermediateCodeGenerator(rewriter), tree);

        FileWrite.Singleton().write("out/" + fileName);

        System.out.println(rewriter.getText());

        System.out.println("done");
    }
}