package cz.upol.jj.seminar07;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.EOFException;
import java.io.IOException;
import java.io.File;

public class OrdersDatabase
{
    public String filename;
    public final File file;
    public OrdersDataStream ordersDataStream = new OrdersDataStream();

    public OrdersDatabase(String filename) throws IOException
    {
        this.filename = filename;
        this.file = new File(filename);
        try
        {
            if (!file.exists())
            {
                file.createNewFile();
            }
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    public OrdersDataStream getOrdersDataStream()
    {
        return ordersDataStream;
    }

    public void setOrdersDataStream(OrdersDataStream ordersDataStream)
    {
        this.ordersDataStream = ordersDataStream;
    }

    public Order loadOrder(DataInput di) throws IOException
    {
        return new Order (di.readInt(), di.readUTF(), di.readUTF(), di.readFloat(), di.readShort());
    }

    public void loadNextOrder() throws IOException
    {
        DataOutputStream output = new DataOutputStream(new FileOutputStream(this.file, true));
        try
        {
            writeOrder(output, loadOrder(this.ordersDataStream));
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {
            output.close();
        }
    }

    public void writeOrder(DataOutputStream output, Order order) throws IOException
    {
        output.writeInt(order.id());
        output.writeUTF(order.getCustomer());
        output.writeUTF(order.getProduct());
        output.writeFloat(order.getPrise());
        output.writeShort(order.getQuantity());
    }

    public void writeOrders(List<Order> orders, boolean append) throws IOException
    {
        DataOutputStream output = new DataOutputStream(new FileOutputStream(this.file, append));
        try
        {
            for (Order order : orders)
                writeOrder(output, order);
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {
            output.close();
        }
    }

    public List<Order> readOrders() throws IOException
    {
        DataInputStream input = new DataInputStream(new FileInputStream(this.file));
        List<Order> orderList = new ArrayList<>();
        try
        {
            while(true)
            {
                orderList.add(getOrder(input));
            }
        }
        catch (EOFException eofException)
        {
            eofException.printStackTrace();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        return orderList;
    }


    public Order getOrder(DataInputStream input) throws IOException
    {
        return new Order (input.readInt(), input.readUTF(), input.readUTF(), input.readFloat(), input.readShort());
    }

    public boolean removeOrder(int id) throws IOException
    {
        List<Order> orderList = readOrders();
        Order removeOrder = null;
        try
        {
            for(Order order : orderList)
            {
                if (order.id() == id)
                {
                    removeOrder = order;
                    break;
                }
            }
            if(removeOrder != null)
            {
                orderList.remove(removeOrder);
                writeOrders(orderList, false);
                return true;
            }
            return false;
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString()
    {
        String result = "";
        try
        {
            for(Order order :readOrders())
            {
                result += order.toString() + System.lineSeparator();
            }
        }
        catch (IOException exception)
        {
            return exception.toString();
        }
        return result;
    }
}