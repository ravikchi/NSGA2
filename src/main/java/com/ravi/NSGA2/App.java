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

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Objective> objectives = new ArrayList<Objective>();
        objectives.add(new MinS());
        objectives.add(new MinT());


        int geneSize = 63;
        Converter converter = new DoubleBinaryConverter(geneSize);
        double mutationRate = 1.0/(geneSize*2);

        CrossOverOperator crossOverOperator = new TwoPointCrossOver();
        MutationOperator mutationOperator = new BitWiseMutate(mutationRate);
        NSGANextGenSelector nSGANextGenSelector = new NSGANextGenSelector(objectives);

        SelectionOperator selectionOperator = new CrowdingSelector();
        NextGenSelector nextGenSelector = nSGANextGenSelector;

        GAOperators gaOperators = new GAOperators(crossOverOperator, mutationOperator, selectionOperator);
        Population population = new NSGAPopulation(gaOperators, nextGenSelector);


        int size = 20;
        population.setN(size);
        List<Individual> individuals = new ArrayList<Individual>();
        while(individuals.size() < size){
            List<Object> phenoType = new ArrayList<Object>();

            double radius = RandomUtils.randomIntWithRange(1, 9);
            String radiusStr = radius+"";
            System.out.println(radiusStr);
            phenoType.add(radiusStr);

            double height = RandomUtils.randomIntWithRange(1, 19);
            String heightStr = height +"";
            System.out.println(heightStr);
            phenoType.add(heightStr);
            ConeIndividual i = new ConeIndividual(phenoType, converter,geneSize);

            if((objectives.get(0).getFitness(i) == Double.POSITIVE_INFINITY) || objectives.get(1).getFitness(i) == Double.POSITIVE_INFINITY){
                continue;
            }

            individuals.add(i);
        }

        individuals = nSGANextGenSelector.getNextGenPopulation(individuals, new ArrayList<Individual>());

        population.setPopulation(individuals);

        for(int i=0; i<100; i++) {
            try {
                System.out.println("Generation :"+i);
                individuals = population.nextGeneration();
                population.printStatistics();

                System.out.println();
                System.out.println();
                System.out.println();
            } catch (GAException e) {
                e.printStackTrace();
            }
            population.setPopulation(individuals);
        }

        for(Individual i: individuals){
            List<Object> phenoType = i.getPhenoType();
            double r = Double.parseDouble((String) phenoType.get(0));
            double h = Double.parseDouble((String) phenoType.get(1));

            double s = Math.sqrt((r*r)+(h*h));
            double S = Math.PI * r * s;
            double B = Math.PI * r * r;

            double T = B+S;

            System.out.println("S : "+ S);
            System.out.println("T : "+ T);

            double V = Math.PI / 3 * r * r * h;

            System.out.println("Volume : "+ V);

            System.out.println();
            System.out.println();
            System.out.println();
        }

    }




}
