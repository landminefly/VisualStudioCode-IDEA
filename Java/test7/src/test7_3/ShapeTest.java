package test7_3;

public class ShapeTest
{
    public static void main(String[] args)
    {
        Circle circle = new Circle(3);
        System.out.println(circle.getArea());
        System.out.println(circle.getPerimeter());
        Rectangle rectangle = new Rectangle(3,2);
        System.out.println(rectangle.getArea());
        System.out.println(rectangle.getPerimeter());
    }
}
