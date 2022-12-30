package test7_3;

abstract class Shape
{
    public abstract double getArea();
    public abstract double getPerimeter();
}
class Circle extends Shape
{
    private double radius;

    public Circle(double radius)
    {
        this.radius = radius;
    }

    public Circle()
    {
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    @Override
    public double getArea()
    {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter()
    {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape
{
    private double length;
    private double width;

    public Rectangle(double length, double width)
    {
        if(length < width)
        {
            throw new RuntimeException("长不能比宽短！");
        }
        this.length = length;
        this.width = width;
    }

    public Rectangle()
    {
    }

    public void setLength(double length)
    {
        this.length = length;
    }

    public void setWidth(double width)
    {
        this.width = width;
    }

    @Override
    public double getArea()
    {
        return width * length;
    }

    @Override
    public double getPerimeter()
    {
        return 2 * (width + length);
    }
}