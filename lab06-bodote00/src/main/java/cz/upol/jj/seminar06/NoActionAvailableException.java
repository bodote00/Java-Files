package cz.upol.jj.seminar06;

public class NoActionAvailableException extends Exception
{
    NoActionAvailableException (Throwable exception)
    {
        super ("No more actions is available", exception);
    }
}
