package com.ravi.NSGA2.GeneticAlgorithm;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.SelectionOperator;
import com.ravi.GenericGA.Utils.RandomUtils;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.MultiObjectiveIndividual;

import java.util.List;

/**
 * Created by ravik on 12/02/2017.
 */
public class CrowdingSelector implements SelectionOperator {

    @Override
    public Individual selection(List<Individual> list) {
        MultiObjectiveIndividual i = (MultiObjectiveIndividual) list.get(RandomUtils.randomIntWithRange(0, list.size()-1));
        MultiObjectiveIndividual j = (MultiObjectiveIndividual) list.get(RandomUtils.randomIntWithRange(0, list.size()-1));
        if(i.getRank() > j.getRank()){
            return i;
        }else if(i.getRank() < j.getRank()){
            return j;
        }else{
            if(i.getiDistance() > j.getiDistance()){
                return i;
            }else{
                return j;
            }
        }
    }
}
