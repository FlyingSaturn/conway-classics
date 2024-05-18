/*
 * Author: FlyingSaturn
 * Versions: March 28, 2020; May 12, 2024
 * Note: Year 0 is invalid because after 1 BCE, there is 1 CE.
 */

 import java.util.*;
 class Calendar extends DayFinder
 {
    int weekd = 0, month = 1, day = 1;
    long year1 = 0, year = 0;
    boolean isLeapYear = false;  

    //main() method
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        Calendar obj = new Calendar();
        do
        {
        System.out.print("Enter a year: ");
        obj.year1 = sc.nextLong();
        } while (obj.year1 == 0);
        obj.year = obj.year1;
        System.out.println('\f'); // For clearing the screeen; change it as you wish
        obj.weekd = obj.answer(obj.day, obj.month, obj.year); // Just finding the week day of Jan 1 of that year
        obj.isLeapYear = obj.LEAAP(obj.year);
        obj.P();
    }

    // To print the calendar
    void P()
    {  
        int i; 
        System.out.println("=================================================================================================================================================");
        System.out.print("\n\n\t\t\t\t\t\t\t\tCalendar " + Math.abs(year1));
        if(year1 > 0)
            System.out.println(" CE");
        else
            System.out.println(" BCE");
        System.out.println("\n\n=================================================================================================================================================\n\n\n");
        for(month = 1; month <= 12; month++)
        {
            System.out.println('\t' + (mon[(month - 1)]));
            System.out.println("\t--------------------");
            System.out.println("\tS  M  T  W  T  F  S");
            System.out.print('\t');
            for(i = 1; i <= weekd; i++)
                System.out.print("   ");
            day = 1;
            if(isLeapYear)
            {
                while(day <= (date[(month - 1)] [1]))
                {
                    System.out.print(day);
                    if(((int) (day / 10)) == 0)
                        System.out.print("  ");
                    else
                        System.out.print(" ");
                    weekd = (weekd + 1) % 7;
                    if(weekd == 0)
                    {
                        if(!(day == (date[(month - 1)] [1])))
                        {
                            System.out.println();
                            System.out.print('\t');
                        }
                    }
                    day++;
                }
            }
            else
            {
                while(day <= (date[(month - 1)] [0]))
                {
                    System.out.print(day);
                    if(((int) (day / 10)) == 0)
                        System.out.print("  ");
                    else
                        System.out.print(" ");
                    weekd = (weekd + 1) % 7;
                    if(weekd == 0)
                    {
                        if(!(day == (date[(month - 1)] [0])))
                        {
                            System.out.println();
                            System.out.print('\t');
                        }
                    }
                    day++;
                }
            }
            System.out.println('\n');
        }
    }
 }
 