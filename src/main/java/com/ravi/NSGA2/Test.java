package com.ravi.NSGA2;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by ravik on 12/02/2017.
 */
public class Test {

    public static void main(String[] args) {
        String s = "5.67888";
        int geneSize = 20;


        int index = s.indexOf('.');
        int value = Integer.parseInt(s.substring(0, index));
        int mantissa = Integer.parseInt(s.substring(index+1, Math.min(s.length(), index+4)));


        String prefix = Integer.toBinaryString(value);
        String manti = Integer.toBinaryString(mantissa);

        System.out.println(StringUtils.leftPad(prefix, geneSize/2, "0"));
        System.out.println(StringUtils.leftPad(manti, geneSize/2, "0"));

        s = StringUtils.leftPad(prefix, geneSize/2, "0") + StringUtils.leftPad(manti, geneSize/2, "0");

        String p = s.substring(0, geneSize/2);
        String m = s.substring(geneSize/2, s.length());

        System.out.println(Integer.parseInt(p, 2)+"."+Integer.parseInt(m,2));


        int min = -10;
        int max = 10;

        for(int i=1; i<100; i++) {
            int diff = max - min;
            double random = Math.random();

            System.out.println(random * diff + min);
        }
    }
}
