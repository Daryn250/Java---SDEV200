import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Exercise20_11 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String path = input.next();
        input.close();
        
        boolean final_value = check_groupings(path);

        if (final_value) {
            System.out.println("Correct grouping pairs");
        }
        if (!final_value) {
            System.out.println("Incorrect grouping pairs");
        }
    }
    
    static List<Character> remove_symbol(List<Character> found_symbols, Character symbol) {
        int index = 0;
        for (int i = found_symbols.size() - 1; i >= 0; i--) {
            if (Objects.equals(found_symbols.get(i), symbol)) {
                index = i;
                break;
            }
        }
        found_symbols.remove(index);
        return found_symbols;
    }

    static boolean check_groupings(String path) {
        // keep an array of the forward symbols and backward symbols
        List<Character> found_symbols = new ArrayList<>();

        // open file
        Path filePath = Paths.get(path);

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            // read file lines
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '[') {
                        found_symbols.add('1');
                    }
                    if (line.charAt(i) == '{') {
                        found_symbols.add('2');
                    }
                    if (line.charAt(i) == '(') {
                        found_symbols.add('3');
                    }
                    if (line.charAt(i) == ']') { // remove last instance of symbol
                        found_symbols = remove_symbol(found_symbols, '1');
                    }
                    if (line.charAt(i) == '}') {
                        found_symbols = remove_symbol(found_symbols, '2');
                    }
                    if (line.charAt(i) == ')') {
                        found_symbols = remove_symbol(found_symbols, '3');
                    }
                }
            }
        } catch (IOException e) {
            // handling errors
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        return found_symbols.isEmpty();
    }

}


/*
*20.11 (Match grouping symbols) A Java program contains various pairs of grouping symbols, such as:

Parentheses: ( and )

Braces: { and }

Brackets: [ and ]

Note the grouping symbols cannot overlap. For example, (a{b)} is illegal. 
Write a program to check whether a Java source-code file has correct pairs 
of grouping symbols. Pass the source-code file name as a command-line argument.
 */
