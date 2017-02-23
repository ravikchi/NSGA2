package com.ravi.NSGA2.GeneticAlgorithm.examples;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.Objective;
import com.ravi.NSGA2.GeneticAlgorithm.NSGACode.FastNonDominatedSort;
import com.ravi.NSGA2.GeneticAlgorithm.NSGACode.FindObjectiveFitness;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 611445924 on 23/02/2017.
 */
public class SortCostMin extends FastNonDominatedSort {
    public SortCostMin(List<Objective> objectives) {
        super(objectives);
    }

    @Override
    protected void calculateObjectiveFitness(List<Individual> P){
        CountDownLatch latch = new CountDownLatch(objectives.size());

        for(Objective m : objectives){
            FindObjectiveFitness fitness = new FindObjectiveFitness(m, latch, P);
            Thread t = new Thread(fitness);
            t.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
