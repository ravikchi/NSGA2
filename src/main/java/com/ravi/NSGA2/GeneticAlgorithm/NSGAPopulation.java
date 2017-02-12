package com.ravi.NSGA2.GeneticAlgorithm;

import com.ravi.GenericGA.GeneticAlgorithm.GAOperators;
import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.NextGenSelector;
import com.ravi.GenericGA.GeneticAlgorithm.Population;

import java.util.List;

/**
 * Created by ravik on 12/02/2017.
 */
public class NSGAPopulation extends Population {
    public NSGAPopulation(GAOperators operators, NextGenSelector nextGenOperator) {
        super(operators, nextGenOperator);
    }

    public void printStatistics() {
        Individual best = getPopulation().get(0);
        Individual worst = getPopulation().get(0);
        double average = 0.0;
        for(Individual i: getPopulation()){

            double V = getVolume(i);

            if(getVolume(best) > V){
                best = i;
            }
            if(getVolume(worst) < V){
                worst = i;
            }

            average = average + V;
        }

        average = average/getN();

        System.out.println("The Best Individual has S :"+getS(best));
        System.out.println("The Best Individual has T :"+getT(best));
        System.out.println("The Best Individual has Volume:"+getVolume(best));
        System.out.println("The Worst Individual has S :"+getS(worst));
        System.out.println("The worst Individual has T :"+getT(worst));
        System.out.println("The worst Individual has Volume:"+getVolume(worst));
        System.out.println("The Average Volume of population :"+average);

    }

    private double getVolume(Individual i){
        List<Object> phenoType = i.getPhenoType();
        double r = Double.parseDouble((String) phenoType.get(0));
        double h = Double.parseDouble((String) phenoType.get(1));

        double s = Math.sqrt((r*r)+(h*h));
        double S = Math.PI * r * s;
        double B = Math.PI * r * r;

        double T = B+S;
        double V = Math.PI / 3 * r * r * h;

        return V;
    }

    private double getS(Individual i){
        List<Object> phenoType = i.getPhenoType();
        double r = Double.parseDouble((String) phenoType.get(0));
        double h = Double.parseDouble((String) phenoType.get(1));

        double s = Math.sqrt((r*r)+(h*h));
        double S = Math.PI * r * s;

        return S;
    }

    private double getT(Individual i){
        List<Object> phenoType = i.getPhenoType();
        double r = Double.parseDouble((String) phenoType.get(0));
        double h = Double.parseDouble((String) phenoType.get(1));

        double s = Math.sqrt((r*r)+(h*h));
        double S = Math.PI * r * s;
        double B = Math.PI * r * r;

        double T = B+S;

        return T;
    }

}
