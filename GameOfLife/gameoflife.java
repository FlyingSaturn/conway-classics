import java.util.*;
public class gameoflife // you may need to omit the public keyword while executing in your IDE
{
    String A[][];
    String B[][];
    static final String BLACK = "\u2b1b ";
    static final String WHITE = "\u2b1c ";
    gameoflife(int n)
    {
        A = new String[n][n];
        B = new String[n][n];
    }
    
    // main() method
    public static void main(String args[]) throws InterruptedException
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the maximum no. of blocks\nyou want to fit in one line\nof the terminal window: ");
        int n = sc.nextInt(); // input may vary based 
        sc.close();
        gameoflife obj = new gameoflife(n);
        obj.clearScreen();
        obj.initialize();
        obj.display();
    }

    // To initialize and to show the starting screen
    void initialize() throws InterruptedException
    {
        clearScreen(); // clearing the screen
        double randomizer = 0.5 / ((2 / 3.0) / 15 * A.length); // 2/3 = 0.6666...
             // For subscript 15, 0.75 * the pseudorandom number had to yield 
             // something greater than or equals 0.5 to be rounded off as 1.
             // Something above 66.666th percentile on the number line...
        for (int i = 0; i < A.length; i++)
        {
            for (int j = 0; j < A[0].length; j++)
            {
                if (Math.round(Math.random() * randomizer) == 1) // .75 was just an arbitrary number to empty the space a bit
                    A[i][j] = BLACK;
                else
                    A[i][j] = WHITE;
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
    
		 // Clears the screen; taken from ChatGPT
    void clearScreen() 
    {
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
        if (A[i][j].equals(BLACK))
            return true;
        return false;
    }
    // handles what should be done to the block after knowing if the block is breedable
    boolean birthAndDeath(int ides, int jdes)
    {
        boolean wasThisNeeded = false;
        boolean breed = breedable(ides, jdes);
        if (!breed && A[ides][jdes].equals(BLACK))
        {    
            B[ides][jdes] = WHITE;
            wasThisNeeded = true;
        }
        else if (breed && A[ides][jdes].equals(WHITE))
        {
            B[ides][jdes] = BLACK;
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
            clearScreen();
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
            Thread.sleep(110); // 110 ms
        } while(change); // if the last step is the same as the current step, then the code will end
    }
}