/*
 * Author: FlyingSaturn
 * Versions: March 28, 2020; May 12, 2024
 * You will not understand the code unless you watch the video -
   https://www.youtube.com/watch?v=714LTMNJy5M
   I have extended it to BCE.
 * I have excluded the year 0 as mentioned in -
   https://en.wikipedia.org/wiki/Year_zero
 * You should visit-
   https://www.wikihow.com/Calculate-the-Day-of-the-Week
 */



import java.util.*;
class DayFinder
{
    int date[] [] = {{31, 31, 3, 4}, {28, 29, 28, 29}, {31, 31, 14, 14}, {30, 30, 4, 4}, {31, 31, 9, 9}, {30, 30, 6, 6}, {31, 31, 11, 11}, {31, 31, 8, 8}, {30, 30, 5, 5}, {31, 31, 10, 10}, {30, 30, 7, 7},{31, 31, 12, 12}};
    String mon[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String week[] = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
    int month = 1, day = 1;
    long year1 = 0, year = 0;
    boolean isLeapYear = false;

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        long yr1;
        int m, d;
        do
        {
            System.out.print("Enter a year, in -ve (BCE) or in +ve (AD) (no Year Zero): ");
            yr1 = sc.nextLong();
        } 
        while (yr1 == 0);
        do
        {
            System.out.print("Enter a month: ");
            m = sc.nextInt();
        } 
        while (!(m > 0 && m < 13));
        DayFinder obj = new DayFinder();
        int cap = obj.LEAAP(yr1) ? 1 : 0;
        do
        {
            System.out.print("Enter a day: ");
            d = sc.nextInt();
        } 
        while (((((obj.date[(m - 1)] [cap]) < d) || (d <= 0))));
        sc.close();
        int w = obj.answer(d, m, yr1);
        System.out.println("The week day is: " + obj.capitalize(obj.week[w]));
    }

    // Capitalization wasn't done because of comparision issues in DayQuizzer
    String capitalize(String s)
    {
        String s1 = "" + Character.toUpperCase(s.charAt(0));
        s1 += s.substring(1);
        return s1;
    }

    // To check if a year is a leap year or not
    boolean LEAAP(long year)
    {
        int ddigit = (int) (year % 100);
        if (ddigit == 0)
        {
            if (year % 400 == 0)
                return true;
        }
        else if (year % 4 == 0)
            return true;
        return false;  
    }

    // To find the week day based on a date
    int answer(int day, int month, long year1)
    {        
        int centuryc = 0, ccode = 0, a = 0, b = 0, c = 0, d = 0, dday = 0, ddigit = 0;
        int weekd = 0;
        long century = 0;
        long year = year1;
        if (year < 0)
            year += 1;
        century = (long) (year / 100);
        centuryc = (int) (century % 4);
        ccode = (centuryc == 0) ? 2 : (centuryc == 1) ? 0 : (centuryc == 2) ? 5 : 3;
        ddigit = (int) (year % 100);
        isLeapYear = LEAAP(year);
        a = (int) (ddigit / 12);
        b = ddigit % 12;
        c = (int) (b / 4);
        int ddayno = (isLeapYear) ? 3 : 2;
        dday = date[(month - 1)] [ddayno];
        d = day - dday;

        weekd = (7 + (ccode + a + b + c + d) % 7) % 7;
        return weekd;
        //Remainder is not modulus; however, we can calculate remainder 
        //in Java using the following formula where D=dividend and d = divisor:
        //Remainder = (|d| + D % d) % d
    }
}