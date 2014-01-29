package focsy.compiler;

import focsy.compiler.lexer.LexException;
import focsy.compiler.lexer.Lexer;
import focsy.compiler.parser.AST;
import focsy.compiler.parser.ParseException;
import focsy.compiler.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Get the file name
	    String fileName = "";
        if(args.length == 0) {
            System.out.print("Enter the name of the file to parse: ");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in));
            try {
                fileName = br.readLine();
            } catch(IOException ex) {
                System.err.println("Failed to read from stdin. Exiting...");
                System.exit(1);
            }
        } else {
            fileName = args[0];
        }

        // Read the file into memory
        String file = "";
        try {
            file = readFile(fileName, Charset.defaultCharset());
        } catch(IOException ex) {
            System.err.println("Failed to read file '" + fileName + "'. Exiting...");
            System.exit(1);
        }

        // Lex the file's contents into tokens
        Lexer lexer = new Lexer();
        List<Token> tokens = null;
        try {
            tokens = lexer.lex(file);
        } catch(LexException ex) {
            System.err.println("Lex error:");
            System.err.println("  Message: " + ex.getMessage());
            System.err.println("  Location: " + ex.getRange());
            System.err.println("Exiting...");
            System.exit(1);
        }

        // Parse the tokens into ASTs
        Parser parser = new Parser();
        List<AST> asts = null;
        try {
            asts = parser.parse(tokens);
        } catch(ParseException ex) {
            System.err.println("Parse error:");
            System.err.println("  Message: " + ex.getMessage());
            System.err.println("  Location: " + ex.getRange());
            System.err.println("Exiting...");
            System.exit(1);
        }

        // Print the ASTs
        for(AST ast : asts) {
            System.out.println(ast);
        }
    }

    private static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }
}
