package cz.upol.jj.seminar07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Short;
import java.lang.System;

public class Client
{
    public OrdersDatabase ordersDatabase = null;
    public OrdersDatabase getDatabase()
    {
        return ordersDatabase;
    }

    void loop()
    {
        short userInput;
        boolean isInProgress = true;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        OrdersDataStream ordersDataStream = new OrdersDataStream();

        while (isInProgress)
        {
            try
            {
                System.out.println("Actions: 0 (exit), 1 (connect), 2 (print), 3 (load next order), 4 (remove order)");
                userInput = Short.parseShort(bufferedReader.readLine());

                System.out.println(userInput);
                switch(userInput)
                {
                    case 0:
                        System.out.println("Action: " + userInput);
                        isInProgress = false;
                        break;

                    case 1:
                        System.out.println("Action: " + userInput);
                        ordersDatabase = new OrdersDatabase(bufferedReader.readLine());
                        isInProgress = false;
                        break;

                    case 2:
                        System.out.println("Action: " + userInput);
                        System.out.println(ordersDatabase.toString());
                        break;

                    case 3:
                        System.out.println("Action: " + userInput);
                        ordersDatabase.loadNextOrder();
                        break;

                    case 4:
                        System.out.println("Action: " + userInput);
                        System.out.println("Id: " + userInput);
                        ordersDatabase.removeOrder(userInput);
                        break;

                    default:
                        break;
                }
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        }
    }
}