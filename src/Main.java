import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Driver for code generation
 */
public class Main {
    public static HashSet<Integer> greenBlocks, orangeBlocks;

    /**
     * generate IR, run that code, the output of that code is read,
     * generate HTML with appropriate colors, run HTML in firefox.
     */
    public static void main(String[] args) throws IOException {
        String fileName = "Example1";
        int runBlock = -1;
        if (args.length == 2) {
            fileName = "Example" + args[0];
            runBlock = Integer.parseInt(args[1]);
        }
        String fileNameExt = fileName + ".java";
        String intermediateFolder = "examples/intermediate/";
        String htmlFolder = "examples/html/";

        CharStream input = CharStreams.fromFileName("examples/" + fileNameExt);
        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();
        ParseTreeWalker walker = new ParseTreeWalker();

        if (runBlock == -1 || runBlock == 0) {
            TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
            walker.walk(new IntermediateCodeGenerator(rewriter, fileName), tree);
            // write file
            // TODO : write at run time not when program finishes
            FileWrite.getInstance().write(intermediateFolder + fileNameExt, rewriter.getText());
        }

        Runtime r = Runtime.getRuntime();

        if (runBlock == -1 || runBlock == 1) {
            // compile file
            r.exec(new String[]{"javac", "-d", intermediateFolder, "-cp", "src/", intermediateFolder + fileNameExt});
        }
        if (runBlock == -1 || runBlock == 2) {
            // run file
            r.exec(new String[]{"java", "-cp", intermediateFolder, fileName});
        }

        // run html in browser
        if (runBlock == -1 || runBlock == 3) {
            fileRead(fileName);
            TokenStreamRewriter rewriter1 = new TokenStreamRewriter(tokens);
            walker.walk(new HtmlGenerator(rewriter1, greenBlocks, orangeBlocks), tree);
            FileWrite.getInstance().write(htmlFolder + fileName + ".html", rewriter1.getText());
//            r.exec(new String[]{"firefox", htmlFolder + fileName + ".html"});
        }
    }

    /**
     * read content of output file of generated IR, fills greenBlocks, orangeBlocks hashsets.
     *
     * @param fileName input file name without extension.
     */
    public static void fileRead(String fileName) {
        greenBlocks = new HashSet<Integer>();
        orangeBlocks = new HashSet<Integer>();
        try {
            File myObj = new File("examples/blocks/" + fileName + "_blocks.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals("o")) break;
                greenBlocks.add(Integer.parseInt(data));
            }
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                orangeBlocks.add(Integer.parseInt(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
