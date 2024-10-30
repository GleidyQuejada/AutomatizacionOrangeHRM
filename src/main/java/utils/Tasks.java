package utils;

import java.lang.reflect.Constructor;

public class Tasks
{
    //Generic Method that instantiates any class
    @SafeVarargs
    public static <T> T instrumented (Class<? extends T> clazz, Object... parameters)
    {
        try
        {
            for(Constructor<?> constructor: clazz.getConstructors())
            {
                if(constructor.getParameterTypes().length == parameters.length)
                {
                    return (T) constructor.newInstance(parameters);
                }
            }

            throw new RuntimeException("Error in constructor" + clazz.getSimpleName());
        }catch (Exception e)
        {
            throw new RuntimeException("error instantiating class" + clazz.getSimpleName(),e);
        }

    }

}
