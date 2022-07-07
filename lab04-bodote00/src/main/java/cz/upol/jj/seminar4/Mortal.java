package cz.upol.jj.seminar4;

public abstract class Mortal
{
    private int age;
    private boolean alive;

    public Mortal(int age)
    {
        this.age = age;
        this.alive = true;
    }

    public int getAge()
    {
        return age;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void die()
    {
        alive = false;
    }

    public boolean setAge(int vek)
    {
        if ((vek > age) && isAlive())
        {
            this.age = vek;
            return true;
        }
        else
        {
            return false;
        }
    }
}
