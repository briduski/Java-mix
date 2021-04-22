package org.example.types;

import java.io.UnsupportedEncodingException;

public class EncodedChars {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String myStr1 = "Ã\u0086"; // ISO: Æ
        String myStr2 = "Ã¦";      // ISO: æ

        String myStr3 = "Ã\u0085"; // ISO: Å
        String myStr4 = "Ã¥";      // ISO: å

        String myStr5 = "Ã\u0098"; // ISO: Ø
        String myStr6 = "Ã¸";      // ISO: ø

        System.out.println("ISO -> Utf-8: " + new String(myStr1.getBytes("ISO-8859-1"),"UTF-8"));
        System.out.println("ISO -> Utf-8: " + new String(myStr2.getBytes("ISO-8859-1"),"UTF-8"));
        System.out.println("ISO -> Utf-8: " + new String(myStr3.getBytes("ISO-8859-1"),"UTF-8"));
        System.out.println("ISO -> Utf-8: " + new String(myStr4.getBytes("ISO-8859-1"),"UTF-8"));
        System.out.println("ISO -> Utf-8: " + new String(myStr5.getBytes("ISO-8859-1"),"UTF-8"));
        System.out.println("ISO -> Utf-8: " + new String(myStr6.getBytes("ISO-8859-1"),"UTF-8"));

        System.out.println("Utf-8 -> ISO: " + new String("Æ".getBytes("UTF-8"),"ISO-8859-1"));
        System.out.println("Utf-8 -> ISO: " + new String("Æ".getBytes("UTF-8"),"ISO-8859-1"));
        System.out.println("Utf-8 -> ISO: " + new String("Å".getBytes("UTF-8"),"ISO-8859-1"));
        System.out.println("Utf-8 -> ISO: " + new String("Å".getBytes("UTF-8"),"ISO-8859-1"));
        System.out.println("Utf-8 -> ISO: " + new String("Ø".getBytes("UTF-8"),"ISO-8859-1"));
        System.out.println("Utf-8 -> ISO: " + new String("Ø".getBytes("UTF-8"),"ISO-8859-1"));
    }
}
