package main;

public class MyPrinter {

    public static void print(String printStr) {
        System.out.print(printStr);

    }
    public static void printf(String format, Object ... args){
        System.out.printf(format,args);
    }
}

