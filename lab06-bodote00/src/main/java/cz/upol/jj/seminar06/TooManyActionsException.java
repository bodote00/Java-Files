package cz.upol.jj.seminar06;

public class TooManyActionsException extends Exception
{
    TooManyActionsException (Throwable exception)
    {
        super ("Too many actions." , exception);
    }
}
