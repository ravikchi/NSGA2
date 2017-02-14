package com.ravi.NSGA2.GeneticAlgorithm.NSGACode;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.MultiObjectiveIndividual;
import com.ravi.NSGA2.GeneticAlgorithm.Objectives.Objective;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ravik on 12/02/2017.
 */
public class CrowdingDistanceAssignment {
    private List<Objective> objectives = new ArrayList<Objective>();
    private double fMax = Double.NEGATIVE_INFINITY;
    private double fMin = Double.POSITIVE_INFINITY;

    public CrowdingDistanceAssignment(List<Objective> objectives) {
        this.objectives = objectives;
    }

    public void calculateCrowdingDistance(List<Individual> individuals){
        List<Individual> population = new ArrayList<Individual>();
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

                double iDistance = Ii.getiDistance() + ((m.getFitness(population.get(i+1)) - m.getFitness(population.get(i-1))) / (fMax - fMin));
                Ii.setiDistance(iDistance);
            }

        }
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
