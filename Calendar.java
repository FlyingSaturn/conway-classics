class Calendar
{
    int date[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,31};
    String mon[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    int weekd = 0, month = 1, day = 1;
    long year1 = 0, year = 0;
    boolean isLeapYear = false;  
    void P()
    {  
        int i; 
        //System.out.println("=================================================================================================================================================");
        (new CalendarGUI()).TextCal.append("\n\n\t\t\t\t\tCalendar: " + Math.abs(year1));

        /*if(year1 > 0)
        (new CalendarGUI()).TextCal.append("\nCE");//System.out.println(" CE");
        else
        (new CalendarGUI()).TextCal.append("\nBCE");*/
        (new CalendarGUI()).TextCal.append("\n");
        (new CalendarGUI()).TextCal.append("==================================================================================================================\n");
        for(month = 1; month <= 12; month++)
        {
            (new CalendarGUI()).TextCal.append("\n\t" + (mon[(month - 1)]));

            (new CalendarGUI()).TextCal.append("\t--------------------");
            (new CalendarGUI()).TextCal.append("\n\tSUN\tMON\tTUE\tWED\tTHU\tFRI\tSAT");
            (new CalendarGUI()).TextCal.append("\n");
            (new CalendarGUI()).TextCal.append("\t");
            for(i = 1; i <= weekd; i++)
                (new CalendarGUI()).TextCal.append("\t");
            day = 1;
            while(day <= (date[(month - 1)]))
            {
                (new CalendarGUI()).TextCal.append(String.valueOf(day));
                if(((int) (day / 10)) == 0)
                    (new CalendarGUI()).TextCal.append("\t");
                else
                    (new CalendarGUI()).TextCal.append("\t");
                weekd = (weekd + 1) % 7;
                if(weekd == 0)
                {
                    if(!(day == (date[(month - 1)])))
                    {
                        (new CalendarGUI()).TextCal.append("\n");
                        (new CalendarGUI()).TextCal.append("\t");
                    }
                }
                day++;
            }
            (new CalendarGUI()).TextCal.append("\n");
        }
    }

    void WeekdayDetermine()
    {//for Jan 1st
        int  centuryc = 0, ccode = 0, a = 0, b = 0, c = 0, d = 0, dday = 0, ddigit = 0;
        long century = 0;
        if(year == 0)
        {
            System.out.println("Invalid year !!");
            System.exit(0);
        }
        if(year < 0)
        year += 1;
        century = (long) (year / 100);
        centuryc = (int) (century % 4);
        ccode = (centuryc == 0) ? 2 : (centuryc == 1) ? 0 : (centuryc == 2) ? 5 : 3;
        ddigit = (int) (year % 100);
        if(ddigit == 0)
        {
            if(year % 400 == 0)
                isLeapYear = true;
        }
        else
        {
            if(year % 4 == 0)
                isLeapYear = true;
        }              
        a = (int) (ddigit / 12);
        b = ddigit % 12;
        c = (int) (b / 4);
        if(isLeapYear == true)         
        {
            /*dday= date[(month - 1)] [3];*/
            d = -3;//Jan 4 is a doomsday in a leap year
            date[1] = 29;
        }
        else
        /*dday= date[(month - 1)] [2];*/
            d = -2;//Jan 3 is a doomsday
        /*d = day - dday;*/
        int asdf = (int)(ccode + a + b + c + d);
        weekd = (int)(Math.abs(7) + (asdf%7.0)) % 7;
        /*
        In Java, -1 % 7 = -1. "%" is called the "modulus" operator, not the "remainder" operator. 
        An elegant approach is to do as follows, where d = divisor, D= dividend:
        Remainder = [|d| + (D % d)] % d
        */
    }

}//End of Calendar Algorithm

