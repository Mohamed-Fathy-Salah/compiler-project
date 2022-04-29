import org.antlr.v4.runtime.ParserRuleContext;
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

    private void injectHtml(ParserRuleContext ctx) {
        String color = greenBlocks.contains(blockNumber)? "MediumSeaGreen" : "tomato";
        injectHtml(ctx, color);
        blockNumber++;
    }

    private void injectHtml(ParserRuleContext ctx, String color) {
        rewriter.insertBefore(ctx.start, "<span style=\"background:" + color + "\">");
        rewriter.insertAfter(ctx.stop, "</span>");
    }

    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.start, "<pre style=\"background-color: MediumSeaGreen;font-size:175%\">");
        rewriter.insertAfter(ctx.stop, "</pre>");
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        String text = ctx.parent.parent.getChild(0).getText();
        if (!text.equals("if") && !text.equals("while") && !text.equals("do") && !text.equals("for"))
            injectHtml(ctx);
    }

    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        switch (ctx.getChild(0).getText()) {
            case "if", "while", "do" -> {
                if (orangeBlocks.contains(blockNumber))
                    injectHtml(ctx.parExpression(), "orange");
                injectHtml(ctx);
            }
            case "for" -> {
                if (orangeBlocks.contains(blockNumber))
                    injectHtml(ctx.forControl().expression(), "orange");
                injectHtml(ctx);
            }
        }
    }

    @Override
    public void exitStatement(JavaParser.StatementContext ctx) {
        // else statement
        if (ctx.getChild(0).getText().equals("if") && ctx.statement(1) != null) {
            rewriter.insertAfter(ctx.statement(0).stop, "<span style=\"background:" + (greenBlocks.contains(blockNumber) ? "MediumSeaGreen" : "tomato") + "\">");
            rewriter.insertAfter(ctx.stop, "</span>");
            blockNumber++;
        }
    }

    @Override
    public void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) {
        if (!ctx.blockStatement(0).start.getText().equals("{"))
            injectHtml(ctx);
    }
}