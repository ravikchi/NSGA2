package com.ravi.NSGA2.GeneticAlgorithm.NSGACode;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.Objective;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.MultiObjectiveIndividual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 611445924 on 23/02/2017.
 */
public class ObjectiveCrowDist implements Runnable {

    private List<Individual> population = new ArrayList<Individual>();
    private Objective objective;
    private double fMax = Double.NEGATIVE_INFINITY;
    private double fMin = Double.POSITIVE_INFINITY;
    private CountDownLatch latch;


    public ObjectiveCrowDist(List<Individual> population, Objective objective, CountDownLatch latch) {
        this.population.addAll(population);
        this.objective = objective;
        this.latch = latch;
    }

    @Override
    public void run() {

        int l = population.size()-1;

        for(Individual k : population){
            MultiObjectiveIndividual i = (MultiObjectiveIndividual) k;
            i.setiDistance(0);
        }

        sortPopulation(objective, population);

        MultiObjectiveIndividual I0 = (MultiObjectiveIndividual) population.get(0);
        I0.setiDistance(Double.POSITIVE_INFINITY);

        MultiObjectiveIndividual IL = (MultiObjectiveIndividual) population.get(l);
        IL.setiDistance(Double.POSITIVE_INFINITY);

        for(int i=1; i<l;i++){
            MultiObjectiveIndividual Ii = (MultiObjectiveIndividual) population.get(i);

            //double iDistance = Ii.getiDistance() + ((m.getFitness(population.get(i+1)) - m.getFitness(population.get(i-1))) / (fMax - fMin));
            double iDistance = Ii.getiDistance() + ((population.get(i+1).getFitness(objective) - population.get(i-1).getFitness(objective)) / (fMax - fMin));
            Ii.setiDistance(iDistance);
        }

        latch.countDown();
    }

    private void sortPopulation(Objective m, List<Individual> population){

        for(Individual i : population){
            MultiObjectiveIndividual I = (MultiObjectiveIndividual) i;
            double iDistance = m.getFitness(i);
            if(iDistance < fMin){
                fMin = iDistance;
            }

            if(iDistance > fMax){
                fMax = iDistance;
            }
            I.setCurrentObjective(m);
        }

        Collections.sort(population, new Comparator<Individual>(){
            public int compare(Individual o1, Individual o2){
                if(o1.getFitness(objective) == o2.getFitness(objective))
                    return 0;
                return o1.getFitness(objective) < o2.getFitness(objective) ? -1 : 1;
            }
        });
    }
}
