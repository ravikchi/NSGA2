package com.ravi.NSGA2.GeneticAlgorithm.NSGACode;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.Objective;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.MultiObjectiveIndividual;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 611445924 on 23/02/2017.
 */
public class FindObjectiveFitness implements Runnable{
    private Objective objective;
    private CountDownLatch latch;
    List<Individual> P;

    public FindObjectiveFitness(Objective objective, CountDownLatch latch, List<Individual> p) {
        this.objective = objective;
        this.latch = latch;
        P = p;
    }

    @Override
    public void run() {
        for(Individual i : P){
            MultiObjectiveIndividual p = (MultiObjectiveIndividual) i;
            p.getFitness(objective);
        }
        latch.countDown();
    }

}
