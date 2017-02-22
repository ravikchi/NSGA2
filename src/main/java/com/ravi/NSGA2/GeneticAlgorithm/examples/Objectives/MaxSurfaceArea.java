package com.ravi.NSGA2.GeneticAlgorithm.examples.Objectives;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.Objective;

import java.util.List;

/**
 * Created by rc16956 on 14/02/2017.
 */
public class MaxSurfaceArea implements Objective {
    @Override
    public String getName() {
        return "Max SurfaceArea ";
    }

    @Override
    public double getFitness(Individual p) {
        List<Object> phenoType = p.getPhenoType();
        double shape = Double.parseDouble((String) phenoType.get(2));
        double x = Double.parseDouble((String) phenoType.get(0));
        double y = Double.parseDouble((String) phenoType.get(1));
        double mat = Double.parseDouble((String) phenoType.get(3));
        int max = 50;

        return 1-Utils.getSurfaceArea((int)(shape*6), (int)(x*max), (int)(y*max));
    }
}
