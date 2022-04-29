import org.antlr.v4.runtime.TokenStreamRewriter;

public class IntermediateCodeGenerator extends JavaParserBaseListener {
    private final String fileName;
    private final TokenStreamRewriter rewriter;
    private int blockNumber = 0;

    public IntermediateCodeGenerator(TokenStreamRewriter rewriter, String fileName) {
        this.rewriter = rewriter;
        this.fileName = fileName;
    }

    private String getBlock() {
        // g for green
        return "\n\t\tFileWrite.Singleton().append(\"g \"" + blockNumber++ + ");\n";
    }

    private String getInjection(String expr) {
        // TODO : parge arr to -> new String[]{exprs};
        String[] arr = expr.split("\\|\\|");
        // TODO: should return g or o
        return "\nColorSelector.Singleton().eval(" + arr + ")\n";
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        rewriter.insertAfter(ctx.start, getBlock());
    }

    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        switch (ctx.getChild(0).getText()) {
            case "if", "while", "do" -> {
                if (!ctx.statement(0).start.getText().equals("{")) {
                    rewriter.insertBefore(ctx.statement(0).start, "{" + getBlock());
                    rewriter.insertAfter(ctx.statement(0).stop, "}");
                } else {
                    rewriter.insertAfter(ctx.statement(0).start, getBlock());
                }
                rewriter.insertAfter(ctx.stop, getBlock(ctx.parExpression().getText());
                System.out.println();
            }
            case "for" -> {
                if (!ctx.statement(0).start.getText().equals("{")) {
                    rewriter.insertBefore(ctx.statement(0).start, "{" + getBlock());
                    rewriter.insertAfter(ctx.statement(0).stop, "}");
                } else {
                    rewriter.insertAfter(ctx.statement(0).start, getBlock());
                }
            }
        }
    }

    @Override
    public void exitStatement(JavaParser.StatementContext ctx) {
        // else statement
        if (ctx.getChild(0).getText().equals("if") && ctx.statement(1) != null && !ctx.statement(1).start.getText().equals("{")) {
            rewriter.insertBefore(ctx.statement(1).start, "{" + getBlock());
            rewriter.insertAfter(ctx.statement(1).stop, "}");
        }
    }

    @Override
    public void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) {
        if (!ctx.blockStatement(0).start.getText().equals("{")) {
            rewriter.insertBefore(ctx.blockStatement(0).start, "{ \n" + getBlock());
            rewriter.insertAfter(ctx.blockStatement(ctx.blockStatement().size() - 1).stop, "\n }");
        }
    }


    @Override
    public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        if (ctx.identifier().getText().equals("main")) {
            rewriter.insertBefore(ctx.methodBody().block().stop, "\t\tFileWrite.Singleton().write(\"examples/blocks/" + fileName + "_blocks.txt\");\n");
        }
    }
}