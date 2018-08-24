package test;

import java.util.Scanner;

/**
 * create by hwx533443
 */
public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String str1 = null;
        String str2 = null;
        while(input.hasNext()){
            str1 = input.nextLine();
            str2 = input.nextLine();
        }
        t(str1);
        t(str2);

    }

    public static void t(String str1){
        int length1 = str1.length();

        int num = length1 / 8;
        int numRemin = length1%8;

        for(int i=0;i<num;i++){
            System.out.println(str1.substring(i*8,(i+1)*8));
        }
        System.out.print(str1.substring(length1 - numRemin,length1));
        for(int i=0; i<length1 - numRemin;i++){
            System.out.print("0");
        }
        System.out.println();
    }
}
