package cz.upol.jj.seminar5;

import java.lang.Iterable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Math;
import java.util.Iterator;

public class Rental <T extends Item & Comparable<T>> implements Iterable
{
    public float cash = 0;
    public final List<RentableItem<T>> rentalList = new ArrayList<>();

    public ArrayList<RentableItem<T>> getItems()
    {
        ArrayList<RentableItem<T>> finalArray = new ArrayList<>();
        for (RentableItem<T> rentalItem: this.rentalList)
        {
            finalArray.add(rentalItem);
        }
        return finalArray;
    }

    public void sort()
    {
        Collections.sort(this.rentalList, new Comparator<RentableItem<T>>()
        {
            public int compare(RentableItem<T> object1, RentableItem<T> object2)
            {
                return object1.compareTo(object2);
            }
        });
    }

    public void sortByPrice()
    {
        Collections.sort(this.rentalList, new Comparator<RentableItem<T>>()
        {
            public int compare(RentableItem<T> object1, RentableItem<T> object2)
            {
                return Math.round(object1.getPrice() - object2.getPrice());
            }
        });
    }

    public RentableItem<T> add(T item, int price)
    {
        RentableItem<T> rentableItem = new RentableItem<>(item,price);
        if (this.rentalList.add(rentableItem))
        {
            return rentableItem;
        }
        else
        {
            return null;
        }
    }

    public boolean remove(RentableItem<T> rentableItem)
    {
        return this.rentalList.remove(rentableItem);
    }

    public RentableItem<T> find(T item, boolean rented)
    {
        for (RentableItem<T> rentalItem: this.rentalList)
        {
            if ((rentalItem.isRented() == rented) && (rentalItem.getItem().equals(item)))
            {
                return rentalItem;
            }
        }
        return null;
    }

    public int count(T item)
    {
        int counter = 0;
        for (RentableItem<T> rentalItem: this.rentalList)
        {
            if ((rentalItem.getItem().equals(item)) && (!rentalItem.isRented()))
            {
                counter = counter + 1;
            }
        }
        return counter;
    }

    public RentableItem<T> rent(T item)
    {
        RentableItem<T> rentedItem = find(item, false);
        if (rentedItem != null)
        {
            rentedItem.setRented(true);
            this.cash += rentedItem.getPrice();
        }
        else if (rentedItem == null)
        {
            return null;
        }
        return rentedItem;
    }

    public boolean retrieve(RentableItem<T> rentableItem)
    {
        for (RentableItem<T> rentalItem: this.rentalList)
        {
            if (rentalItem.equals(rentableItem))
            {
                rentableItem.setRented(false);
            }
        }
        return false;
    }

    public float getCash()
    {
        return this.cash;
    }

    public Iterator<RentableItem<T>> iterator()
    {
        return this.rentalList.iterator();
    }
}