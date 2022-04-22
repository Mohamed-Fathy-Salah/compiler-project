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
        s.append(str);

        System.out.println(s);

        // TODO : fix not appending to the file
        // FileWrite.getInstance().write(s.toString());
    }
    private void exitPrint(String str) {
        indent--;
        print(str);
    }

    @Override
    public void enterStatement(JavaParser.StatementContext ctx) {
        String text = ctx.getChild(0).getText();

        // skip blocks & if statements
        if (text.charAt(0) == '{' || ctx.ifBranch() != null)
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
            case "try" :
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
        if (text.charAt(0) == '{' || ctx.ifBranch() != null)
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
}

