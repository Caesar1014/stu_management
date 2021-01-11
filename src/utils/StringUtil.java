package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
    // 判断是否为合法的手机号格式
    public static boolean isMobileNo(String str) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    // 判断是否全为数字输入
    public static boolean isNumeric(String str) {
        Pattern p = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
    // 判断是否全为中文输入
    public static boolean isChinese(String str) {
        Pattern p = Pattern.compile("[\u4E00-\u9FA5]+");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
