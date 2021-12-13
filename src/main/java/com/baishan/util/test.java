package com.baishan.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class test {

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public static void main(String[] args) {
        /*System.out.println(LocalDateTime.now().format(timeFormatter));
        System.out.println(System.currentTimeMillis() / 1000);
        String a = "32";
        System.out.println(Integer.parseInt(a));*/

        /*String[] shell2 = new String[]{"/bin/bash", "-c", "ls | wc -l"};
        String num = ExecShell.doShell(shell2);

        System.out.println(Integer.parseInt(num));*/
        /*String result = "    19";
        StringBuffer num = new StringBuffer();
        for (int i = 0; i < result.length();++i) {
            if (Character.isDigit(result.charAt(i))) {
                num.append(result.charAt(i));
            }
        }
        System.out.println(String.valueOf(num));*/
        System.out.println(Integer.parseInt(wow()));
    }

    public static String wow() {
        String result = "    19";
        StringBuffer num = new StringBuffer();
        for (int i = 0; i < result.length();++i) {
            if (Character.isDigit(result.charAt(i))) {
                num.append(result.charAt(i));
            }
            return String.valueOf(num);
        }
        return null;
    }
}
