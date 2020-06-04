package com.chedilong.event.util;

public class StringJudgeUtil {
    private void StringUtil() {
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        //str.trim()将字符串中前后的空格去掉
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否不是空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (str == null && "".equals(str.trim())) {
            return false;
        } else {
            return true;
        }
    }
}
