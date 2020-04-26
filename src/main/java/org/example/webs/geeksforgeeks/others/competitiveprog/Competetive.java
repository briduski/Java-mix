package org.example.webs.geeksforgeeks.others.competitiveprog;

import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;
//https://www.geeksforgeeks.org/java-tricks-competitive-programming-java-8/?ref=rp
public class Competetive {
    public static void main(String[] args) throws IOException {
        fastMultDiv();
        easyEventOdd();
        swappingInts();
       // fastIOScanner();
       // fastIOBufferReader();
       // fastIOBufferReaderAndTokenizer();
        mostSignificantDigit();
        numberOfDigits();
        GCD();
        prime();
        swappingStrings();
    }
    public static void prime() {
        int a = 1234457;
        System.out.println();
       boolean rres= BigInteger.valueOf(a).isProbablePrime(1);
        System.out.println(a+ " is probable prime ?"+rres);

    }
    public static void GCD() {
        int a =25;
        int b =125;
        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger gcd = b1.gcd(b2);
        System.out.println("GCD of ("+a + ", "+ b + ") is "+gcd.intValue());

        BigInteger c1 = BigInteger.valueOf(1234567);
        BigInteger c2 = BigInteger.valueOf(989812);
        BigInteger gcdLong = c1.gcd(c2);
        System.out.println("GCD of ("+c1 + ", "+ c2 + ") is "+gcdLong.longValue());
    }
    public static void numberOfDigits(){
        int n=123456;
        double res = Math.floor(Math.log10(n)) + 1;
        System.out.println("numberOfDigits");
        System.out.println(n +" has "+res + " digits");
    }
    public static void mostSignificantDigit(){
        int n=5024;
        double kk = Math.log10(n);
        double jj = kk - Math.floor(kk);
        int x = (int)Math.pow(10, jj);
        System.out.println("Most significant digit of "+n+ ", is "+x);
    }
    public static void fastReader() throws IOException {
        // the fastest way
        Reader s=new Reader();
        int n = s.nextInt();
        int k = s.nextInt();
        int count=0;
        while (n-- > 0)
        {
            int x = s.nextInt();
            if (x%k == 0)
                count++;
        }
        System.out.println(count);
    }
    public static void fastIOBufferReaderAndTokenizer()  {
        //This method uses the time advantage of BufferedReader and StringTokenizer and the advantage
        // of user defined methods for less typing and therefore a faster input altogether
        System.out.println("fastIOBufferReaderAndTokenizer");
        System.out.println("Insert #atempts and the base number .. ");
        FastReader s=new FastReader();
        int n = s.nextInt();
        int k = s.nextInt();
        System.out.println("n: "+n+", k: "+k);
        int count = 0;
        while (n-- > 0) {
            int x = s.nextInt();
            if (x%k == 0)
                count++;
        }
        System.out.println("count : "+ count);
    }
    public static void fastIOBufferReader() throws IOException {
        // (fast, but not recommended as it requires lot of typing)
        System.out.println("fastIOBufferReader");
        System.out.println("Insert #atempts and the base number (one line).. ");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        System.out.println("n: "+n+", k: "+k);
        int count = 0;
        while (n-- > 0){
            int x = Integer.parseInt(br.readLine());
            if (x%k == 0)
                count++;
        }
        System.out.println("count : "+ count);
    }
    public static void fastIOScanner(){
        // (easy, less typing, but not recommended very slow, refer this for reasons of slowness)
        System.out.println("fastIO");
        System.out.println("Insert #atempts and the base number ... ");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        int count = 0;
        System.out.println("n: "+n+", k: "+k);
        while (n-- > 0){
            int x = s.nextInt();
            if (x%k == 0)
                count++;
        }
        System.out.println("count : "+ count);
    }
    public static void swappingStrings(){
        // Declare two strings
        String a = "Hello";
        String b = "World";

        // Print String before swapping
        System.out.println("Strings before swap: a = " +
                a + " and b = "+b);

        // append 2nd string to 1st
        a = a + b;
        System.out.println("Strings after 1 swap: a = " +
                a + " and b = "+b);
        // store intial string a in string b
        b = a.substring(0,a.length()-b.length());
        System.out.println("Strings after 2 swap: a = " +
                a + " and b = "+b);
        // store initial string b in string a
        a = a.substring(b.length());

        // print String after swapping
        System.out.println("Strings after swap: a = " +
                a + " and b = " + b);
    }
    public static void swappingInts(){
        System.out.println("swapping");
        int a = 1;
        int b=2;

        System.out.println("Before swapping: a=" + a+ ", b="+b);
        // A quick way to swap a and b
        a ^= b;
        System.out.println("After-1 swapping: a=" + a+ ", b="+b);
        b ^= a;
        System.out.println("After-2 swapping: a=" + a+ ", b="+b);
        a ^= b;
        System.out.println("After-Last swapping: a=" + a+ ", b="+b);


    }
    public static void easyEventOdd(){
        System.out.println("easyEventOdd");
        int a=124892;
        System.out.println(a+" is " + ((a & 1) == 0 ? "EVEN" : "ODD" ));

    }
    public static void fastMultDiv(){
        System.out.println("fastMultDiv");
        int n=126;
        int res=0;
        res = n << 1;   // Multiply n with 2
        System.out.println(" "+n + " << 1 = "+res);
        res = n >> 1;   // Divide n by 2
        System.out.println(" "+n + " >> 1 = "+res);
    }
}

class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        br = new BufferedReader(new
                InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException  e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt() {
        return Integer.parseInt(next());
    }
    long nextLong() {
        return Long.parseLong(next());
    }
    double nextDouble() {
        return Double.parseDouble(next());
    }
    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
 class Reader {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader() {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException {
        din = new DataInputStream(new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
        byte[] buf = new byte[64]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n')
                break;
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException {
        int ret = 0;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg)
            return -ret;
        return ret;
    }

    public long nextLong() throws IOException {
        long ret = 0;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        }
        while ((c = read()) >= '0' && c <= '9');
        if (neg)
            return -ret;
        return ret;
    }

    public double nextDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();

        do {
            ret = ret * 10 + c - '0';
        }
        while ((c = read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg)
            return -ret;
        return ret;
    }

    private void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1)
            buffer[0] = -1;
    }

    private byte read() throws IOException {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (din == null)
            return;
        din.close();
    }
}
