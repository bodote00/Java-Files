package cz.upol.jj.seminar5;

public class Book extends Item implements Comparable<Book>
{
    public final String name;
    public final String author;

    Book(String name, String author, int year)
    {
        super(year);
        this.name = name;
        this.author = author;
    }

    public int compareTo(Book book)
    {
        return this.author.compareTo(book.author);
    }

    public boolean equals(Object object)
    {
        if ((object == null) || (getClass() != object.getClass()))
        {
            return false;
        }
        Book bookTemp = (Book) object;
        return this.name.equals(bookTemp.name)
                && this.author.equals(bookTemp.author)
                && this.getYear() == bookTemp.getYear();

    }
}

