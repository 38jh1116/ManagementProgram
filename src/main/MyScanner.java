package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
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

    public static String gradeScanner() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = "";
        List<String> gradeRange = Arrays.asList("A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F");
        try {
            inputStr = bufferedReader.readLine();
            if (gradeRange.contains(inputStr)) return inputStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
