package cz.upol.jj.seminar06;

public class EmptyStackException extends Exception
{
    EmptyStackException (Throwable exception)
    {
        super ("Stack is empty.", exception);
    }
}
