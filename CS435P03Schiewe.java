import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class CS435P03Schiewe {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) inputFile = args[0];

        InputStream is = System.in;
        if (inputFile != null) is = new FileInputStream(inputFile);

        ANTLRInputStream input = new ANTLRInputStream(is);
        P03SchieweLexer lexer = new P03SchieweLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        P03SchieweParser parser = new P03SchieweParser(tokens);
        ParseTree tree = parser.program();

        String treeRepresentation = tree.toStringTree(parser);
        System.out.println(treeRepresentation);

        // Write the tree representation to an output file
        if (inputFile != null) {
            String outputFile = inputFile.replace(".txt", "-output.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(treeRepresentation);
            }
        }
    }
}