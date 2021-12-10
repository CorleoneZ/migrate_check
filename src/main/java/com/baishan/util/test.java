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

        String[] shell2 = new String[]{"/bin/bash", "-c", "ls | wc -l"};
        String num = ExecShell.doShell(shell2);

        System.out.println(Integer.parseInt(num));
    }
}
