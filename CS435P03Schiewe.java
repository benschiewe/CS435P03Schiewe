import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

public class CS435P03Schiewe {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) inputFile = args[0];

        InputStream is = System.in;
        if (inputFile != null) is = new FileInputStream(inputFile);

        ANTLRInputStream input = new ANTLRInputStream(is);
        P03SchieweLexer lexer = new P03SchieweLexer(input);

        // Custom error listener for lexer
        CustomErrorListener errorListener = new CustomErrorListener();
        lexer.removeErrorListeners(); // Remove default console error listener
        lexer.addErrorListener(errorListener);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        P03SchieweParser parser = new P03SchieweParser(tokens);

        // Custom error listener for parser
        parser.removeErrorListeners(); // Remove default console error listener
        parser.addErrorListener(errorListener);

        ParseTree tree = parser.program();

        String treeRepresentation = tree.toStringTree(parser);
        System.out.println(treeRepresentation);

        // Write the tree representation and errors to an output file
        if (inputFile != null) {
            String outputFile = inputFile.replace(".txt", "-output.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write("Parse Tree:\n");
                writer.write(treeRepresentation + "\n\n");
                writer.write("Errors:\n");
                for (String error : errorListener.getErrors()) {
                    writer.write(error + "\n");
                }
            }
        }
    }
}

// Custom error listener to capture errors
class CustomErrorListener extends BaseErrorListener {
    private final List<String> errors = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, 
                            int line, int charPositionInLine, String msg, RecognitionException e) {
        String error = "line " + line + ":" + charPositionInLine + " " + msg;
        errors.add(error);
        System.err.println(error); // Print the error to the console
    }

    public List<String> getErrors() {
        return errors;
    }
}