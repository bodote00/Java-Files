package cz.upol.jj.seminar4;

public class Dog extends Animal implements Receiver
{
    public Human owner;
    public Message lastMessage;

    public Dog (String name, int age, Human owner)
    {
        super(name, age);
        this.owner = owner;

    }

    public Human getOwner()
    {
        return owner;
    }

    public void setOwner(Human owner)
    {
        this.owner = owner;
    }

    public boolean receive(Message message)
    {
        if (message.sender() != owner)
        {
            return false;
        }
        else
        {
            lastMessage = message;
            return true;
        }
    }

    public Message getLastMessage()
    {
        return lastMessage;
    }
}
