
import java.math.BigInteger;
import java.util.Scanner;


public class Exercise13_15 {


    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Enter the first rational number: ");
        long numerator = input.nextLong();
        long denominator = input.nextLong();
        Rational r_0 = new Rational(numerator, denominator);

        System.out.print("Enter the second rational number: ");
        long numerator2 = input.nextLong();
        long denominator2 = input.nextLong();
        Rational r_1 = new Rational(numerator2, denominator2);

        System.out.println(r_0 + " + " + r_1 + " = " + r_0.add(r_1));
        System.out.println(r_0 + " - " + r_1 + " = " + r_0.subtract(r_1));
        System.out.println(r_0 + " * " + r_1 + " = " + r_0.multiply(r_1));
        System.out.println(r_0 + " / " + r_1 + " = " + r_0.divide(r_1));
        System.out.println(r_1 + " is " + r_1.doubleValue());

        int comparison = r_0.compareTo(r_1);
        if (comparison < 0) System.out.println(r_0 + " is smaller than " + r_1);
        else if (comparison > 0) System.out.println(r_0 + " is bigger than " + r_1);
        else System.out.println(r_0 + " is equals to " + r_1);

        System.out.println(r_0 + " as (long) " + r_0.longValue());
        System.out.println(r_0 + " as (int) " + r_0.intValue());
        System.out.println(r_0 + " as (double) " + r_0.doubleValue());
        System.out.println(r_0 + " as (float) " + r_0.floatValue());

    }
    
}

class Rational extends Number implements Comparable<Rational> {
    private BigInteger numerator;
    private BigInteger denominator;
    
    public Rational() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    public Rational(long numerator, long denominator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    public Rational(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public Rational add(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator()).add(denominator.multiply(secondRational.getNumerator()));
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    public Rational subtract(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator()).subtract(denominator.multiply(secondRational.getDenominator()));
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    public Rational multiply(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getNumerator());
        BigInteger d = denominator.multiply(secondRational.getDenominator());
        return new Rational(n, d);
    }

    public Rational divide(Rational secondRational) {
        BigInteger n = numerator.multiply(secondRational.getDenominator());
        BigInteger d = denominator.multiply(secondRational.getNumerator());
        return new Rational(n, d);
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE)) {
            return numerator + "";
        }
        else {
            return numerator + "/" + denominator;
        }
    }

    @Override
    public int intValue() {
        return (int)doubleValue();
    }
    @Override
    public float floatValue() {
        return (float)doubleValue();
    }
    @Override
    public double doubleValue() {
        return numerator.doubleValue()/denominator.doubleValue();
    }
    @Override
    public long longValue() {
        return (long)doubleValue();
    }

    @Override
    public int compareTo(Rational secondRational) {
        BigInteger one = numerator.multiply(secondRational.getDenominator());
        BigInteger two = secondRational.getNumerator().multiply(denominator);
        return one.compareTo(two); 
    }



}
