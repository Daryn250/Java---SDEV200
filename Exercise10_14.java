import java.time.LocalDateTime;

public class Exercise10_14 {
    public static void main(String[] args) {
        // test program !
        MyDate a = new MyDate();
        MyDate b = new MyDate(34355555133101L);
        System.out.println("a date: " + a.getDay() + "-" + a.getMonth() + "-" + a.getYear()); // day month year format. I know, the metric system.
        System.out.println("b date: " + b.getDay() + "-" + b.getMonth() + "-" + b.getYear());
    }
}
class MyDate {
    int year;
    int month;
    int day;
    public MyDate() { // blank constructor, gets time from localdatetime package
        LocalDateTime temp = LocalDateTime.now();
        this.year = temp.getYear();
        this.month = temp.getMonthValue();
        this.day = temp.getDayOfMonth();
    }
    public MyDate(long milliseconds) { // ms constructor, sets time to time from ms
        ConvertFromMS a = new ConvertFromMS(milliseconds);
        this.year = a.getYear();
        this.month = a.getMonth();
        this.day = a.getDay();
    }
    public MyDate(int year, int month, int day) { // full constructor, allows user to set all values
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public int getYear() { // returns year
        return this.year;
    }
    public int getMonth() { // returns month (0 based!)
        return this.month;
    }
    public int getDay() { // returns day
        return this.day;
    }
    public void SetDate(long elapsedTime) { // setdate method to change the date to desired date in ms.
        ConvertFromMS a = new ConvertFromMS(elapsedTime);
        this.year = a.getYear();
        this.month = a.getMonth();
        this.day = a.getDay();
    }
}

// class that helps. Could be accomplished without the use of a class, however, I added it for readability. Handles technical challenges.
class ConvertFromMS {
    int year;
    int month;
    int day;
    public ConvertFromMS(long milliseconds) {
        calcualte_years_days_months(milliseconds);
    }
    public int getYear() { // return statements, same as for mydate
        return this.year;
    }
    public int getMonth() {
        return this.month;
    }
    public int getDay() {
        return this.day;
    }

    private void calcualte_years_days_months(long milliseconds) {
        int days_since_epoch = year_from_ms(milliseconds); // set this.year and return days since epoch
        month_from_days(days_since_epoch);
    }

    private int year_from_ms(long milliseconds) {
        // change ms into year format ONLY and returns remaining days 
        int year = 1970;
        int days_since_epoch = day_from_ms(milliseconds);
        int days_per_year = 365;

        while (days_since_epoch >= days_per_year) {
            days_since_epoch -= days_per_year; // subtract a year from the total days
            year ++; // add a year
            if (is_leap_year(year)) { // if it's a leap year, change the days to 366
                days_per_year = 366;
            }
            else { // else, set them back to 365
                days_per_year = 365;
            }
        }
        this.year = year; // return the year
        return days_since_epoch; // return the amount of days in the year left for months calculation
    }

    private void month_from_days(int days) {
        int month = 0;
        int[] month_days = {
            31, is_leap_year(this.year) ? 29 : 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };
        while (days>=month_days[month]) {
            days -= month_days[month];
            month ++;
        }
        this.month = month; // month is zero based
        this.day = days + 1; // adjust for day format (no 0th day of the month)
    }
    
    private int day_from_ms(long milliseconds) {
        return Math.toIntExact(milliseconds / 86400000); // 1000 ms per second for 3600 seconds (1 hour) for 24 hours
    }

    private boolean is_leap_year(int year) {
        // leap year is a year that is divisible by 4, excluding years that are divisible by 100 and not 400
        if ((year & 3)!=0) {
            return false;
        }
        if ((year % 100) == 0) {
            if ((year%400) != 0) {
                return false;
            }
        }
        return true;
    }

}

/*
*10.14 (The MyDate class) Design a class named MyDate. The class contains:

The data fields year, month, and day that represent a date. month is 0-based, i.e., 0 is for January.

A no-arg constructor that creates a MyDate object for the current date.

A constructor that constructs a MyDate object with a specified elapsed time since midnight, January 1, 1970, in milliseconds.

A constructor that constructs a MyDate object with the specified year, month, and day.

Three getter methods for the data fields year, month, and day, respectively.

A method named setDate(long elapsedTime) that sets a new date for the object using the elapsed time.

Draw the UML diagram for the class then implement the class. Write a test program that creates two MyDate objects (using new MyDate() and new MyDate(34355555133101L)) and displays their year, month, and day.

HINT: The first two constructors will extract the year, month, and day from the elapsed time.
For example, if the elapsed time is 561555550000 milliseconds, the year is 1987,
the month is 9, and the day is 18. You may use the GregorianCalendar class discussed
in Programming Exercise 9.5 to simplify coding. 
*/