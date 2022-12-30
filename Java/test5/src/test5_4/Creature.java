package test5_4;
import java.io.Serializable;

public class Creature<T> implements Serializable
{
    private char gender;
    public double weight;

    private void breath()
    {
        System.out.println("The creature can breathe.");
    }

    public void eat()
    {
        System.out.println("The creature can eat.");
    }

}
