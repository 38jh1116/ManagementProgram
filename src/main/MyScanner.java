package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MyScanner {
    public MyScanner(){

    }

    public static String stringScanner(){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = "";
        try {
            inputStr = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStr;
    }

    public static int intScanner(){
        Scanner scanner = new Scanner(System.in);
        int inputNum = -1;
        try{
            inputNum = scanner.nextInt();
        }catch (Exception e){ }
        return inputNum;
    }
}
