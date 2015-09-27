package com.bitarts.common.util;

import com.bitarts.parsian.service.factory.InsuranceServiceFactory;
import com.ghasemkiani.util.DateFields;
import com.ghasemkiani.util.SimplePersianCalendar;
import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 5/1/11
 * Time: 2:43 PM
 */
public class DateUtil {
    private static String getFormattedDate(DateFields dateFields) {
        String year = String.valueOf(dateFields.getYear());
        String month = dateFields.getMonth() < 9 ?
                "0" + String.valueOf(dateFields.getMonth() + 1) :
                String.valueOf(dateFields.getMonth() + 1);
        String day = dateFields.getDay() < 10 ?
                "0" + String.valueOf(dateFields.getDay()) :
                String.valueOf(dateFields.getDay());
        return year + "/" + month + "/" + day;
    }

    private static String[] adapteAlignement(String[] stringArray) {
        if (stringArray.length != 3) return stringArray;
        if (stringArray[0].length() < stringArray[2].length()) {
            String temp = stringArray[0];
            stringArray[0] = stringArray[2];
            stringArray[2] = temp;
        }
        return stringArray;
    }

    public static String getCurrentDate() {
        DateFields dateFields = new SimplePersianCalendar().getDateFields();
        return getFormattedDate(dateFields);
    }

    public static String getCurrentTime() {
        Date date = new Date();
        String hours = date.getHours() < 10 ? "0" + String.valueOf(date.getHours()) : String.valueOf(date.getHours());
        String minutes = date.getMinutes() < 10 ? "0" + String.valueOf(date.getMinutes()) : String.valueOf(date.getMinutes());
        return hours + ":" + minutes;
    }

    public static String addYear(String date, int year) {
        String[] dateFilelsStr = adapteAlignement(date.split("/"));
        int yearInt = Integer.parseInt(dateFilelsStr[0]);
        int monthInt = Integer.parseInt(dateFilelsStr[1]) - 1;
        int dayInt = Integer.parseInt(dateFilelsStr[2]);
        yearInt = yearInt + year;
        DateFields dateFields = new DateFields(yearInt, monthInt, dayInt);
        return getFormattedDate(dateFields);
    }

    public static String addMonth(String date, int month) {
        String[] dateFilelsStr = adapteAlignement(date.split("/"));
        int yearInt = Integer.parseInt(dateFilelsStr[0]);
        // monthint is zero indexed
        int monthInt = Integer.parseInt(dateFilelsStr[1]) - 1;
        int dayInt = Integer.parseInt(dateFilelsStr[2]);
        if (((month % 12) + monthInt) < 12 && ((month % 12) + monthInt) >= 0) {
            monthInt += (month % 12);
            yearInt += month / 12;
        } else if (((month % 12) + monthInt) < 0) {
            int yearCount = (month / 12) - 1;
            monthInt += month - 12 * yearCount;
            yearInt += yearCount;
        } else {
            int yearCount = (month / 12) + 1;
            monthInt += month - 12 * yearCount;
            yearInt += yearCount;
        }
        // fix for aghsat
        if(monthInt > 5 && dayInt > 30)
            dayInt = 30;
        if (monthInt == 11 && dayInt > 29)
            dayInt = 29;
        DateFields dateFields = new DateFields(yearInt, monthInt, dayInt);
        return getFormattedDate(dateFields);
    }

    public static String addDays(String date, int days) {
        String[] dateFilelsStr = adapteAlignement(date.split("/"));
        int yearInt = Integer.parseInt(dateFilelsStr[0]);
        int monthInt = Integer.parseInt(dateFilelsStr[1]) - 1;
        int dayInt = Integer.parseInt(dateFilelsStr[2]);
        DateFields dateFields = new DateFields(yearInt, monthInt, dayInt);
        SimplePersianCalendar spcDate = new SimplePersianCalendar();
        spcDate.setDateFields(dateFields);
        long added = days * (1000L * 60 * 60 * 24);
        spcDate.setTimeInMillis(spcDate.getTime().getTime() + added);
        return getFormattedDate(spcDate.getDateFields());
    }

    public static String addDaysWithTatilat(String date, int days) {
        try {
            int tedadRooz = 0;
            int tedadRooz2 = 0;
            if (days > 0){
                tedadRooz = InsuranceServiceFactory.getWithoutSessionAsnadeSodorService().getTatilatFromTwoDate(date,addDays(date,days));
                while (true) {
                    tedadRooz2 = InsuranceServiceFactory.getWithoutSessionAsnadeSodorService().getTatilatFromTwoDate(date,addDays(date,days+tedadRooz));
                    if (tedadRooz != tedadRooz2)
                        tedadRooz = tedadRooz2;
                    else
                        break;
                }
                return addDays(date,days+tedadRooz2);
            }
            else{
                tedadRooz = InsuranceServiceFactory.getWithoutSessionAsnadeSodorService().getTatilatFromTwoDate(addDays(date,days),date);
                while (true) {
                    tedadRooz2 = InsuranceServiceFactory.getWithoutSessionAsnadeSodorService().getTatilatFromTwoDate(addDays(date,days-tedadRooz),date);
                    if (tedadRooz != tedadRooz2)
                        tedadRooz = tedadRooz2;
                    else
                        break;
                }
                return addDays(date,days-tedadRooz2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String minusDays(String date, int days) {
        String[] dateFilelsStr = adapteAlignement(date.split("/"));
        int yearInt = Integer.parseInt(dateFilelsStr[0]);
        int monthInt = Integer.parseInt(dateFilelsStr[1]) - 1;
        int dayInt = Integer.parseInt(dateFilelsStr[2]);
        DateFields dateFields = new DateFields(yearInt, monthInt, dayInt);
        SimplePersianCalendar spcDate = new SimplePersianCalendar();
        spcDate.setDateFields(dateFields);
        spcDate.setTimeInMillis(spcDate.getTime().getTime() - (days * (1000 * 60 * 60 * 24)));
        return getFormattedDate(spcDate.getDateFields());
    }

    public static int getTimeDifferenceByDayCount(String fromDate, String toDate) {
        Calendar fromCalendar = getPersianCalendar(fromDate);
        Calendar toCalendar = getPersianCalendar(toDate);
        return (int) Math.round((fromCalendar.getTimeInMillis() - toCalendar.getTimeInMillis()) / (1000.0 * 60 * 60 * 24));
    }

    public static String getTimeDifferenceByStringDescription(String fromDate, String toDate) {
        String[] fromDateStr = adapteAlignement(fromDate.split("/"));
        String[] toDateStr = adapteAlignement(toDate.split("/"));
        if (fromDateStr.length != 3 || toDateStr.length != 3) {
            return "-";
        }
        int yearDiff = (Integer.parseInt(fromDateStr[0]) - Integer.parseInt(toDateStr[0]));
        int monthDiff = (Integer.parseInt(fromDateStr[1]) - Integer.parseInt(toDateStr[1]));
        int dayDiff = (Integer.parseInt(fromDateStr[2]) - Integer.parseInt(toDateStr[2]));
        if (dayDiff < 0) {
            dayDiff += 30;
            if (Integer.parseInt(fromDateStr[1]) < 8) {
                dayDiff++;
            }
            monthDiff--;
        }
        if (monthDiff < 0) {
            monthDiff += 12;
            yearDiff--;
        }
        String result = "";
        if (yearDiff > 0) {
            result = yearDiff + " سال";
        }
        if (monthDiff > 0) {
            if (result.equalsIgnoreCase("")) {
                result += monthDiff + " ماه";
            } else {
                result += " و " + monthDiff + " ماه";
            }
        }
        if (dayDiff > 0) {
            if (result.equalsIgnoreCase("")) {
                result += dayDiff + " روز";
            } else {
                result += " و " + dayDiff + " روز";
            }
        }
        if (result.equals("")) {
            result = "0 روز";
        }
        return result;
    }

    public static boolean isGreaterThanOrEqual(String currentDate, String targetDate) {
        Calendar spcCurrent = getPersianCalendar(currentDate);
        Calendar spcTarget = getPersianCalendar(targetDate);
        if (spcCurrent.getTimeInMillis() >= spcTarget.getTimeInMillis()) {
            return true;
        } else {
            return false;
        }
    }

    private static Calendar getPersianCalendar(String date) {
        String[] currentDateStr = adapteAlignement(date.split("/"));
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Tehran");
        ULocale uLocale = ULocale.createCanonical("en_US");
        Calendar spcCurrent = new PersianCalendar(timeZone, uLocale);
        spcCurrent.set(Calendar.EXTENDED_YEAR, Integer.parseInt(currentDateStr[0]));
        spcCurrent.set(Calendar.MONTH, Integer.parseInt(currentDateStr[1]) - 1);
        spcCurrent.set(Calendar.DAY_OF_MONTH, Integer.parseInt(currentDateStr[2]));
        spcCurrent.set(Calendar.HOUR_OF_DAY, 0);
        spcCurrent.set(Calendar.MINUTE, 0);
        spcCurrent.set(Calendar.SECOND, 0);
        spcCurrent.set(Calendar.MILLISECOND, 0);
        return spcCurrent;
    }

    public static boolean isGreaterThan(String currentDate, String targetDate) {
        Calendar spcCurrent = getPersianCalendar(currentDate);
        Calendar spcTarget = getPersianCalendar(targetDate);
        if (spcCurrent.getTimeInMillis() > spcTarget.getTimeInMillis()) {
            return true;
        } else {
            return false;
        }
    }

    public static Integer extractYear(String date) {
        return Integer.parseInt(date.split("/")[0]);
    }

    public static String getAvaleMah() {
        final String s[] = getCurrentDate().split("/");
        return s[0] + "/" + s[1] + "/" + "01";
    }

    public static Integer extractMonth(String date) {
        return Integer.parseInt(date.split("/")[1]);
    }

    public static boolean between(String tarikh, String azTarikh, String taTarikh) {
        if (tarikh.isEmpty()) return false;
        return isGreaterThan(taTarikh, tarikh) && (azTarikh.isEmpty() || isGreaterThanOrEqual(tarikh, azTarikh));
    }
    public static boolean betweenEq(String tarikh, String azTarikh, String taTarikh) {
        if (tarikh.isEmpty()) return false;
        return isGreaterThanOrEqual(taTarikh, tarikh) && (azTarikh.isEmpty() || isGreaterThanOrEqual(tarikh, azTarikh));
    }

    public static int getBimeYear(String tarikh, String tarikheShoru) {
        // starts from 0
        if(tarikheShoru.equals(tarikh))
            return 0;

        if (isGreaterThan(tarikheShoru, tarikh)) //Error
            return -1;

        int bimeYear = -1;
        String tarikhCounter = tarikheShoru;
        while (isGreaterThanOrEqual(tarikh, tarikhCounter)) {
            bimeYear++;
            tarikhCounter = addYear(tarikhCounter, 1);
        }

        return bimeYear;
    }
}
