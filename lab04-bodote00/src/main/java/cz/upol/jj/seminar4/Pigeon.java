package cz.upol.jj.seminar4;

public class Pigeon extends Animal implements Sender
{
    public Human owner;

    public Pigeon(String name, int age, Human owner)
    {
        super(name, age);
        this.owner = owner;
    }

    public boolean sendMessage(String message, Receiver receiver)
    {
        Message message_ = new Message(this, message);
        receiver.receive(message_);
        return true;
    }
}
