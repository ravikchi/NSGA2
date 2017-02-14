package com.ravi.NSGA2.GeneticAlgorithm.Operators;

import com.ravi.GenericGA.GeneticAlgorithm.CrossOverOperator;
import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.Utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rc16956 on 14/02/2017.
 */
public class RealValueCrossOver implements CrossOverOperator {
    @Override
    public Individual crossOver(Individual individual, Individual individual1) {
        List<Object> parent1 = individual.getPhenoType();
        List<Object> parent2 = individual1.getPhenoType();

        int crossOverPoint1 = RandomUtils.randomIntWithRange(1, parent1.size()-1);

        List<Object> offSpring = new ArrayList<Object>();
        offSpring.addAll(parent1.subList(0, crossOverPoint1));
        offSpring.addAll(parent2.subList(crossOverPoint1, parent1.size()));

        return individual.getConverter().getIndividual(offSpring);
    }
}
