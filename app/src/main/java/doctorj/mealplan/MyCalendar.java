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
        if((endYear % 4) == 0)
            daysInMonths[1] = 29;
        endMonth = endMonth + (12 * (endYear - startYear));
        int monthCount = startMonth;
        boolean yearCheck = false;
        while(((monthCount < endMonth)||(startDay < endDay))&&(startYear <= endYear))
        {
            num++;
            startDay++;
            if((daysInMonths[monthCount % 12] - (startDay-1)) <= 0)
            {
                //startMonth = (startMonth % 12);
                monthCount++;
                startDay = 1;
                if((!yearCheck) && ((monthCount % 12) == 0)) {
                    yearCheck = true;
                    startYear++;
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
        return(getMonthString(month) + " " + day + ", " + year);
    }

    public String getMonthString(int mI){
        String monthString;
        switch (mI) {
            case 0:  monthString = "January";
                break;
            case 1:  monthString = "February";
                break;
            case 2:  monthString = "March";
                break;
            case 3:  monthString = "April";
                break;
            case 4:  monthString = "May";
                break;
            case 5:  monthString = "June";
                break;
            case 6:  monthString = "July";
                break;
            case 7:  monthString = "August";
                break;
            case 8:  monthString = "September";
                break;
            case 9: monthString = "October";
                break;
            case 10: monthString = "November";
                break;
            case 11: monthString = "December";
                break;
            default: monthString = "Invalid month";
                break;
        }
        return monthString;
    }
}
