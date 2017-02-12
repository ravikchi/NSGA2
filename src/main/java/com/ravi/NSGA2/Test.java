package com.ravi.NSGA2;

import org.apache.commons.lang.StringUtils;

/**
 * Created by ravik on 12/02/2017.
 */
public class Test {

    public static void main(String[] args){

        double value = Double.parseDouble("1.0");
        long binaryRadius = Double.doubleToLongBits(value);
        String binary = Long.toBinaryString(binaryRadius);
        if(binary.length() < 63){
            binary = StringUtils.leftPad(binary, 63, "0");
        }

        System.out.println(binary);
        /*double radius = 20.0;
        long binaryRadius = Double.doubleToLongBits(radius);
        String radiusString = Long.toBinaryString(binaryRadius);
        System.out.println(binaryRadius);
        System.out.println(radiusString);
        System.out.println(radiusString.length());

        radius = 1.0;
        binaryRadius = Double.doubleToLongBits(radius);
        radiusString = Long.toBinaryString(binaryRadius);
        System.out.println(binaryRadius);
        System.out.println(radiusString);

        StringBuilder result = new StringBuilder();
        for(char c : radiusString.toCharArray()){
            char val = c;
            if(Math.random() < 0.05){
                if(c == '1'){
                    val = '0';
                }else{
                    val = '1';
                }
            }

            result.append(val);
        }

        System.out.println(result.toString());

        long value = Long.parseLong(result.toString(), 2);
        System.out.println(value);
        System.out.println(Double.longBitsToDouble(binaryRadius));
        System.out.println(Double.longBitsToDouble(value));*/
    }
}
