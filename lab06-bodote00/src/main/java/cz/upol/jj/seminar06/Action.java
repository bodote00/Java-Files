package cz.upol.jj.seminar06;

public enum Action
{
    STEP(0),
    BACK(1),  //toto je podľa warningov zbytočné
    LEFT(2),
    RIGHT(3);

    private int code;
    private short price;

    Action(int i)
    {
        switch (i)
        {
            case 0:
                this.code = 100;
                this.price = 1000;
                break;
            case 1:
                this.code = 200;
                this.price = 1000;
                break;
            case 2:
                this.code = 10;
                this.price = 10;
                break;
            case 3:
                this.code = 20;
                this.price = 10;
                break;
        }
    }

    public int getCode()
    {
        return this.code;
    }

    public int getPrice()
    {
        return this.price;
    }
}
