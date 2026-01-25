public class Exercise09_09 {
  public static void main(String[] args) {
    RegularPolygon polygon1 = new RegularPolygon(); // use no arg
    RegularPolygon polygon2 = new RegularPolygon(6, 4); // use 2 arg
    RegularPolygon polygon3 = new RegularPolygon(10, 4, 5.6, 7.8); // use 4 arg
    
    // output perimeter and area for each shape
    System.out.println("Polygon 1 - Perimeter: " + polygon1.getPerimeter() + ", Area: " + polygon1.getArea());
    System.out.println("Polygon 2 - Perimeter: " + polygon2.getPerimeter() + ", Area: " + polygon2.getArea());
    System.out.println("Polygon 3 - Perimeter: " + polygon3.getPerimeter() + ", Area: " + polygon3.getArea());
  }
}
@SuppressWarnings("unused")
class RegularPolygon {
  private int n = 3; // sides number
  private double side = 1; // side length
  private double x = 0; // x coordinate of polygon center
  private double y = 0; // y coordinate of polygon center

    public RegularPolygon() { // no arg constructor
      this.n = 3;
      this.side = 1;
      this.x = 0;
      this.y = 0;
    }
    public RegularPolygon(int sides, double side_length) { // 2 arg constructor
      this.n = sides;
      this.side = side_length;
      this.x = 0;
      this.y = 0;
    }
    public RegularPolygon(int sides, double side_length, double x, double y) { // 4 arg constructor
      this.n = sides;
      this.side = side_length;
      this.x = x;
      this.y = y;
    }
    public double getPerimeter() {
      return this.n * this.side;
    }
    public double getArea() {
      return ((this.n * (this.side*this.side))/4*Math.tan(Math.PI/this.n));
    }
  
}