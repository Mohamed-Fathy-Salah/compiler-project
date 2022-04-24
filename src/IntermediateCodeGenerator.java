import org.antlr.v4.runtime.TokenStream;

public class IntermediateCodeGenerator extends JavaParserBaseListener {
    private int blockNumber = 0;
    private int indent = 0;
    private TokenStream tokens;

    public IntermediateCodeGenerator (JavaParser parser) {this.tokens = parser.getTokenStream();}

    private void enterPrint(String str) {
        print(str);
        indent++;
        print("FileWrite.getInstance().write(" + blockNumber++ + ");");
    }

    private void print(String str) {
        StringBuilder s = new StringBuilder();

        for (int i = 0 ;i < indent;i++)
            s.append("\t");
        s.append(str + "\n");

        FileWrite.Singleton().append(s.toString());
    }
    private void exitPrint(String str) {
        indent--;
        print(str);
    }

    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        String text = ctx.getChild(0).getText();

        // skip blocks & if statements
        if (text.charAt(0) == '{' || ctx.ifBranch() != null || ctx.tryBlock() != null)
            return;

        switch (text) {
            case "for" :
                enterPrint("for (" + tokens.getText(ctx.forControl()) + ") {");
                break;
            case "while" :
                enterPrint("while (" + tokens.getText(ctx.parExpression()) + ") {");
                break;
            case "do" :
                enterPrint("do {");
                break;
            case "switch" :
                break;
            default:
                print(ctx.getText());
        }
    }

    @Override
    public void exitStatement(JavaParser.StatementContext ctx) {
        String text = ctx.getChild(0).getText();

        // skip blocks & if statements
        if (text.charAt(0) == '{' || ctx.ifBranch() != null || ctx.tryBlock() != null)
            return;

        switch (text) {
            case "for" :
            case "while" :
                exitPrint("}");
                break;
            case "do" :
                exitPrint("} while(" + tokens.getText(ctx.parExpression()) + ");");
                break;
        }
    }

    @Override
    public void enterIfBranch(JavaParser.IfBranchContext ctx) {
        enterPrint("if " + tokens.getText(ctx.parExpression()) + " {");
    }

    @Override
    public void exitIfBranch(JavaParser.IfBranchContext ctx) {
        exitPrint("}");
    }

    @Override
    public void enterElseBranch(JavaParser.ElseBranchContext ctx) {
        enterPrint("else {");
    }

    @Override
    public void exitElseBranch(JavaParser.ElseBranchContext ctx) {
        exitPrint("}");
    }

    // Try
    @Override
    public void enterTryBlock(JavaParser.TryBlockContext ctx) { enterPrint("try {"); }

    @Override
    public void exitTryBlock(JavaParser.TryBlockContext ctx) { exitPrint("}"); }

    // catch
    @Override
    public void enterCatchClause(JavaParser.CatchClauseContext ctx) { enterPrint("catch " + tokens.getText(ctx.catchIdentifier()) + " {");}

    @Override
    public void exitCatchClause(JavaParser.CatchClauseContext ctx) { exitPrint("}");}

    // finally
    @Override
    public void enterFinallyBlock(JavaParser.FinallyBlockContext ctx) { enterPrint("finally {");}

    @Override
    public void exitFinallyBlock(JavaParser.FinallyBlockContext ctx) { exitPrint("}"); }


}


