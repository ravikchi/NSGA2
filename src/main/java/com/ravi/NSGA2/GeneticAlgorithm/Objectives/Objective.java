package com.ravi.NSGA2.GeneticAlgorithm.Objectives;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;

/**
 * Created by ravik on 12/02/2017.
 */
public interface Objective {
    public double getFitness(Individual p);
}
