package cz.upol.jj.seminar2;

public class Person
{
    int id;
    String firstname;
    String lastname;
    int age;
    String phone;
    Country country;

    public Person(int id, String firstname, String lastname, int age, String phone, Country country)
    {
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;
            this.age = age;
            this.phone = phone;
            this.country = country;
    }

    public String getStatus()
    {
        if (age < 18)
        {
            return "junior";
        }
        else if (age >= 65)
        {
            return "senior";
        }
        else return "dospely";
    }

    public String getPhone()
    {
        String helper;
        if(country == null)
        {
            helper = phone;
        }
        else
        {
            helper = country.phoneCode + phone;
        }
        return helper;
    }

    public void print()
    {
        String helper;
        if (country == null)
        {
            helper = "neznamy";
        }
        else
        {
            helper = country.name;
        }
        System.out.print("Osoba{id=" + id + ", jmeno=" + firstname + ", prijmeni=" + lastname + ", vek=" + age + ", telefon=" + getPhone() + ", status=" + getStatus() + ", stat=" + helper + "}" + System.lineSeparator());
    }
}
