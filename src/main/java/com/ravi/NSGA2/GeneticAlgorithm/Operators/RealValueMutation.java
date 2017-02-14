package com.ravi.NSGA2.GeneticAlgorithm.Operators;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.MutationOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rc16956 on 14/02/2017.
 */
public class RealValueMutation implements MutationOperator {

    @Override
    public Individual mutate(Individual individual) {
        List<Object> parent = individual.getPhenoType();
        double mutationRate = 1/parent.size();

        List<Object> offSpring = new ArrayList<Object>();
        for(Object obj : parent){
            if(Math.random() < mutationRate){
                offSpring.add(Math.random()+"");
            }else{
                offSpring.add(obj);
            }
        }

        return individual.getConverter().getIndividual(offSpring);
    }
}
