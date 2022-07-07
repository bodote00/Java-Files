package cz.upol.jj.seminar06;

import java.util.ArrayList;
import java.util.Optional;

public class Stack<T>
{
    public final int capacity;
    public final ArrayList<T> stack;
    public int top = -1;

    public Stack(int capacity)
    {
        this.capacity = capacity;
        this.stack = new ArrayList<T>(capacity);
    }

    public int getCapacity()
    {
        return this.capacity;
    }

    public int length()
    {
        return this.stack.size();
    }

    public T peek() throws EmptyStackException
    {
        try
        {
            return this.stack.get(this.top);
        }
        catch (Exception exception)
        {
            throw new EmptyStackException(exception);
        }
    }

    public void push(T element) throws FullStackException
    {
        if (!this.isFull())
        {
            this.stack.add(element);
        }
        else if (this.isFull())
        {
            throw new FullStackException(new Exception());
        }
        this.top = this.top + 1;
    }

    public T pop() throws EmptyStackException
    {
        try
        {
            T result = this.stack.get(this.top);
            this.stack.remove(this.top--);
            return result;
        }
        catch (Exception exception)
        {
            throw new EmptyStackException(exception);
        }
    }

    public boolean isFull()
    {
        return this.capacity - 1 == this.top;
    }

    public boolean isEmpty()
    {
        return this.top == -1;
    }

    public Optional<T> safePop()
    {
        if (this.isEmpty())
        {
            Optional<T> result = Optional.empty();
            return result;
        }
        else if (!this.isEmpty())
        {
            T element = this.stack.get(this.top);
            this.stack.remove(this.top--);
            Optional<T> result = Optional.of(element);
            return result;
        }
        return null;
    }

    public boolean safePush(T element)
    {
        if (this.isFull())
        {
            return false;
        }
        else if (!this.isFull())
        {
            this.stack.add(++top, element);
            return true;
        }
        else
        {
            return true;
        }
    }

    public Optional<T> safePeek()
    {
        if(!this.isEmpty())
        {
            T result_T = this.stack.get(this.top);
            Optional<T> result = Optional.of(result_T);
            return result;
        }
        else
        {
            Optional<T> result = Optional.empty();
            return result;
        }
    }

    public void emptyStack()
    {
        this.top = -1;
        this.stack.clear();
    }
}
