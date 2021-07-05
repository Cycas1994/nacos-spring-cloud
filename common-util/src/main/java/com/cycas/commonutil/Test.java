package com.cycas.commonutil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Test {

    public static void main(String[] args) {

        String str = "http://1812.img.pp.sohu.com.cn/images/blog/2009/11/18/18/8/125b6560a6ag214.jpg";
        try {
            String result = URLDecoder.decode(str, "utf-8");
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
