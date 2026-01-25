import java.util.Scanner;

public class Exercise12_9 {
    public static void main(String[] args) {
        Scanner a = new Scanner(System.in); // make the scanner for input
        System.out.println("Enter a binary number: "); // get the number from the user
        String b = a.next(); // string input
        try {
            System.out.println(bin2Dec(b)); // run bin2Dec (or try based off user input)
        } catch (BinaryFormatException e) {
            System.out.println("Not a binary number"); // this is the catch clause if the user inputs an invalid number
        }
    }

    public static int bin2Dec(String binaryString) throws BinaryFormatException { // define bin2Dec
        for (int i = 0; i < binaryString.length(); i++) { // check the entire string for things that are NOT 1s or 0s
            if ((binaryString.charAt(i)) != '0' && (binaryString.charAt(i)) != '1') {
                throw new BinaryFormatException("Not a binary number"); // throw exception if found
            }
        }
        int total = 0;
        int count = 0;
        int binary = Integer.parseInt(binaryString); // parse the string to int

        while (binary > 0) { // loop through binary string and add values to itself
            if ((binary % 10) == 1) { // get digit of binary and check if it's 1
                total += (int)(Math.pow(2, count)); // if 1, add it plus the current power of 2 to position to total
            }
            binary = binary/10;
            count ++;
        }
        return total; // return int total
    }
}

class BinaryFormatException extends Exception { // exception definition
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
