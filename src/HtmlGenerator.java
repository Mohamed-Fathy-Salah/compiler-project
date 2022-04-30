import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStreamRewriter;

import java.util.HashSet;
/**
 * This class extends {@link JavaParserBaseListener},
 * Used to inject Html tags into the .html output file for clear visualisation of file execution
 */
public class HtmlGenerator extends JavaParserBaseListener {
    private final TokenStreamRewriter rewriter;
    private int blockNumber = 0;
    private final HashSet<Integer> greenBlocks, orangeBlocks;

    /**
     *
     * This is the class constructor
     *
     * @param rewriter
     * @param greenBlocks : Hashset contains the indices of the blocks that will be colored with green
     * @param orangeBlocks : Hashset contains the indices of the blocks that will be colored with orange
     */

    public HtmlGenerator(TokenStreamRewriter rewriter, HashSet<Integer> greenBlocks, HashSet<Integer> orangeBlocks) {
        this.rewriter = rewriter;
        this.greenBlocks = greenBlocks;
        this.orangeBlocks = orangeBlocks;
    }

    /**
     * this method decides the current block color and call injectHtml with the right color
     *
     * @param ctx
     *
     */
    private void injectHtml(ParserRuleContext ctx) {
        String color = greenBlocks.contains(blockNumber)? "green" : "red";
        injectHtml(ctx, color);
        blockNumber++;
    }

    /**
     * colors the block by injecting the html tags before and after the block with the right color and style
     *
     * @param ctx
     * @param color : the color that the block will take depending on its state in execution
     */
    private void injectHtml(ParserRuleContext ctx, String color) {
        rewriter.insertBefore(ctx.start, "<div style=\"background-color: " + color + "; border: 1px dashed white;\" >");
        rewriter.insertAfter(ctx.stop, "</div>");
    }

    /**
     * this method responsible for giving the background color for the whole page
     * @param ctx
     */
    @Override
    public void enterCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.start, "<div style=\"background-color:green; width: fit-content; color: white;\"><pre>");
        rewriter.insertAfter(ctx.stop, "</pre></div>");
    }

    /**
     * this method inject the html for any block but if, while, do and for
     * other functions will take care of those blocks
     * @param ctx
     */
    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        String text = ctx.parent.parent.getChild(0).getText();
        if (!text.equals("if") && !text.equals("while") && !text.equals("do") && !text.equals("for"))
            injectHtml(ctx);
    }

    /**
     * this method inject html for if, while, do and for
     * @param ctx
     */
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

    /**
     * this method handle the else statement
     * @param ctx
     */
    @Override
    public void exitStatement(JavaParser.StatementContext ctx) {
        // else statement
        if (ctx.getChild(0).getText().equals("if") && ctx.statement(1) != null) {
            rewriter.insertAfter(ctx.statement(0).stop, "<div style=\"background-color: " + (greenBlocks.contains(blockNumber) ? "green" : "red") + "; border: 1px dashed white;\" >");
            rewriter.insertAfter(ctx.stop, "</div>");
            blockNumber++;
        }
    }

    /**
     * this method is responsible for switch
     * @param ctx
     */
    @Override
    public void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) {
        if (!ctx.blockStatement(0).start.getText().equals("{"))
            injectHtml(ctx);
    }
}