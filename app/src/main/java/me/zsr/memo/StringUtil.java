package me.zsr.memo;

/**
 * @description:
 * @author: Match
 * @date: 22/11/2017
 */

public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean equals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }

        if (str1 == null || str2 == null) {
            return false;
        }

        return str1.equals(str2);
    }
}
