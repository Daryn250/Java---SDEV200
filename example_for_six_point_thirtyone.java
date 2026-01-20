import java.util.Scanner;

public class example_for_six_point_thirtyone {

  // First, define the main function
  public static void main(String[] args) {
    // create an input to enter the credit card number. not neccicary, but fun.
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter a credit card number: "); // print instructions
    long card = input.nextLong(); // get credit card number as a long
    
    // Check if the card is valid using user defined functions
    if (is_valid(card)) {
      System.out.println("The card number is valid");
    } else {
      System.out.println("The card number is invalid");
    }
    input.close(); // close for preventing the very real memory leak
  }

  // check to see if credit card is valid
  public static boolean is_valid(long number) {
    // 13 - 16 digits exclusive
    if (getSize(number) < 13 || getSize(number) > 16) {
      return false;
    }
    
    // prefix checker to make sure it starts with 37, 4, 5, or 6
    if (!prefix(number, 4) && !prefix(number, 5) && 
        !prefix(number, 37) && !prefix(number, 6)) {
      return false;
    }
    
    // luhn check or sum of doubling even places and odd places
    int total = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
    
    boolean luhn_check = total % 10 == 0; // true or false depending on last digit is a 0

    return luhn_check;
  }

  // get sum of even places by going through digits (%10) and then adding them (doubled) when count is even
  public static int sumOfDoubleEvenPlace(long number) {
    int sum = 0;    // current sum
    int count = 1;  // count digits
    
    
    while (number > 0) {
      int digit = (int)(number % 10);  // rightmost digit
      number = number / 10;
      
      // add only when count is an even number
      if (count % 2 == 0) {
        int d2 = digit * 2; // digit doubled
        sum = sum + getDigit(d2); // add to total
      }
      
      count++;
    }
    
    return sum;
  }

  // important when doing lahn's method for adding digits. can handle 2 place digits and 1 place digits
  public static int getDigit(int number) {
    if (number > 9) {
      // 2 place digit, add them together
      int firstDigit = number / 10;
      int secondDigit = number % 10;
      return firstDigit + secondDigit;
    } else {
      // already 1 digit, just return
      return number;
    }
  }

  // get the sum of all the odd placed digits
  public static int sumOfOddPlace(long number) {
    int sum = 0; // sum
    int count = 1;  // count of digits
    
    // vididing by 10 goes through all digits
    while (number > 0) {
      int digit = (int)(number % 10);  // rightmost digit
      number = number / 10;
      
      // if odd, add to the sum
      if (count % 2 == 1) {
        sum = sum + digit;
      }
      
      count++; // increment count
    }
    
    return sum;
  }

  // does card (number) start with certain digits (d)
  public static boolean prefix(long number, int d) {
    // get the first digits of the card number
    long prefix = getPrefix(number, getSize(d));
    
    return prefix == d; // returns true if matches
  }

  // length of card number
  public static int getSize(long d) {
    int size = 0;
    
    // divide until 0
    while (d > 0) {
      size++;
      d = d / 10;
    }
    
    return size;
  }

  // get k amount of digits from the prefix
  public static long getPrefix(long number, int k) {
    int totalDigits = getSize(number);
    
    // figure out how many times to divide, or just length minus k
    int timesToDivide = totalDigits - k;
    
    // loop through to divide
    for (int i = 0; i < timesToDivide; i++) {
      number = number / 10;
    }
    
    return number; // return prefix
  }
}
