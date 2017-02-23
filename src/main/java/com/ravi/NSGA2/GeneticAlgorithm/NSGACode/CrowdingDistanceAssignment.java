package com.ravi.NSGA2.GeneticAlgorithm.NSGACode;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.Objective;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.MultiObjectiveIndividual;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ravik on 12/02/2017.
 */
public class CrowdingDistanceAssignment {
    private List<Objective> objectives = new ArrayList<Objective>();
    private double fMax = Double.NEGATIVE_INFINITY;
    private double fMin = Double.POSITIVE_INFINITY;
    private long totalTime = 0;

    public CrowdingDistanceAssignment(List<Objective> objectives) {
        this.objectives = objectives;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void calculateCrowdingDistance(List<Individual> individuals){
        Calendar start = Calendar.getInstance();
        /*List<Individual> population = new ArrayList<Individual>();
        population.addAll(individuals);
        int l = population.size()-1;

        for(Individual k : population){
            MultiObjectiveIndividual i = (MultiObjectiveIndividual) k;
            i.setiDistance(0);
        }

        for(Objective m : objectives){
            sortPopulation(m, population);

            MultiObjectiveIndividual I0 = (MultiObjectiveIndividual) population.get(0);
            I0.setiDistance(Double.POSITIVE_INFINITY);

            MultiObjectiveIndividual IL = (MultiObjectiveIndividual) population.get(l);
            IL.setiDistance(Double.POSITIVE_INFINITY);

            for(int i=1; i<l;i++){
                MultiObjectiveIndividual Ii = (MultiObjectiveIndividual) population.get(i);

                //double iDistance = Ii.getiDistance() + ((m.getFitness(population.get(i+1)) - m.getFitness(population.get(i-1))) / (fMax - fMin));
                double iDistance = Ii.getiDistance() + ((population.get(i+1).getFitness(m) - population.get(i-1).getFitness(m)) / (fMax - fMin));
                Ii.setiDistance(iDistance);
            }

        }*/

        CountDownLatch latch = new CountDownLatch(objectives.size());

        for(Objective m : objectives){
            ObjectiveCrowDist dist = new ObjectiveCrowDist(individuals, m, latch);
            Thread t = new Thread(dist);
            t.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Calendar end = Calendar.getInstance();

        totalTime = totalTime + (end.getTimeInMillis() - start.getTimeInMillis());
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
            public int compare(Individual m1, Individual m2){
                MultiObjectiveIndividual o1 = (MultiObjectiveIndividual) m1;
                MultiObjectiveIndividual o2 = (MultiObjectiveIndividual) m2;
                if(o1.objectiveFitness() == o2.objectiveFitness())
                    return 0;
                return o1.objectiveFitness() < o2.objectiveFitness() ? -1 : 1;
            }
        });
    }
}
