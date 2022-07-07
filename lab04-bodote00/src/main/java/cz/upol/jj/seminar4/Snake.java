package cz.upol.jj.seminar4;

public class Snake extends Animal
{
    public Snake(String name, int age)
    {
        super(name, age);
    }

    public boolean bite(Mortal mortal)
    {
        mortal.die();
        return mortal.isAlive();
    }
}
