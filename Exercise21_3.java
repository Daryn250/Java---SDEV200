import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Exercise21_3 {
    public static void main(String[] args) throws FileNotFoundException {
        CountKeywords a = new CountKeywords();
        a.main();
    }
}

class CountKeywords {
    public void main() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Java source file: ");
        String filename = input.nextLine();

        File file = new File(filename);
        if (file.exists()) {
            System.out.println("The number of keywords in " + filename + " is " + countKeywords(file));

        } else {
            System.out.println("File " + filename + " does not exist");
        }
    }


public static int countKeywords(File file) throws FileNotFoundException {
    // Array of all java keywords
    String[] keywordString = {
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else", "enum", "extends", "for", "final", "finally", "float", "goto", "if",
        "implements", "import", "instanceof", "int", "protected", "public", "return", "short", "static", "strictfp",
        "super", "switch", "synchronised", "this", "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "null"
    };

    Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));

    int count = 0;

    Scanner input = new Scanner(file);

    boolean in_comment = false;

    // go word by word and count words that are NOT in comments.

    while (input.hasNextLine()) {
        String line = input.nextLine();
        int len = line.length();
        boolean inLineComment = false;
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char c = line.charAt(i);

            // check for start and end of block comment
            if (!inLineComment) {
                if (!in_comment && i + 1 < len && c == '/' && line.charAt(i + 1) == '*') {
                    in_comment = true;
                    i++; // skip '*'
                    continue;
                }
                if (in_comment && i + 1 < len && c == '*' && line.charAt(i + 1) == '/') {
                    in_comment = false;
                    i++; // skip '/'
                    continue;
                }
            }

            // skip text in block comments
            if (in_comment) {
                continue;
            }

            // line comment is "//" or pos 0 and pos 1
            if (!inLineComment && i + 1 < len && c == '/' && line.charAt(i + 1) == '/') {
                    inLineComment = true;
                    break; // rest of line is a comment
            }
            

            if (Character.isJavaIdentifierPart(c)) { //build from tokens
                token.append(c);
            } else {
                if (token.length() > 0) {
                    String word = token.toString();
                    if (keywordSet.contains(word)) {
                        count++;
                    }
                    token.setLength(0);
                }
            }
        }

        // end of line, flush token
        if (!in_comment) {
            if (token.length() > 0) {
                String word = token.toString();
                if (keywordSet.contains(word)) {
                    count++;
                }
            }
        }
    }

    input.close();
    return count;
}
}

/*
**21.3 (Count the keywords in Java source code) Revise the program in LiveExample 21.7. 
If a keyword is in a comment or in a string, don’t count it. Pass the Java file name from the command line. 
Assume the Java source code is correct and line comments and paragraph comments do not overlap.
 */