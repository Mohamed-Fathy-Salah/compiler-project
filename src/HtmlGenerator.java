import org.antlr.v4.runtime.TokenStreamRewriter;

public class HtmlGenerator extends JavaParserBaseListener {
    private final TokenStreamRewriter rewriter;
    private int blockNumber = 0;
    private final String[] colors;

    public HtmlGenerator(TokenStreamRewriter rewriter, String[] colors) {
        this.rewriter = rewriter;
        this.colors = colors;
    }

    private String injectHtml() {
        // if (bockNumber in green)
        // if (blockNumber in red)
        // if (blockNumber in orange)
        return "<pre style=\"background-color:" + colors[blockNumber % 2] + "\">";
    }

    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.start, injectHtml());
        rewriter.insertAfter(ctx.stop, "</pre>");
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        String text = ctx.parent.parent.getChild(0).getText();
        if (!text.equals("if") && !text.equals("while") && !text.equals("do") && !text.equals("for")) {
            blockNumber++;
            rewriter.insertBefore(ctx.start, injectHtml());
            rewriter.insertAfter(ctx.stop, "</pre>");
        }
    }

    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        switch (ctx.getChild(0).getText()) {
            case "if", "for", "while", "do" -> {
                blockNumber++;
                rewriter.insertBefore(ctx.start, injectHtml());
                rewriter.insertAfter(ctx.stop, "</pre>");
            }
        }
    }

    @Override
    public void exitStatement(JavaParser.StatementContext ctx) {
        // else statement
        if (ctx.getChild(0).getText().equals("if") && ctx.statement(1) != null) {
            blockNumber++;
            rewriter.insertAfter(ctx.statement(0).stop, injectHtml());
            rewriter.insertAfter(ctx.stop, "</pre>");
        }
    }

    @Override
    public void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) {
        if (!ctx.blockStatement(0).start.getText().equals("{")) {
            blockNumber++;
            rewriter.insertBefore(ctx.start, injectHtml());
            rewriter.insertAfter(ctx.stop, "</pre>");
        }
    }
}