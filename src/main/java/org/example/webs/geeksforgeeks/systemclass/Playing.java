package org.example.webs.geeksforgeeks.systemclass;

import java.io.*;
import java.nio.channels.Channel;
import java.util.*;

public class Playing {
    public static void copyArrays (){

        int []a = new int[]{1,2,3,4};
        int []b=new int[]{9,8,7,6};
        //Arrays.stream(a).forEach(x-> System.out.println("a: "+x));
        System.out.println(Arrays.toString(a));
        // static void arraycopy(Object source, int sourceStart, Object Target, int targetStart, int size
        System.arraycopy(a,0, b,0,4);
        System.out.println(Arrays.toString(b));

        int[] c = {1, 2, 3, 4, 5};
        int[] d = {6, 7, 8, 9, 10};

        System.arraycopy(c, 0, d, 2, 2);

        // array b after arraycopy operation
        System.out.println(Arrays.toString(d)); // [6, 7, 1, 2, 10]
    }
    public static void systemProperties(){
       // checking specific property
       System.out.println(System.getProperty("user.home"));
       System.out.println(System.getProperty("user.country"));

       // setting specific property
       System.setProperty("LOG_LEVEL", "INFO");
       // checking property
       System.out.println("LOG_LEVEL: "+ System.getProperty("LOG_LEVEL"));

       // clearing this property
       System.clearProperty("LOG_LEVEL");
       // checking property
       System.out.println("(cleared) LOG_LEVEL: "+ System.getProperty("LOG_LEVEL"));

        Properties gfg = System.getProperties();
         gfg.forEach((k,v)->  System.out.println(k + " : " + v));

    }
    public static void times(){
        System.out.println("difference between the "
                + "current time and midnight,"
                + " January 1, 1970 UTC is: " +
                System.currentTimeMillis());
        System.out.println("cuurent time in "
                + "nano sec: " +
                System.nanoTime());
    }
    public static void consconsole(){
        Console console = System.console();
        if(console != null){
            Currency currency = Currency.getInstance(Locale.ITALY);
            console.printf(currency.getSymbol());
            console.flush();
        } else{
            System.out.println("No console attached");
        }
    }
    public static void garbageColl(){
        Runtime gfg = Runtime.getRuntime();
        long memory1, memory2;
        Integer integer[] = new Integer[1000];

        // checking the total memeory
        System.out.println("Total memory is: "
                + gfg.totalMemory()/(1024*1024));

        // checking free memory
        memory1 = gfg.freeMemory();
        System.out.println("Initial free memory: " + memory1/(1024*1024));

        // calling the garbage collector on demand
        System.gc();

        memory1 = gfg.freeMemory();

        System.out.println("Free memory after garbage collection: " + memory1/(1024*1024));

        // allocating integers
        for (int i = 0; i < 1000; i++) integer[i] = new Integer(i);

        memory2 = gfg.freeMemory();
        System.out.println("Free memory after allocation: " + memory2/(1024*1024));

        System.out.println("Memeory used by allocation: " + (memory1 - memory2)/(1024*1024));

        // discard integers
        for (int i = 0; i < 1000; i++) integer[i] = null;

        System.gc();

        memory2 = gfg.freeMemory();
        System.out.println("Free memory after collecting discarded Integers: " + memory2/(1024*1024));
    }
    public static void envProperties() {
        Map<String, String> gfg = System.getenv();
        Set<String> keySet = gfg.keySet();
       /* for(String key : keySet) {
            System.out.println("key= " + key);
        }*/
        gfg.forEach((k,v)->  System.out.println(k+ " : " + v));

        // checking specific environment variable
        System.out.println("- PATH env var: "+ System.getenv("PATH"));
    }
    public static void secManager() {
        SecurityManager gfg = new SecurityManager();

        // setting the security manager
       // System.setSecurityManager(gfg);

        gfg = System.getSecurityManager();
        if(gfg != null)
            System.out.println("Security manager is configured");

        System.setSecurityManager(null);
    }
    public static void std()  throws IOException {
        /*   FileOutputStream OUT = new FileOutputStream("system.txt");

        // set output stream
        System.setOut(new PrintStream(OUT));
        System.out.write("Hi Abhishek\n".getBytes());

        // set error stream
        System.setErr(new PrintStream(OUT));
        System.err.write("Exception message\n".getBytes());*/

        FileInputStream IN = new FileInputStream("system.txt");
        // set input stream
        System.setIn(IN);
        char c = (char) System.in.read();
   //     System.setOut(System.out);
        System.out.print(c);
    }

    public static void loadPropertiesFromFile(){
       try {
            FileInputStream    propFile = new FileInputStream( "application.properties");
            Properties p = new Properties(System.getProperties());
            p.load(propFile);
            // set the system properties
            System.setProperties(p);
            // display new properties
          System.getProperties().list(System.out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setToEnvironmentPropertyAFile(){
        System.setProperty("EnvironmentName", "Dummy");
        System.setProperty("GetEnvironmentPropertiesURL", "file://localhost/" +
                new File("./src/test/resources/generated/application.properties").getAbsolutePath());
        // or
        // System.setProperty("GetEnvironmentPropertiesURL", "file:src/test/resources/EnvironmentProperties.txt");

    }
    public static void loadLib() throws NullPointerException {
        // map library name
        String libName = System.mapLibraryName("os.name");
        System.out.println("os.name library= " + libName);

        //load external libraries   libos.name.dylib
        System.loadLibrary("libos.name.dylib");
        System.load("lixXYZ.so");

        //run finalization
        System.runFinalization();
    }
    public static void main(String[] args) throws IOException {
        //copyArrays();
         systemProperties();
        // envProperties();
        //  times();
        // consconsole();
        // garbageColl();
        // System.exit(1);
        // Runtime.getRuntime().exit(1);
        // secManager();
        // std();
        // loadLib();

        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> System.out.println("Say exit, bye, chao")
        ));


        Integer x = 400;
        System.out.println(System.identityHashCode(x));

    }

}
