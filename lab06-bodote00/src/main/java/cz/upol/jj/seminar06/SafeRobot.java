package cz.upol.jj.seminar06;

import java.util.Optional;

    public class SafeRobot
    {
        public final String name;
        public final int memorySize;
        public final Stack<Action> stack;

        SafeRobot(String name, int memorySize)
        {
            this.name = name;
            this.memorySize = memorySize;
            this.stack = new Stack<Action>(memorySize);
        }

        public boolean readActions(Action[] actions)
        {
            this.stack.emptyStack();
            int i;
            for (i = 0; i <= actions.length - 1; i++)
            {
                boolean result = this.stack.safePush(actions[i]);
                if (result)
                {
                    continue;
                }
                else
                {
                    this.stack.emptyStack();
                    return false;
                }
            }
            return true;
        }

        public Optional<Action> move()
        {
            return this.stack.safePop();
        }

        public Optional<Action> nextStep()
        {
            return this.stack.safePeek();
        }

        public String toString()
        {
            StringBuilder str = new StringBuilder();
            try {
                str.append("Name: " + this.name + ", Action: ");
                if (!this.stack.isEmpty())
                {
                    str.append(this.stack.peek().getCode() + ":" + this.stack.peek().getPrice());
                } else
                {
                    str.append("NONE");
                }

            } catch (EmptyStackException exception)
            {
                exception.printStackTrace();
            }

            return str.toString();
        }

        public boolean hasProgram()
        {
            return (!this.stack.isEmpty());
        }
    }
