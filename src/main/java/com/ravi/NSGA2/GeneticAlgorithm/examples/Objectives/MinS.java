package com.ravi.NSGA2.GeneticAlgorithm.examples.Objectives;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.Objective;

import java.util.List;

/**
 * Created by ravik on 12/02/2017.
 */
public class MinS implements Objective {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public double getFitness(Individual p) {
        List<Object> phenoType = p.getPhenoType();
        double r = Double.parseDouble((String) phenoType.get(0));
        double h = Double.parseDouble((String) phenoType.get(1));

        double s = Math.sqrt((r*r)+(h*h));
        double S = Math.PI * r * s;

        double V = Math.PI / 3 * r * r * h;
        if(V < 200){
            return Double.POSITIVE_INFINITY;
        }

        return S;
    }
}
