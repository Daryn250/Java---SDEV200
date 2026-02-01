public class Exercise13_9 {
    public static void main(String[] args) {
        
    }
}

class GeometricObject {

    public int getDateCreated() {
        // FIXME: return date created for geometric object
        return 0;
    }

    public double getArea() {
        // FIXME: return area
        return 0;
    }

    public double getPerimeter() {
        // FIXME: return perimeter
        return 0;
    }

    public double compareTo(GeometricObject obj) {

        return 0;
    }
}

class Circle extends GeometricObject {
    private double radius;

    public Circle() {

    }
    public Circle(double radius) {
        this.radius = radius;
    }

    // get radius
    public double getRadius() {
        return this.radius;
    }


    // set a new radius
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override /* return area */
    public double getArea() {
        return radius * radius * Math.PI;
    }

    @Override /* return perimeter */
    public double getPerimeter() {
        return 2*radius *Math.PI;
    }

    public int compareTo(Circle circle) {
        return Double.compare(radius, circle.radius);
    }

    public void printCircle() {
        System.out.println("The circle is created " + getDateCreated() + " and the radius is " + radius);
    }
}

/* 13.9 (Enable Circle comparable) Rewrite the Circle class in LiveExample
13.2 to extend GeometricObject and implement the Comparable interface.
Override the equals method in the Object class. Two Circle objects are equal 
if their radii are the same. Draw the UML diagram that involves Circle, GeometricObject, 
and Comparable. */