package com.ravi.NSGA2.GeneticAlgorithm;


import com.ravi.GenericGA.GeneticAlgorithm.*;
import com.ravi.GenericGA.GeneticAlgorithm.Exceptions.GAException;
import com.ravi.NSGA2.GeneticAlgorithm.Converters.RealValueConverter;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.MinCostIndividual;
import com.ravi.NSGA2.GeneticAlgorithm.Objectives.*;
import com.ravi.NSGA2.GeneticAlgorithm.Operators.RealValueCrossOver;
import com.ravi.NSGA2.GeneticAlgorithm.Operators.RealValueMutation;
import com.ravi.NSGA2.GeneticAlgorithm.Selectors.CrowdingSelector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rc16956 on 14/02/2017.
 */
public class CostMinPopulation extends Population {
    public CostMinPopulation(GAOperators operators, NextGenSelector nextGenOperator) {
        super(operators, nextGenOperator);
    }

    public static void runAlgorithm(int populationSize, double crossOverPerc, double mutationPerc, int numberOfGen){
        List<Objective> objectives = new ArrayList<Objective>();
        objectives.add(new MinCost());
        objectives.add(new MaxSurfaceArea());
        objectives.add(new MaxVolume());

        Converter converter = new RealValueConverter();

        CrossOverOperator crossOverOperator = new RealValueCrossOver();
        MutationOperator mutationOperator = new RealValueMutation();
        NSGANextGenSelector nSGANextGenSelector = new NSGANextGenSelector(objectives);

        SelectionOperator selectionOperator = new CrowdingSelector();
        NextGenSelector nextGenSelector = nSGANextGenSelector;

        GAOperators gaOperators = new GAOperators(crossOverOperator, mutationOperator, selectionOperator);
        gaOperators.setCrossOverRate(crossOverPerc);
        gaOperators.setMutationRate(mutationPerc);
        Population population = new CostMinPopulation(gaOperators, nextGenSelector);


        population.setN(populationSize);
        List<Individual> individuals = new ArrayList<Individual>();
        while(individuals.size() < populationSize){
            List<Object> phenoType = new ArrayList<Object>();
            phenoType.add(Math.random()+"");
            phenoType.add(Math.random()+"");
            phenoType.add(Math.random()+"");
            phenoType.add(Math.random()+"");
            MinCostIndividual i = new MinCostIndividual(phenoType, converter);

            individuals.add(i);
        }

        individuals = nSGANextGenSelector.getNextGenPopulation(individuals, new ArrayList<Individual>());

        population.setPopulation(individuals);
        population.printStatistics();

        for(int i=0; i<numberOfGen; i++) {
            try {
                System.out.println("Generation :"+i);
                individuals = population.nextGeneration();
                //population.printStatistics();
            } catch (GAException e) {
                e.printStackTrace();
            }
            population.setPopulation(individuals);
        }

        population.printStatistics();
    }

    @Override
    public void printStatistics() {
        System.out.println("SA,V,C,L,Shape,Material");

        for(Individual p : getPopulation()) {
            List<Object> phenoType = p.getPhenoType();
            double shape = Double.parseDouble((String) phenoType.get(2));
            double x = Double.parseDouble((String) phenoType.get(0));
            double y = Double.parseDouble((String) phenoType.get(1));
            double mat = Double.parseDouble((String) phenoType.get(3));
            int max = 50;

            int length = (int)(x * max) + (int)(y * max);

            double SA = Utils.getSurfaceArea((int) (shape * 6), (int) (x * max), (int) (y * max));
            double V = Utils.getVolume((int) (shape * 6), (int) (x * max), (int) (y * max));
            double c = Utils.getCost(SA, V, (int) (3 * mat));

            StringBuilder output = new StringBuilder();
            output.append((int)SA + ",");
            output.append((int)V + ",");
            output.append((int)c + ",");
            output.append(length + ",");
            output.append(getShape((int) (shape * 6)) + ",");
            output.append(getMat((int) (3 * mat)));


            System.out.println(output.toString());
        }
    }

    public String getShape(int i){
        if(i==0){
            return "Tetrahedron";
        }
        if(i==1){
            return "Cube";
        }
        if(i==2){
            return "Octahedron";
        }
        if(i==3){
            return "Dodecahedron";
        }
        if(i==4){
            return "Icosahedron";
        }
        if(i==5){
            return "Sphere";
        }
        return "?";
    }
    public String getMat(int i){
        if(i==0){
            return "Wood";
        }
        if(i==1){
            return "Metal";
        }
        if(i==2){
            return "Plastic";
        }
        return "?";
    }
}
