import org.antlr.v4.runtime.TokenStreamRewriter;

import java.util.HashSet;

public class HtmlGenerator extends JavaParserBaseListener {
    private final TokenStreamRewriter rewriter;
    private int blockNumber = 0;
    private final HashSet<Integer> greenBlocks, orangeBlocks;

    public HtmlGenerator(TokenStreamRewriter rewriter, HashSet<Integer> greenBlocks, HashSet<Integer> orangeBlocks) {
        this.rewriter = rewriter;
        this.greenBlocks = greenBlocks;
        this.orangeBlocks = orangeBlocks;
    }

    private String injectHtml() {
        String color = "tomato";
        if (greenBlocks.contains(blockNumber))
            color = "MediumSeaGreen";
        return "<pre style=\"background-color:" + color + "\">";
    }

    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.start, "<pre style=\"background-color: MediumSeaGreen;font-size:175%\">");
        rewriter.insertAfter(ctx.stop, "</pre>");
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        String text = ctx.parent.parent.getChild(0).getText();
        if (!text.equals("if") && !text.equals("while") && !text.equals("do") && !text.equals("for")) {
            rewriter.insertBefore(ctx.start, injectHtml());
            rewriter.insertAfter(ctx.stop, "</pre>");
            blockNumber++;
        }
    }

    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        switch (ctx.getChild(0).getText()) {
            case "if", "while", "do" -> {
                if (orangeBlocks.contains(blockNumber)) {
                    rewriter.insertBefore(ctx.parExpression().start, "<pre style=\"background-color:orange\">");
                    rewriter.insertAfter(ctx.parExpression().stop, "</pre>");
                }
                rewriter.insertBefore(ctx.start, injectHtml());
                rewriter.insertAfter(ctx.stop, "</pre>");
                blockNumber++;
            }
            case "for" -> {
                if (orangeBlocks.contains(blockNumber)) {
                    rewriter.insertBefore(ctx.forControl().expression().start, "<pre style=\"background-color:orange\">");
                    rewriter.insertAfter(ctx.forControl().expression().stop, "</pre>");
                }
                rewriter.insertBefore(ctx.start, injectHtml());
                rewriter.insertAfter(ctx.stop, "</pre>");
                blockNumber++;
            }
        }
    }

    @Override
    public void exitStatement(JavaParser.StatementContext ctx) {
        // else statement
        if (ctx.getChild(0).getText().equals("if") && ctx.statement(1) != null) {
            rewriter.insertAfter(ctx.statement(0).stop, injectHtml());
            rewriter.insertAfter(ctx.stop, "</pre>");
            blockNumber++;
        }
    }

    @Override
    public void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) {
        if (!ctx.blockStatement(0).start.getText().equals("{")) {
            rewriter.insertBefore(ctx.start, injectHtml());
            rewriter.insertAfter(ctx.stop, "</pre>");
            blockNumber++;
        }
    }
}