package cz.upol.jj.seminar4;

public class Human extends Mortal implements Receiver, Sender
{
    private final String name;
    private final String surname;
    private int messageLength = 0;
    private String id;
    private Message[] messages;


    public Human(String name, String surname, String id, int age)
    {
        super(age);
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getFullName()
    {
        return getName() + " " + getSurname();
    }

    public String getID()
    {
        return id;
    }

    public void setID(String id)
    {
        int idLengthHelper = id.length(); //for case id.length() < 6
        if (id.length() < 6)
        {
            StringBuilder idBuilder = new StringBuilder(id);
            int i;
            for(i = 0; i < 6 - idLengthHelper; i++)
            {
                idBuilder.insert(0,"0");
            }
            id = idBuilder.toString();
            this.id = id;
        }
        else if (id.length() == 6)
        {
            this.id = id;
        }
        else
        {
            this.id = id.substring(0, 6);
        }
    }

    public boolean receive(Message message)
    {
        Message[] newArray = new Message[this.messageLength + 1];
        int i = 0;
        while (i < this.messageLength)
        {
            newArray[i] = messages[i];
            i++;
        }
        newArray[messageLength] = message;
        this.messages = newArray;
        messageLength = messageLength + 1;
        return true;
    }

    public boolean sendMessage(String message, Receiver receiver)
    {
        Message message2 = new Message(this, message);
        return receiver.receive(message2);
    }

    public Message[] getMessages()
    {
        int myLength = messages.length;
        Message helper;
        String a;
        String b;
        int i = 0;
        while (i < myLength)
        {
            int j = i + 1;
            while (j < myLength)
            {
                a = messages[i].message();
                b = messages[j].message();

                if(a.compareTo(b) > 0)
                {
                    helper = messages[i];
                    messages[i] = messages[j];
                    messages[j] = helper;
                }
                j++;
            }
            i++;
        }
        return messages;
    }
}
