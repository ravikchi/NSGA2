package com.ravi.NSGA2;


import com.ravi.GenericGA.GeneticAlgorithm.*;
import com.ravi.GenericGA.GeneticAlgorithm.Exceptions.GAException;
import com.ravi.GenericGA.GeneticAlgorithm.impl.CrossOver.TwoPointCrossOver;
import com.ravi.GenericGA.GeneticAlgorithm.impl.MutationOpe.BitWiseMutate;
import com.ravi.GenericGA.Utils.RandomUtils;
import com.ravi.NSGA2.GeneticAlgorithm.*;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.ConeIndividual;
import com.ravi.NSGA2.GeneticAlgorithm.Objectives.MinS;
import com.ravi.NSGA2.GeneticAlgorithm.Objectives.MinT;
import com.ravi.NSGA2.GeneticAlgorithm.Objectives.Objective;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        NSGAPopulation.runAlgorithm();

    }




}
