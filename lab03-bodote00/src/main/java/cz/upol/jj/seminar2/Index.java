package cz.upol.jj.seminar2;

public class Index
{
    Person[] persons;
    Country[] countries;

    public Index(Person[] persons, Country[] countries)
    {
        this.persons = persons;
        this.countries = countries;
    }

    public int count(String countryName)
    {
        int counter = 0;
        for (int i = 0; i < persons.length; i++)
        {
            if ((persons[i].country != null) && (persons[i].country.name == countryName))
            {
                counter++;
            }
        }
        return counter;
    }

    public int count(String firstname, String lastname)
    {
        int counter = 0;
        for (int i = 0; i < persons.length; i++)
        {
            if ((persons[i].firstname == firstname) || (persons[i].lastname == lastname))
            {
                counter++;
            }
        }
        return counter;
    }

    Person search(int id)
    {
        for (int i = 0; i < persons.length; i++)
        {
            if (id == persons[i].id)
            {
                return persons[i];
            }
        }
        return null;
    }

    Person[] search(String countryName)
    {
        int counter = 0;
        int helper = count(countryName);
        Person[] rslt = new Person[helper];

        for (int i = 0; i < persons.length; i++)
        {
            if (persons[i].country != null)
            {
                if (persons[i].country.name == countryName)
                {
                    rslt[counter] = persons[i];
                    counter++;
                }
            }
        }
        Person[] rslt2 = new Person[counter];
        for(int i = 0; i < counter; i++)
        {
            rslt2[i] = rslt[i];
        }
        return rslt2;
    }

    Person[] search(String firstname, String lastname)
    {
        int counter = 0;
        int helper = count(firstname, lastname);
        Person [] rslt = new Person[helper];

        for(int i = 0; i < persons.length; i++)
        {
            if((persons[i].firstname == firstname) && (persons[i].lastname == lastname))
            {
                rslt[counter] = persons[i];
                counter++;
            }
        }
        Person[] rslt2 = new Person[counter];
        for(int i = 0; i < counter; i++)
        {
            rslt2[i] = rslt[i];
        }
        return rslt2;

    }

    public boolean setPhoneCode(Person person, String phoneCode)
    {
        for (int i = 0; i < countries.length; i++)
        {
            if (countries[i].phoneCode == phoneCode)
            {
                person.country = countries[i];
                return true;
            }
        }
        return false;
    }

    public void print()
    {
        System.out.print("Rejstrik{" + System.lineSeparator());
        for(int i = 0; i < persons.length; i++)
        {
            System.out.print("Osoba{id=" + i + ", jmeno=" + persons[i].firstname + ", prijmeni=" + persons[i].lastname + ", telefon=" + persons[i].getPhone() + "}" + System.lineSeparator());
        }
        System.out.print("}" + System.lineSeparator());
    }
}
