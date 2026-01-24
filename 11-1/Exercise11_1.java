import java.util.Scanner;

public class Exercise11_1 {
    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        System.out.println("Enter 3 sides of a triangle: "); // get the sides from the user
        double side1, side2, side3;

        side1 = Integer.parseInt(a.next());
        side2 = Integer.parseInt(a.next());
        side3 = Integer.parseInt(a.next());

        System.out.println("Enter a color for the Triangle: "); // get the color
        String color = a.next();

        System.out.println("(true/false) Will the triangle be filled?: "); // get if filled
        boolean fill = a.nextBoolean();

        Triangle triag = new Triangle(side1, side2, side3); // make the triangle
        triag.setFilled(fill);
        triag.setColor(color);
        System.out.println("Area: " + triag.getArea() + ", Perimeter: " + triag.getPerimeter() + ", Color: " + triag.getColor() + ", Filled: " + triag.isFilled());
        //The program should display the area, perimeter, color, and true or false to indicate whether it is filled or not.
    }
}
// GeometricObject.java: The abstract GeometricObject class
abstract class GeometricObject {
private String color = "white";
private boolean filled;
/**Default construct*/
protected GeometricObject() {
}
/**Construct a geometric object*/
protected GeometricObject(String color, boolean filled) {
this.color = color;
this.filled = filled;
}
/**Getter method for color*/
public String getColor() {
return color;
}
/**Setter method for color*/
public void setColor(String color) {
this.color = color;
}
/**Getter method for filled. Since filled is boolean,
so, the get method name is isFilled*/
public boolean isFilled() {
return filled;
}
/**Setter method for filled*/
public void setFilled(boolean filled) {
this.filled = filled;
}
/**Abstract method findArea*/
public abstract double getArea();
/**Abstract method getPerimeter*/
public abstract double getPerimeter();
}
class Triangle extends GeometricObject {
    double side1, side2, side3 = 1.0;

    public Triangle() { // empty constructor
    }
    public Triangle(double side1, double side2, double side3) { // filled constructor
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getSide1() { // side1 get
        return this.side1;
    }
    public double getSide2() { // side2 get
        return this.side2;
    }
    public double getSide3() { // side3 get
        return this.side3;
    }

    public double getArea() { // get the area of the triangle
        double s = getPerimeter();
        return Math.sqrt((s * (s - this.side1)*(s-this.side2)*(s - this.side3)));
    }

    public double getPerimeter() { // get the perimeter of the triangle
        return this.side1+ this.side2+ this.side3; // all sides added together
    }

    public String getColor() { // get the triangle's color
        return super.getColor();
    }
    public String toString() { // tostring method
        return "Triangle: side1 = " + this.side1 + " side2 = " + this.side2 + " side3 = " + this.side3;
    }
    
}

/* 11.1 (The Triangle class) Design a class named Triangle that extends GeometricObject. The class contains:

Three double data fields named side1, side2, and side3 with default values 1.0 to denote three sides of a triangle.

A no-arg constructor that creates a default triangle.

A constructor that creates a triangle with the specified side1, side2, and side3.

The accessor methods for all three data fields.

A method named getArea() that returns the area of this triangle.

A method named getPerimeter() that returns the perimeter of this triangle.

A method named toString() that returns a string description for the triangle.

For the formula to compute the area of a triangle, see Programming Exercise 2.19. The toString() method is implemented as follows:


return "Triangle: side1 = " + side1 + " side2 = " + side2 +
  " side3 = " + side3;

Draw the UML diagrams for the classes Triangle and GeometricObject and implement the classes. 
Write a test program that prompts the user to enter three sides of the triangle, a color, and a 
Boolean value to indicate whether the triangle is filled. The program should create a Triangle 
object with these sides and set the color and filled properties using the input. 

The program should display the area, perimeter, color, and true or false to indicate whether it is filled or not. */
