package cz.upol.jj.seminar5;

public class RentableItem <T extends Item & Comparable<T>> implements Comparable<RentableItem<T>>
{
    public T item;
    public boolean rented = false;
    public int price;

    RentableItem(T item, int price)
    {
        this.item = item;
        this.price = price;
    }
    public T getItem()
    {
        return this.item;
    }

    public int compareTo(RentableItem<T> object)
    {
        return this.item.compareTo(object.item);
    }

    public boolean isRented()
    {
        return this.rented;
    }

    public void setRented(boolean rented)
    {
        this.rented = rented;
    }

    public int getPrice()
    {
        return this.price;
    }

}
