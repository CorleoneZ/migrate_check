package com.baishan.util;

import java.io.IOException;
import java.io.InputStream;

public class ExecShell {

    public static String doShell(String[] cmd) {
        Runtime run = null;
        Process process = null;
        InputStream inputStream = null;
        String result = "";
        StringBuffer num = new StringBuffer();
        try {
            run = Runtime.getRuntime();
            process = run.exec(cmd);
            inputStream = process.getInputStream();
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[8192];
            int n;
            while ((n = inputStream.read(b)) != -1)
                out.append(new String(b, 0, n));
            result = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            process.destroy();
        }
        for (int i = 0; i < result.length();++i) {
            if (Character.isDigit(result.charAt(i))) {
                num.append(result.charAt(i));
            }
        }
        return String.valueOf(num);
    }
}
