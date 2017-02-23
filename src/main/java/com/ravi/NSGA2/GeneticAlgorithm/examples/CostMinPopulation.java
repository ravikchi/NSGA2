package com.ravi.NSGA2.GeneticAlgorithm.examples;


import com.ravi.GenericGA.GeneticAlgorithm.*;
import com.ravi.GenericGA.GeneticAlgorithm.Exceptions.GAException;
import com.ravi.NSGA2.GeneticAlgorithm.Converters.RealValueConverter;
import com.ravi.NSGA2.GeneticAlgorithm.NSGANextGenSelector;
import com.ravi.NSGA2.GeneticAlgorithm.NSGACode.CrowdingDistanceAssignment;
import com.ravi.NSGA2.GeneticAlgorithm.NSGACode.FastNonDominatedSort;
import com.ravi.NSGA2.GeneticAlgorithm.examples.Objectives.*;
import com.ravi.NSGA2.GeneticAlgorithm.Operators.RealValueCrossOver;
import com.ravi.NSGA2.GeneticAlgorithm.Operators.RealValueMutation;
import com.ravi.NSGA2.GeneticAlgorithm.Selectors.CrowdingSelector;

import java.util.*;

/**
 * Created by rc16956 on 14/02/2017.
 */
public class CostMinPopulation extends Population {
    public CostMinPopulation(GAOperators operators, NextGenSelector nextGenOperator) {
        super(operators, nextGenOperator);
    }

    public static void runAlgorithm(int populationSize, double crossOverPerc, double mutationPerc, int numberOfGen){
        Calendar start = Calendar.getInstance();
        List<Objective> objectives = new ArrayList<Objective>();
        objectives.add(new MinCost());
        objectives.add(new MaxSurfaceArea());
        objectives.add(new MaxVolume());

        Converter converter = new RealValueConverter();

        CrossOverOperator crossOverOperator = new RealValueCrossOver();
        MutationOperator mutationOperator = new RealValueMutation();
        FastNonDominatedSort sort = new SortCostMin(objectives);
        CrowdingDistanceAssignment assignment = new CrowdingDistanceAssignment(objectives);
        NSGANextGenSelector nSGANextGenSelector = new NSGANextGenSelector(objectives, sort, assignment);

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

        Calendar end = Calendar.getInstance();

        population.printStatistics();

        System.out.println("Total time taken by Sort : "+ sort.getTotalTime()/1000 +"s");
        System.out.println("Total time taken setup CrowdingDistance : "+ assignment.getTotalTime()/1000 +"s");
        System.out.println("Total time to run full algo : "+(end.getTimeInMillis()-start.getTimeInMillis())/1000+"s");
    }

    @Override
    public void printStatistics() {
        System.out.println("SA,V,C,L,Shape,Material");

        Collections.sort(getPopulation(), new Comparator<Individual>(){
            public int compare(Individual o1, Individual o2){
                if(Double.parseDouble((String) o1.getPhenoType().get(3)) == Double.parseDouble((String) o2.getPhenoType().get(3))) {
                    if(Double.parseDouble((String) o1.getPhenoType().get(2)) == Double.parseDouble((String) o2.getPhenoType().get(2))) {
                        return 0;
                    }
                    return Double.parseDouble((String) o1.getPhenoType().get(2)) > Double.parseDouble((String) o2.getPhenoType().get(2)) ? -1 : 1;
                }
                return Double.parseDouble((String) o1.getPhenoType().get(3)) > Double.parseDouble((String) o2.getPhenoType().get(3)) ? -1 : 1;
            }
        });

        for(Individual p : getPopulation()) {
            /*if(p.getFitness() < -1){
                continue;
            }*/
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
            output.append(getMat((int) (3 * mat))+",");
            output.append(p.getFitness());


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
