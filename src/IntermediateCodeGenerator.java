import org.antlr.v4.runtime.TokenStreamRewriter;

public class IntermediateCodeGenerator extends JavaParserBaseListener {
    private final TokenStreamRewriter rewriter;
    private int blockNumber = 0;

    public IntermediateCodeGenerator (TokenStreamRewriter rewriter) {this.rewriter = rewriter;}

    private String getBlock() {return "FileWrite.Singleton().append(" + blockNumber++ + ");\n";}

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        rewriter.insertBefore(ctx.blockStatement(0).start, getBlock());
    }

    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        switch (ctx.getChild(0).getText()) {
            case "if" : {
                if (!ctx.statement(0).start.getText().equals("{")) {
                   rewriter.insertBefore(ctx.statement(0).start, "{ \n" + getBlock());
                   rewriter.insertAfter(ctx.statement(0).stop, "}");
                }
                if (ctx.statement(1) != null) {
                    if (!ctx.statement(1).start.getText().equals("{")) {
                        rewriter.insertBefore(ctx.statement(1).start, "{ \n" + getBlock());
                        rewriter.insertAfter(ctx.statement(1).stop, "}");
                    }
                }
            }
            case "for" : {}
            case "while" : {}
            case "do" : {}
        }
    }
}