import java.util.Scanner;

// cannot do multiplication
// cannot do negative numbers
// cannot do division
// but it can do addition :)

public class Java_Calculator {
    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        String input;
        System.out.print("Enter an equation: ");
        input = a.next();
        System.out.println(compute(get_first_num(input), get_last_num(input), get_operand(input)));
        

    }
    public static int check_until_nonint(String a) {
        // returns the end position of the first int number in the string
        for (int i = 0; i<a.length(); i++) {
            if (!Character.isDigit(a.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public static int get_last_num(String a) {
        int pos = check_until_nonint(a);
        int num;
        if (pos == -1) {
            return -1; // cannot compute, no first number
        }
        num = Integer.parseInt(a.substring(pos));
        return num;
    }
    public static int get_first_num(String a) {
        int pos = check_until_nonint(a);
        int num;
        if (pos == -1) {
            return -1; // cannot compute, no first number
        }
        num = Integer.parseInt(a.substring(0,pos));
        return num;
    }
    public static char get_operand(String a) {
        int pos = check_until_nonint(a);
        return a.charAt(pos);
    }
    public static double compute(int a, int b, char c) {
        double computed = 0;
        if (c == '+') {
            computed = a+b;
        }
        if (c == '-') { // very confusing but actually works.
            computed = a+b;
        }
        if (c == '*') {
            computed = a*b;
        }
        if (c == '/') {
            computed = a/b;
        }
        return computed;
    }
}

