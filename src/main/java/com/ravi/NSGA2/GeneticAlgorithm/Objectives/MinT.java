package com.ravi.NSGA2.GeneticAlgorithm.Objectives;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;

import java.util.List;

/**
 * Created by ravik on 12/02/2017.
 */
public class MinT implements Objective {
    @Override
    public double getFitness(Individual p) {
        List<Object> phenoType = p.getPhenoType();
        double r = Double.parseDouble((String) phenoType.get(0));
        double h = Double.parseDouble((String) phenoType.get(1));

        double V = Math.PI / 3 * r * r * h;
        if(V < 200){
            return Double.POSITIVE_INFINITY;
        }

        double s = Math.sqrt((r*r)+(h*h));
        double S = Math.PI * r * s;
        double B = Math.PI * r * r;

        double T = B+S;

        return T;
    }
}
