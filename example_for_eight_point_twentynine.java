import java.util.Scanner;

public class example_for_eight_point_twentynine {
    public static void main(String[] args) {
        int[][] array1 = get_2d_array(); // get array 1
        int[][] array2 = get_2d_array();

        boolean a = equals(array1, array2); // get if arrays are the same

        if (a) {
            System.out.println("The two arrays are identical.");
        }
        else {
            System.out.println("The two arrays are not identical.");
        }

        /*
        System.out.println(Arrays.toString(array1[0])); // printing arrays at finale for testing
        System.out.println(Arrays.toString(array1[1]));
        System.out.println(Arrays.toString(array1[2]));
        */

    }
    public static int[] get_1d_array(int num) {
        Scanner inputs = new Scanner(System.in); // getting an error 'convert to try-with-resources. unsure of origin'
        int[] array = {0, 0, 0}; // initial array
        int current;
        for (int i = 0; i < 3; i++) {
            System.out.print("["+num+"]"+"["+i+"]: ");
            current = inputs.nextInt(); // get next int from input.
            array[i] = current; // add to list at space [i]
        }
        return array;
    }
    public static int[][] get_2d_array() { // get a 2d array by looping through getting 1d arrays 3 times
        int[][] array = {{0,0,0}, {0,0,0}, {0,0,0}}; // define a 2d array
        System.out.println("Enter a 2d array: "); // tell user to enter a 2d array
        for (int i = 0; i < 3; i++) {
            array[i] = get_1d_array(i);
        }
        System.out.print("\n"); // add endline charaters at the end
        return array;
    }

    public static boolean equals(int[][] array1, int[][] array2) { // check if the two arrays are equal
        for (int y = 0; y < 3; y++) { // loop through array
            for (int x = 0; x < 3; x++) { // loop through arrays in array
                if (array1[y][x] != array2[y][x]) { // check if any values are not equal
                    return false; // return false if values are different
                }
            }
        }
        return true; // return true if everything is the same
    }
}