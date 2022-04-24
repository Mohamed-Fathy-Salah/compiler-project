import org.antlr.v4.runtime.TokenStreamRewriter;

public class IntermediateCodeGenerator extends JavaParserBaseListener {
    private final TokenStreamRewriter rewriter;
    private int blockNumber = 0;

    public IntermediateCodeGenerator (TokenStreamRewriter rewriter) {this.rewriter = rewriter;}

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        rewriter.insertBefore(ctx.blockStatement(0).start, "FileWrite.Singleton().append(" + blockNumber++ + ");\n");
    }
}