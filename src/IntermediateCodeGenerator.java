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
            case "if","for","while","do" : {
                if (!ctx.statement(0).start.getText().equals("{")) {
                   rewriter.insertBefore(ctx.statement(0).start, "{ \n" + getBlock());
                   rewriter.insertAfter(ctx.statement(0).stop, "}");
                }
            }

            }
        }

    @Override
    public void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) {
        if (!ctx.blockStatement(0).start.getText().equals("{")) {
            rewriter.insertBefore(ctx.blockStatement(0).start, "{ \n" + getBlock());
            rewriter.insertAfter(ctx.blockStatement(ctx.blockStatement().size()-1).stop ,"\n }");
        }
    }


    @Override
    public void exitStatement(JavaParser.StatementContext ctx) {
        if (ctx.getChild(0).getText().equals("if") && ctx.statement(1) != null && !ctx.statement(1).start.getText().equals("{")){
                rewriter.insertBefore(ctx.statement(1).start, "{ \n" + getBlock());
                rewriter.insertAfter(ctx.statement(1).stop, "}");
        }
    }
}