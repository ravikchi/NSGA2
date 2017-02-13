package com.ravi.NSGA2;

import org.apache.commons.lang.StringUtils;

/**
 * Created by ravik on 12/02/2017.
 */
public class Test {

    public static void main(String[] args) {
        float f = 3.0f;
        int intBits = Float.floatToIntBits(f);
        int rawbits = Float.floatToRawIntBits(f);

        System.out.println(intBits);
        System.out.println(rawbits);

        int sign     = intBits & 0x80000000;
        int exponent = intBits & 0x7f800000;
        int mantissa = intBits & 0x007fffff;


        System.out.printf("sign = %d  exponent = %d  mantissa = %d%n",
                sign, exponent, mantissa);

        String binarySign = Integer.toBinaryString(sign);
        String binaryExponent = Integer.toBinaryString(exponent);
        String binaryMantissa = Integer.toBinaryString(mantissa);

        System.out.printf("binarySign     = %s%nbinaryExponent = %s%n" +
                        "binaryMantissa = %s%n", binarySign,
                binaryExponent, binaryMantissa);
    }
}
