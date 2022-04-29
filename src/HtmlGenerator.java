import org.antlr.v4.runtime.TokenStreamRewriter;

public class HtmlGenerator extends JavaParserBaseListener {
    private final TokenStreamRewriter rewriter;
    private int blockNumber = 0;
    private final String[] colors;

    public HtmlGenerator(TokenStreamRewriter rewriter) {
        this.rewriter = rewriter;
        colors = new String[]{"red", "green", "cyan", "blue", "yellow", "orange", "gray"};
    }

    private String getBlock() {
        // if (bockNumber in green)
        // if (blockNumber in red)
        // if (blockNumber in orange)
        return "<pre style=\"background-color:" + colors[blockNumber % colors.length] + "\">";
    }

    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.start, getBlock());
        rewriter.insertAfter(ctx.stop, "</pre>");
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        blockNumber++;
        rewriter.insertBefore(ctx.start, getBlock());
        rewriter.insertAfter(ctx.stop, "</pre>");
    }

    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        switch (ctx.getChild(0).getText()) {
            case "if", "for", "while", "do" -> {
                if (!ctx.statement(0).start.getText().equals("{")) {
                    blockNumber++;
                    rewriter.insertBefore(ctx.start, getBlock());
                    rewriter.insertAfter(ctx.stop, "</pre>");
                }
            }
        }
    }

    @Override
    public void exitStatement(JavaParser.StatementContext ctx) {
        // else statement
        if (ctx.getChild(0).getText().equals("if") && ctx.statement(1) != null && !ctx.statement(1).start.getText().equals("{")) {
            blockNumber++;
            rewriter.insertAfter(ctx.statement(0).stop, getBlock());
            rewriter.insertAfter(ctx.stop, "</pre>");
        }
    }

    @Override
    public void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) {
        if (!ctx.blockStatement(0).start.getText().equals("{")) {
            blockNumber++;
            rewriter.insertBefore(ctx.start, getBlock());
            rewriter.insertAfter(ctx.stop, "</pre>");
        }
    }
}