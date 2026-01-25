import java.util.Scanner;

public class Exercise12_9 {
    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        System.out.println("Enter a binary number: ");
        String b = a.next();
        try {
            System.out.println(bin2Dec(b));
        } catch (BinaryFormatException e) {
            System.out.println("Not a binary number");
        }
    }

    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        for (int i = 0; i < binaryString.length(); i++) {
            if ((binaryString.charAt(i)) != '0' && (binaryString.charAt(i)) != '1') {
                throw new BinaryFormatException("Not a binary number");
            }
        }
        int total = 0;
        int count = 0;
        int binary = Integer.parseInt(binaryString);
        while (binary > 0) {
            if ((binary % 10) == 1) {
                total += (int)(Math.pow(2, count));
            }
            binary = binary/10;
            count ++;
        }
        return total;
    }
}

class BinaryFormatException extends Exception {
    public BinaryFormatException(String message) {
        super(message);
    }
}
/*
*12.9 (BinaryFormatException) Exercise 12.7 implements the bin2Dec method to throw a BinaryFormatException 
if the string is not a binary string. Define a custom exception called BinaryFormatException. 
Implement the bin2Dec method to throw a BinaryFormatException if the string is not a binary string. 
*/

/*
*12.7 (NumberFormatException) Write the bin2Dec(String binaryString) method to convert a binary string into a decimal number. 
Implement the bin2Dec method to throw a NumberFormatException if the string is not a binary string. 
Write a test program that prompts the user to enter a binary number as a string and displays its decimal equivalent. 
If the method throws an exception, display "Not a binary number". */