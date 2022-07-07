package cz.upol.jj.seminar06;

    public class ExceptionalRobot
    {
        public final String name;
        public int memorySize;
        public final Stack<Action> stack;

        ExceptionalRobot(String name, int memorySize)
        {
            this.name = name;
            this.memorySize = memorySize;
            this.stack = new Stack<Action>(memorySize);
        }

        public void readActions(Action[] actions) throws TooManyActionsException
        {
            int i;
            for(i = 0; i < actions.length; i++)
            {
                try
                {
                    this.stack.push(actions[i]);
                }
                catch (FullStackException exception)
                {
                    this.stack.emptyStack();
                    throw new TooManyActionsException(exception);
                }
            }
        }

        public Action move() throws NoActionAvailableException
        {
            try
            {
                Action action = this.stack.pop();
                return action;
            }
            catch (EmptyStackException exception)
            {
                throw new NoActionAvailableException(exception);
            }
        }

        public Action nextStep() throws NoActionAvailableException
        {
            try
            {
                Action result = this.stack.peek();
                return result;

            } catch (EmptyStackException exception)
            {
                throw new NoActionAvailableException(exception);
            }
        }

        @Override
        public String toString()
        {
            StringBuilder str = new StringBuilder();
            try
            {
                str.append("Name: " + this.name + ", Action: ");
                if (!this.stack.isEmpty())
                {
                    str.append(this.stack.peek().getCode() +":"+ this.stack.peek().getPrice());
                }
                else if (this.stack.isEmpty())
                {
                    str.append("NONE");
                }
            }
            catch (EmptyStackException exception)
            {
                exception.printStackTrace();
            }

            return str.toString();
        }

        public boolean hasProgram()
        {
            return (this.stack.isEmpty() == false);
        }

    }


