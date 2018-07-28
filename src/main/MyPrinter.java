package main;

public class MyPrinter {

    public void print(String printStr) {
        System.out.print(printStr);

    }
    public void printf(String format, Object ... args){
        System.out.printf(format,args);
    }
}

