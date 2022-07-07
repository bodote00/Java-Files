package cz.upol.jj.seminar06;

public class FullStackException extends Exception
{
    FullStackException (Throwable exception)
    {
        super ("Stack is full." , exception);
    }
}