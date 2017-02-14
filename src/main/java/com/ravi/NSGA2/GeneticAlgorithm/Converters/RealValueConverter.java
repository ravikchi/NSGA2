package com.ravi.NSGA2.GeneticAlgorithm.Converters;

import com.ravi.GenericGA.GeneticAlgorithm.Converter;
import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.MinCostIndividual;

import java.util.List;

/**
 * Created by rc16956 on 14/02/2017.
 */
public class RealValueConverter implements Converter {
    @Override
    public Individual getIndividual(String s) {
        return new MinCostIndividual(s, this);
    }

    @Override
    public Individual getIndividual(List<Object> list) {
        return new MinCostIndividual(list, this);
    }

    @Override
    public String getGene(String s) {
        return null;
    }

    @Override
    public String getPheno(String s) {
        return null;
    }
}
