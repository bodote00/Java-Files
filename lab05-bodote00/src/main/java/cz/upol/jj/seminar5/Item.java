package cz.upol.jj.seminar5;

public abstract class Item
{
    public final int year;

    public Item(int year)
    {
        this.year = year;
    }

    public int getYear()
    {
        return year;
    }

}