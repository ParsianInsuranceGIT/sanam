package com.bitarts.common.util;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Arron2
 * Date: 4/21/11
 * Time: 2:53 PM
 */
public class StringUtil {
    public static String removeNoneDigits(String word) {
        StringBuilder wordOnlyByDigits = new StringBuilder();
        char[] wordChars = word.toCharArray();
        for (char ch : wordChars) {
            if ('0' <= (int) ch && (int) ch <= '9') {
                wordOnlyByDigits.append(ch);
            }
        }
        return wordOnlyByDigits.toString();
    }

    public static String toUnicode(String originalText) {
        String outPutText = new String();
        boolean asciiText = false;
        for (int i = 0; i < originalText.length(); i++)
            if (originalText.substring(i, i + 1).compareTo("0") >= 0 && originalText.substring(i, i + 1).compareTo("9") <= 0) {
                int ii = Character.getNumericValue(originalText.charAt(i)) + 1632;
                char ch = (char) ii;
                outPutText = outPutText + Character.toString(ch);
            } else {
                outPutText = outPutText + originalText.charAt(i);
            }

        return outPutText != null ? outPutText : "";
    }

    public static String toAnsii(String unicodeText) {
        String outPutText = "";
        char c = '\0';
        int i = 0;
        while (true) {
            if (i < unicodeText.length()) {
                c = unicodeText.charAt(i);
                if ((int) c >= 1776 && (int) c <= 1785) //number
                {
                    outPutText = outPutText + (char) (unicodeText.charAt(i) - 1728);
                } else {
                    outPutText = outPutText + c;
                }
                i++;
            } else {
                return outPutText;
            }
        }
    }

    private static final String[] yekan = new String[]{"صفر", "يک", "دو", "سه", "چهار", "پنج", " شش", "هفت", "هشت", "نه"};
    private static final String[] dahTaNozdah = new String[]{"ده", "يازده", "دوازده", "سيزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده"};
    private static final String[] dahgan = new String[]{"", "", "بيست", "سي", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"};
    private static final String[] sadgan = new String[]{"", "يکصد", "دويست", "سيصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد"};
    private static final String[] pasvandeMazrabeHezar = new String[]{"", "هزار", "ميليون", "میلیارد", "تریلیون"};
    private static final String vaav = " و ";

    private static String convertMeghdareAdadi3raghamiBeHorof(String adade3raghami) {
        int adade3raghamiInteger = Integer.parseInt(adade3raghami);
        int d1, d2, d3, d12;
        String result = "";
        d12 = adade3raghamiInteger % 100;
        d3 = adade3raghamiInteger / 100;
        if (d3 != 0) result = sadgan[d3] + vaav;
        if (d12 >= 10 && d12 <= 19) {
            result += dahTaNozdah[d12 - 10];
        } else {
            d1 = d12 % 10;
            d2 = d12 / 10;
            if (d2 != 0) result += dahgan[d2] + vaav;
            if (d1 != 0) result += yekan[d1] + vaav;
            //hazfe vaave akhar. chon tebghe algorithm yek vaav ezafe gozashteim.
            result = result.substring(0, result.length() - vaav.length());
        }
        return result;
    }

    public static String convertMeghdareAdadiBeHorofi(String meghdareAdadi) {
        String result = "";
        meghdareAdadi = removeNoneDigits(meghdareAdadi);
        int l = meghdareAdadi.length();
        if (meghdareAdadi.equals("0")) return yekan[0];
        if (l % 3 == 1) meghdareAdadi = "00" + meghdareAdadi;
        else if (l % 3 == 2) meghdareAdadi = "0" + meghdareAdadi;
        l = meghdareAdadi.length() / 3;
        int b = 0;
        for (int i = 0; i < l; i++) {
            b = Integer.parseInt(meghdareAdadi.substring(i * 3, i * 3 + 3));
            if (b != 0) result += convertMeghdareAdadi3raghamiBeHorof(String.valueOf(b)) + " " +
                    pasvandeMazrabeHezar[l - i - 1] + vaav;
        }
        result = result.substring(0, result.length() - vaav.length());
        return result;
    }

    public static String unifyDateFormat(String date) {
        String[] parts = date.split("/");
        if (parts.length != 3) {
            return "ERROR";
        }
        String result = parts[0] + "/";
        if (parts[1].length() < 2) {
            result += "0" + parts[1];
        } else {
            result += parts[1];
        }
        result += "/";
        if (parts[2].length() < 2) {
            result += "0" + parts[2];
        } else {
            result += parts[2];
        }
        return result;
    }

    public static String join(Collection<?> s, String delimiter) {
        final StringBuilder builder = new StringBuilder();
        final Iterator iter = s.iterator();
        while (iter.hasNext()) {
            builder.append(iter.next());
            if (!iter.hasNext()) {
                break;
            }
            builder.append(delimiter);
        }
        return builder.toString();
    }

    public static boolean validCodeMelli(String codeMelli) {
        if (codeMelli == null || codeMelli.isEmpty() || codeMelli.length() == 0) {
            return false;
        }

        if (codeMelli.length() != 10 ||
                codeMelli.equals("0000000000") ||
                codeMelli.equals("1111111111") ||
                codeMelli.equals("2222222222") ||
                codeMelli.equals("3333333333") ||
                codeMelli.equals("4444444444") ||
                codeMelli.equals("5555555555") ||
                codeMelli.equals("6666666666") ||
                codeMelli.equals("7777777777") ||
                codeMelli.equals("8888888888") ||
                codeMelli.equals("9999999999")) {
            return false;
        }

        int c = Integer.valueOf(String.valueOf(codeMelli.charAt(9)));
        int n = Integer.valueOf(String.valueOf(codeMelli.charAt(0))) * 10 +
                Integer.valueOf(String.valueOf(codeMelli.charAt(1))) * 9 +
                Integer.valueOf(String.valueOf(codeMelli.charAt(2))) * 8 +
                Integer.valueOf(String.valueOf(codeMelli.charAt(3))) * 7 +
                Integer.valueOf(String.valueOf(codeMelli.charAt(4))) * 6 +
                Integer.valueOf(String.valueOf(codeMelli.charAt(5))) * 5 +
                Integer.valueOf(String.valueOf(codeMelli.charAt(6))) * 4 +
                Integer.valueOf(String.valueOf(codeMelli.charAt(7))) * 3 +
                Integer.valueOf(String.valueOf(codeMelli.charAt(8))) * 2;
        int r = n % 11;
        return (r == 0 && r == c) || (r == 1 && c == 1) || (r > 1 && c == 11 - r);

    }

    public static String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "ERROR";
        }
    }

    public static List<String> convertStringToList(String str, char seperator)
    {
        if(str==null || str.isEmpty() || str.equals("")) return null;
//        if (str.contains(String.valueOf(seperator)) == false) return null;
        if(str.charAt(str.length()-1)!=seperator)
            str+=seperator;

        List<String> list= new ArrayList();
        char[] charArr= str.toCharArray();

        String rsltStr="";

        for(int i=0; i< charArr.length ;i++)
        {
            if(charArr[i]!=seperator)
            {
                rsltStr+=charArr[i];
            }
            else
            {
                list.add(rsltStr);
                rsltStr = "";
            }
        }
        return list;
    }

    public static String getDeploymentPath() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String url = request.getRequestURL().toString();
        return url.substring(0, url.substring("http://".length()).indexOf('/') + "http://".length());
    }

    public static boolean isNumber(String string) {
        try
        {
            long testLong = Long.valueOf(string.replaceAll(",",""));
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }


}
