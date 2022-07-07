package cz.upol.jj.seminar07;

import java.util.Objects;
import java.lang.Float;

public record Order(int id, String customer, String product, float prise, int quantity)
{

    public int id()
    {
        return this.id;
    }

    public String getCustomer()
    {
        return this.customer;
    }

    public String getProduct()
    {
        return this.product;
    }

    public float getPrise()
    {
        return this.prise;
    }


    public int getQuantity()
    {
        return this.quantity;
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "id=" + this.id +
                ", customer='" + this.customer + '\'' +
                ", product='" + this.product + '\'' +
                ", price=" + this.prise +
                ", quantity=" + this.quantity +
                '}';
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (getClass() != object.getClass())
        {
            return false;
        }
        Order order = (Order) object;
        return this.id == order.id && Float.compare(order.prise, this.prise) == 0 && this.quantity == order.quantity && Objects.equals(this.customer, order.customer) && Objects.equals(this.product, order.product);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id, this.customer, this.product, this.prise, this.quantity);
    }

}
