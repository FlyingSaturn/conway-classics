/*
 * Author: FlyingSaturn
 * Version: May 12, 2024
 * I have excluded the year 0 as mentioned in -
    https://en.wikipedia.org/wiki/Year_zero
 * You should visit-
    https://www.wikihow.com/Calculate-the-Day-of-the-Week

 * This code's motivation:
 * "To improve his speed, he [practiced] his calendrical calculations on his computer, 
 * which [was] programmed to quiz him with random dates every time he [logged] on."
 * (from https://www.scientificamerican.com/article/not-just-fun-and-games/)

 * (Also, I know that this is not good code, but I am encountering this for the very first time.)
 */

 import java.util.*;
 class DayQuizzer extends DayFinder
 {
    int day, month; 
    long year1;

    // main()
    public static void main(String args[])
    {   
        DayQuizzer obj = new DayQuizzer();
        for (int j = 0; j < 5; j++)
        {
            System.out.print("\n" + (j + 1) + ". ");
            obj.inputOutput();
        }
    }

    // To get random dates between 1 AD and 5000 AD 
    String randomizer()
    {
        year1 = (int) (Math.random() * (5000 - 1000) + 1000);
        month = (int) (Math.random() * (12 - 1) + 1);
        int cap = LEAAP(year1) ? 1 : 0;
        day = (int)(Math.random() * (date[month - 1] [cap] - 1) + 1);
        return (day + "/" + month + "/" + year1);
    }    

    // To input the quiz answer
    void inputOutput()
    {
        Scanner sc = new Scanner(System.in);
        int uweek = 0; // user week
        outer:do
        {
            System.out.print("What is the week day of " + randomizer() + "?\nAnswer: ");
            String abc = (sc.nextLine()).toLowerCase();
            for (int i = 0; i < 7; i++)
            {
                boolean b = (week[i].startsWith(abc) && !(abc.equals("t") || abc.equals("s")));
                if(abc.length() == 1 && Character.isDigit(abc.charAt(0)))
                    b = Integer.parseInt(abc) % 7 == i;
                if (b)
                {
                    uweek = i;
                    break outer;
                }
            }
        }
        while (true);
        int cweek = answer(day, month, year1); // computer week
        if(cweek == uweek)
        System.out.println("\nCorrect!\n");
        else
        System.out.println("\nWrong!\n");
        System.out.println("Your answer: " + capitalize(week[uweek]));
        System.out.println("Correct answer: " + capitalize(week[cweek]));
    }
 }