package com.ravi.NSGA2;


import com.ravi.NSGA2.GeneticAlgorithm.examples.CostMinPopulation;

public class App 
{
    public static void main( String[] args )
    {
        //ConeProblemPopulation.runAlgorithm(50, 0.8, 0.2, 500);
        CostMinPopulation.runAlgorithm(200, 0.8, 0.2, 1000);

    }


}
