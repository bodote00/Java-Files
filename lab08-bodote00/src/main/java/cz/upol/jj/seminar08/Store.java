package cz.upol.jj.seminar08;

import java.util.Set;
import java.util.Comparator;
import java.util.Optional;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

public class Store
{
    public List<Item> items;

    public Store(List<Item> items)
    {
        this.items = items;
    }

    public List<Item> getItems()
    {
        return items;
    }

    public boolean hasItem(String name)
    {
        Stream<Item> itemStream = getItems().stream();
        List<Item> itemsList = itemStream.filter(x -> x.getName().equals(name)).
                toList();

        if (!itemsList.isEmpty())
            return true;
        else return false;
    }

    public int countItems(String name, Type type)
    {
        Stream<Item> itemStream = getItems().stream();
        return itemStream.filter(x -> x.getName().equals(name) &&
                x.getType().equals(type)).mapToInt(Item::getCount).sum();
    }

    public double getTotalPrice()
    {
        Stream<Item> itemStream = getItems().stream();
        return itemStream.mapToDouble(x -> x.getCount() * x.getPrice()).sum();
    }

    public double getTotalPriceOfType(Type type)
    {
        Stream<Item> itemStream = getItems().stream();
        return itemStream.filter(x -> x.getType().equals(type))
                .mapToDouble(x -> x.getPrice() * x.getCount()).sum();
    }

    public int countTotalItems()
    {
        Stream<Item> itemStream = getItems().stream();
        return itemStream.mapToInt(Item::getCount).sum();
    }

    public int countTypes()
    {
        Stream<Item> itemStream = getItems().stream();
        List<Item> itemsList = itemStream.filter(distinctByKey(Item::getType)).toList();
        return itemsList.size();
    }

    public <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor)
    {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public Stream<Item> cheaperThan(double price)
    {
        Stream<Item> itemStream = getItems().stream();
        return itemStream.filter(x -> x.getPrice() < price);
    }

    public Optional<Item> mostExpensive()
    {
        Stream<Item> itemStream = getItems().stream();
        return itemStream.max(Comparator.comparing(Item::getPrice));
    }
}

