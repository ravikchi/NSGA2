package com.ravi.NSGA2.GeneticAlgorithm;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.NextGenSelector;
import com.ravi.GenericGA.GeneticAlgorithm.Objective;
import com.ravi.NSGA2.GeneticAlgorithm.NSGACode.CrowdingDistanceAssignment;
import com.ravi.NSGA2.GeneticAlgorithm.NSGACode.FastNonDominatedSort;

import java.util.*;

/**
 * Created by ravik on 12/02/2017.
 */
public class NSGANextGenSelector implements NextGenSelector {
    List<Objective> objectives = new ArrayList<Objective>();

    FastNonDominatedSort fastNonDominatedSort;
    CrowdingDistanceAssignment crowdingDistanceAssignment;

    public NSGANextGenSelector(List<Objective> objectives) {
        this.objectives = objectives;
        fastNonDominatedSort = new FastNonDominatedSort(objectives);
        crowdingDistanceAssignment = new CrowdingDistanceAssignment(objectives);
    }

    @Override
    public List<Individual> getNextGenPopulation(List<Individual> parentGen, List<Individual> childGen) {
        List<Individual> population = new ArrayList<Individual>();
        population.addAll(parentGen);
        population.addAll(childGen);

        fastNonDominatedSort.sort(population);
        crowdingDistanceAssignment.calculateCrowdingDistance(population);

        Collections.sort(population, new Comparator<Individual>(){
            public int compare(Individual o1, Individual o2){
                if(o1.getFitness() == o2.getFitness())
                    return 0;
                return o1.getFitness() > o2.getFitness() ? -1 : 1;
            }
        });

        return population.subList(0, parentGen.size());
    }


}
