import org.antlr.v4.runtime.TokenStreamRewriter;

public class IntermediateCodeGenerator extends JavaParserBaseListener {
    private final String fileName;
    private final TokenStreamRewriter rewriter;
    private int blockNumber = 0;

    public IntermediateCodeGenerator(TokenStreamRewriter rewriter, String fileName) {
        this.rewriter = rewriter;
        this.fileName = fileName;
    }

    private String injectAppendFile() {
        return "\nFileWrite.getInstance().append(" + blockNumber++ + ");\n";
    }

    private String injectColor(String expr) {
        String[] expressions = expr.split("\\|\\|");
        StringBuilder tmp = new StringBuilder(expressions[0]);
        for (int i = 1; i < expressions.length; i++)
            tmp.append(",").append(expressions[i]);
        return "ColorHelper.getInstance().eval(" + blockNumber + ", new boolean[]{" + tmp + "});\n";
    }

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        String text = ctx.parent.parent.getChild(0).getText();
        if (!text.equals("if") && !text.equals("while") && !text.equals("do") && !text.equals("for"))
            rewriter.insertAfter(ctx.start, injectAppendFile());
    }

    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        switch (ctx.getChild(0).getText()) {
            case "if", "while", "do" -> {
                if (!ctx.statement(0).start.getText().equals("{")) {
                    rewriter.insertBefore(ctx.statement(0).start,
                            "{" + injectColor(ctx.parExpression().expression().getText()) + injectAppendFile());

                    rewriter.insertAfter(ctx.statement(0).stop, "}");
                } else {
                    rewriter.insertAfter(ctx.statement(0).start,
                            injectColor(ctx.parExpression().expression().getText()) + injectAppendFile());
                }
            }
            case "for" -> {
                if (!ctx.statement(0).start.getText().equals("{")) {
                    rewriter.insertBefore(ctx.statement(0).start,
                            "{" + injectColor(ctx.forControl().expression().getText()) + injectAppendFile());

                    rewriter.insertAfter(ctx.statement(0).stop, "}");
                } else {
                    rewriter.insertAfter(ctx.statement(0).start,
                            injectColor(ctx.forControl().expression().getText()) + injectAppendFile());
                }
            }
        }
    }

    @Override
    public void exitStatement(JavaParser.StatementContext ctx) {
        // else statement
        if (ctx.getChild(0).getText().equals("if") && ctx.statement(1) != null) {
            if (!ctx.statement(1).start.getText().equals("{")) {
                rewriter.insertBefore(ctx.statement(1).start, "{" + injectAppendFile());
                rewriter.insertAfter(ctx.statement(1).stop, "}");
            } else {
                rewriter.insertAfter(ctx.statement(1).start, injectAppendFile());
            }
        }
    }

    @Override
    public void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx) {
        if (!ctx.blockStatement(0).start.getText().equals("{")) {
            rewriter.insertBefore(ctx.blockStatement(0).start, "{ \n" + injectAppendFile());
            rewriter.insertAfter(ctx.blockStatement(ctx.blockStatement().size() - 1).stop, "\n }");
        }
    }


    @Override
    public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        if (ctx.identifier().getText().equals("main")) {
            rewriter.insertBefore(ctx.methodBody().block().stop,
                    "FileWrite.getInstance().append(\"o\");\n" +
                            "FileWrite.getInstance().append(ColorHelper.getInstance().getOrange());\n" +
                            "FileWrite.getInstance().write(\"examples/blocks/" + fileName + "_blocks.txt\");\n"
            );
        }
    }
}