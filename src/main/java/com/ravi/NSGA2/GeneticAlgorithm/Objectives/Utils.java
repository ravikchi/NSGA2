package com.ravi.NSGA2.GeneticAlgorithm.Objectives;

/**
 * Created by rc16956 on 14/02/2017.
 */
public class Utils {
    public static double getVolume(int shape, int x, int y){
        int length = x + y;
        if(shape==0){
            return (int)((Math.sqrt(2*(Math.pow(length, 2))))/(12));
        }
        if(shape==1){
            return (int)(Math.pow(length, 3));
        }
        if(shape==2){
            return (int)((0.333)*(Math.sqrt(2*Math.pow(length, 3))));
        }
        if(shape==3){
            return (int)((0.25)*(15+(7*Math.sqrt(5)))*Math.pow(length, 3));
        }
        if(shape==4){
            return (int)((5.0/12.0)*(3+(Math.sqrt(5)))*Math.pow(length, 3));
        }
        if(shape==5){
            return (int)((4*Math.PI*(Math.pow(length, 3)))/3);
        }

        return Double.MAX_VALUE;
    }

    public static double getSurfaceArea(int shape, int x, int y){
        double length = (x)+(y);
        if(shape==0){
            return (int)(Math.sqrt(3*(Math.pow(length, 2))));
        }
        if(shape==1){
            return (int)(6*Math.pow(length, 2));
        }
        if(shape==2){
            return (int)(2*(Math.sqrt(3*(Math.pow(length, 2)))));
        }
        if(shape==3){
            return (int)(3*(Math.sqrt(25+(10*Math.sqrt(5*Math.pow(length, 2))))));
        }
        if(shape==4){
            return (int)(5*(Math.sqrt(3*(Math.pow(length, 2)))));
        }
        if(shape==5){
            return (int)(4*Math.PI*Math.pow(length, 2));
        }

        return Double.MAX_VALUE;
    }

    public static double getCost(double SA, double V, int material){
        double cost = 0;
        if(material == 0){
            cost = (SA *1) +(V*1); ///wood;
        }
        if(material == 1){
            cost = (SA *1.5) +(V*0.5); ///metal;
        }
        if(material == 2){
            cost = (SA *0.5) +(V*1.5); ///plastic;
        }

        return cost;
    }
}
