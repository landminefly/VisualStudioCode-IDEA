package test5_2;

import java.util.ArrayList;
import java.util.List;

public class GenericMethod
{
    public <E> List<E> copyFromArrayToList(E[] arr)
    {
        ArrayList<E> list = new ArrayList<>();
        for(E e : arr)
        {
            list.add(e);
        }
        return list;
    }
}
