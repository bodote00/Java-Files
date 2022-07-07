package cz.upol.jj.seminar4;

public record Message(Sender sender, String message)
{
    public String message()
    {
        return message;
    }

    public Sender sender()
    {
        return sender;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Message that = (Message)o;
        return this.message().equals(that.message()) && this.sender.equals(that.sender());
    }
}
