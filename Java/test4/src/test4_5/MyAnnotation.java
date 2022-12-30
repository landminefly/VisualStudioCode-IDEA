package test4_5;

import java.lang.annotation.*;

@Repeatable(MyAnnotations.class)
public @interface MyAnnotation
{
    String[] value();
}
