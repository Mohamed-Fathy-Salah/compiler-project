import org.antlr.v4.runtime.TokenStream;

public class IntermediateCodeGenerator extends JavaParserBaseListener {
    private int blockNumber = 0;
    private int indent = 0;
    private final TokenStream tokens;

    public IntermediateCodeGenerator (JavaParser parser) {this.tokens = parser.getTokenStream();}

    private void enterPrint(String str) {
        println(str);
        indent++;
        println("FileWrite.getInstance().write(" + blockNumber++ + ");");
    }

    private void print(String str) {
        String s = "\t".repeat(indent) + str ;

        FileWrite.Singleton().append(s);
    }

    private void println(String str) {print(str + "\n");}

    private void exitPrint(String str) {
        indent--;
        println(str);
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
                println(tokens.getText(ctx));
        }
    }

    @Override
    public void exitStatement(JavaParser.StatementContext ctx) {
        String text = ctx.getChild(0).getText();

        // skip blocks & if statements
        if (text.charAt(0) == '{' || ctx.ifBranch() != null || ctx.tryBlock() != null)
            return;

        switch (text) {
            case "for", "while" -> exitPrint("}");
            case "do" -> exitPrint("} while(" + tokens.getText(ctx.parExpression()) + ");");
        }
    }

    @Override
    public void enterIfBranch(JavaParser.IfBranchContext ctx) { enterPrint("if " + tokens.getText(ctx.parExpression()) + " {"); }

    @Override
    public void exitIfBranch(JavaParser.IfBranchContext ctx) { exitPrint("}"); }

    @Override
    public void enterElseBranch(JavaParser.ElseBranchContext ctx) { enterPrint("else {"); }

    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        String thrown = (ctx.THROWS() != null) ?
                " " + ctx.THROWS() + " " + ctx.qualifiedNameList().getText() : "";

        enterPrint(ctx.typeTypeOrVoid().getText() + " " +
                ctx.identifier().getText() + " " +
                tokens.getText(ctx.formalParameters()) +
                thrown + " {");
    }

    @Override
    public void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        if(ctx.identifier().getText().equals("main"))
            println("FileWrite.Singleton().write(\"out/runblocks.txt\");");

        exitPrint("}");
    }

    @Override
    public void enterModifier(JavaParser.ModifierContext ctx) { print(ctx.getText() + " "); }

    @Override
    public void exitElseBranch(JavaParser.ElseBranchContext ctx) { exitPrint("}"); }

    @Override
    public void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx) { println(tokens.getText(ctx)); }

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

    @Override
    public void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx) { println(tokens.getText(ctx));}
}