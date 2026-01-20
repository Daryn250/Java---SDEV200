public class Main {
    /** Convert from feet to meters */
  public static double footToMeter(double foot) {
    return foot * 3.279;
  }

  /** Convert from meters to feet */
  public static double meterToFoot(double meter) {
    return meter * 0.305;
  }
  public static void main(String[] args) { // main function
    System.out.println("Feet    Meters                     Meters    Feet"); // print header
    System.out.println("-------------------------------------------------"); // print line
    for (int i = 1; i <= 10; i++) { // loop 10 times through all values
      double meters = footToMeter(i); // get meters
      double feet = meterToFoot(i * 5); // get feet
      System.out.println(i + ".0     " + meters + "             " + (i * 5) + ".0     " + feet); // print formatted
    }
  }
}