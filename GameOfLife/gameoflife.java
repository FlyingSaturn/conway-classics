class gameoflife
{
    String A[][] = new String[15][15];
    String B[][] = new String[15][15]; // you can choose the subscript of your choice
    // To initialize and to show the starting screen
    void initialize() throws InterruptedException
    {
        System.out.print('\f'); // clearing the screen
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A[0].length; j++)
            {
                if (Math.round(Math.random() * .75) == 1) // .75 was just an arbitrary number to empty the space a bit
                    A[i][j] = "⬛";
                else
                    A[i][j] = "⬜";
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("Starting");
        Thread.sleep(1000);//1 s
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
    }
    
		 // Clears the screen
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // the main part of the code: can a block(ides, jdes) be given life?
    boolean breedable(int ides, int jdes)
    {
        int neighbours = 0;
        for (int i = ides - 1; i <= ides + 1; i++)
        {
            for (int j = jdes - 1; j <= jdes + 1; j++)
                if ((i != ides || j != jdes) && j > -1 && j < A[0].length && i > -1 && i < A.length)
                { // neighbours. neighbours can't have negative indexes or indexes beyond range
                // but the coords have to be different from the main block (ides, jdes)
                    if (isAlive(i, j))
                        neighbours++;
                }
        }
        if ((neighbours == 2 || neighbours == 3) && isAlive(ides, jdes)) // the rule of game of life!!!
            return true; // "keep them alive." The change variable in void display() could've been avoided if I did something for this
        else if (neighbours == 3 && !isAlive(ides, jdes))
            return true; // create...
        else
            return false;
    }
    // is the specific block even alive in the first place?
    boolean isAlive(int i, int j)
    {
        if (A[i][j].equals("⬛"))
            return true;
        return false;
    }
    // handles what should be done to the block after knowing if the block is breedable
    boolean birthAndDeath(int ides, int jdes)
    {
        boolean wasThisNeeded = false;
        boolean breed = breedable(ides, jdes);
        if (!breed && A[ides][jdes].equals("⬛"))
        {    
            B[ides][jdes] = "⬜";
            wasThisNeeded = true;
        }
        else if (breed && A[ides][jdes].equals("⬜"))
        {
            B[ides][jdes] = "⬛";
            wasThisNeeded = true;
        }
        return wasThisNeeded; // for the variable change in void display()
    }
    // displays the blocks continuously
    void display() throws InterruptedException
    {
        boolean change;
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A[0].length; j++)
                B[i][j] = A[i][j];
        }
        do
        {
            System.out.print('\f');
            change = false;
            for (int i = 0; i < A.length; i++)
            {
                for (int j = 0; j < A[0].length; j++)
                {
                    if (birthAndDeath(i, j)) // if it isn't in the same state like in the previous iteration
                        change = true;
                }
            }
            for (int i = 0; i < A.length; i++)
            {
                for (int j = 0; j < A[0].length; j++)
                {
                    A[i][j] = B[i][j];
                    System.out.print(B[i][j] + " ");
                }
                System.out.println();
            }          
            Thread.sleep(105); // 105 ms
        } while(change); // if the last step is the same as the current step, then the code will end
    }
    // main() method
    public static void main(String args[]) throws InterruptedException
    {
        gameoflife obj = new gameoflife();
        obj.initialize();
        obj.display();
    }
}