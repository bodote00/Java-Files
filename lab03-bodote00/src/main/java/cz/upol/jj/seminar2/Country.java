package cz.upol.jj.seminar2;

public class Country
{
    String name;
    String phoneCode;

    public Country(String name, String phoneCode)
    {
        this.name = name;
        this.phoneCode = phoneCode;
    }

    public void print()
    {
        System.out.println("Stat{jmeno=" + name + ", predvolba=" + phoneCode + "}");
    }
}
