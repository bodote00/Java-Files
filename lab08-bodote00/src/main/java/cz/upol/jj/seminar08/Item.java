package cz.upol.jj.seminar08;

import java.lang.Object;

public class Item extends Object
{
    private final String name;
    private final Type type;
    private final int count;
    private final double price;

    public Item(String name, Type type, int count, double price)
    {
        this.name = name;
        this.type = type;
        this.count = count;
        this.price = price;
    }

    public double getPrice()
    {
        return price;
    }

    public String getName()
    {
        return name;
    }

    public int getCount()
    {
        return count;
    }

    public Type getType()
    {
        return type;
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "name=" + name +
                ", type=" + type +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}

