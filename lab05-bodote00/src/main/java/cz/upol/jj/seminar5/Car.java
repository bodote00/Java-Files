package cz.upol.jj.seminar5;

public class Car extends Item implements Comparable<Car>
{
    public final String manufacturer;
    public final String model;
    public final float price;

    Car(String manufacturer, String model, float price, int year) {
        super(year);
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
    }

    public int compareTo(Car car)
    {
        return Math.round(this.price - car.price);
    }

    public boolean equals(Object object) {

        if ((object == null) || (getClass() != object.getClass()))
        {
            return false;
        }
        Car carTemp = (Car) object;
        return this.manufacturer.equals(carTemp.manufacturer)
                && this.model.equals(carTemp.model)
                && this.price == carTemp.price && this.getYear() == carTemp.getYear();
    }
}
