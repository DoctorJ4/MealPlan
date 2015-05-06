package doctorj.mealplan;

import java.util.Calendar;

/**
 * Created by DSU on 4/30/2015.
 */
public class MyCalendar {
    public final int daysInJanuary = 31; //{31,28,31,30,31,30,31,31,30,31,30,31}
    public final int daysInFebruary = 28; //29 if leap year(divisible by 4)
    public final int daysInMarch = 31;
    public final int daysInApril = 30;
    public final int daysInMay = 31;
    public final int daysInJune = 30;
    public final int daysInJuly = 31;
    public final int daysInAugust = 31;
    public final int daysInSeptember = 30;
    public final int daysInOctober = 31;
    public final int daysInNovember = 30;
    public final int daysInDecember = 31;


    public int getDaysBetweenDates(int startMonth, int startDay, int startYear, int endMonth, int endDay, int endYear)
    {
        int num = 0;
        int[] daysInMonths = new int []{31,28,31,30,31,30,31,31,30,31,30,31};
        if((startYear % 4) == 0)
            daysInMonths[1] = 29;
        endMonth = endMonth + (12 * (endYear - startYear));
        int monthCount = startMonth;
        while(((monthCount < endMonth)||(startDay < endDay))&&(startYear <= endYear))
        {
            num++;
            startDay++;
            if((daysInMonths[startMonth - 1] - (startDay-1)) <= 0)
            {
                startMonth = (startMonth % 12) + 1;
                monthCount++;
                startDay = 1;
                if(startMonth == 1) {
                    startYear++;
                    if((startYear % 4) == 0)
                        daysInMonths[1] = 29;
                    else
                        daysInMonths[1] = 28;
                }
            }

        }
        return num + 1;
    }

    public int getDayOfWeek(int year, int monthBegin, int dayBegin)
    {
        Calendar getDate = Calendar.getInstance();
        getDate.set(year, monthBegin, dayBegin);
        int dayNum = 0;

        switch(getDate.get(Calendar.DAY_OF_WEEK)){
            case Calendar.SUNDAY: dayNum = 0;
                break;
            case Calendar.MONDAY: dayNum = 1;
                break;
            case Calendar.TUESDAY: dayNum = 2;
                break;
            case Calendar.WEDNESDAY: dayNum = 3;
                break;
            case Calendar.THURSDAY: dayNum = 4;
                break;
            case Calendar.FRIDAY: dayNum = 5;
                break;
            case Calendar.SATURDAY: dayNum = 6;
                break;
        }
        return dayNum;
    }

    public String getFullDateString(int month, int day, int year)
    {
        return(getMonthString(month + 1) + " " + day + ", " + year);
    }

    public String getMonthString(int mI){
        String monthString;
        switch (mI) {
            case 1:  monthString = "January";
                break;
            case 2:  monthString = "February";
                break;
            case 3:  monthString = "March";
                break;
            case 4:  monthString = "April";
                break;
            case 5:  monthString = "May";
                break;
            case 6:  monthString = "June";
                break;
            case 7:  monthString = "July";
                break;
            case 8:  monthString = "August";
                break;
            case 9:  monthString = "September";
                break;
            case 10: monthString = "October";
                break;
            case 11: monthString = "November";
                break;
            case 12: monthString = "December";
                break;
            default: monthString = "Invalid month";
                break;
        }
        return monthString;
    }
}
