package io.tiklab.kanass.common;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

public class IDGeneratorUtil {
    private static final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random random = new Random();

    // 生成6位ID，随机大写字母和数字
    public static String generateID() {
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }

    // 将ID递增1位
    public static String incrementID(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid ID format");
        }

        char[] chars = id.toCharArray();
        boolean carry = true;
        int i = chars.length - 1;

        while (carry && i >= 0) {
            char c = chars[i];
            if (c == '9') {
                chars[i] = 'A';
                carry = false;
            } else if (c == 'Z') {
                chars[i] = '0';
                carry = true;
                i--;
            } else if (Character.isDigit(c)) {
                chars[i] = (char)(c + 1);
                carry = false;
            } else if (Character.isUpperCase(c)) {
                chars[i] = (char)(c + 1);
                carry = false;
            }
        }

        // 处理最高位进位
        if (carry) {
            return "1" + new String(chars);
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        String id = generateID();

        System.out.println( id);
        String newID  = incrementID("ZZZZZZ");

        System.out.println(newID);
    }
}
