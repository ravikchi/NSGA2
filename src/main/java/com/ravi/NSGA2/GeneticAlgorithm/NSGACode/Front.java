package com.ravi.NSGA2.GeneticAlgorithm.NSGACode;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravik on 12/02/2017.
 */
public class Front {
    private List<Individual> elements = new ArrayList<Individual>();

    public List<Individual> getElements() {
        return elements;
    }

    public void setElements(List<Individual> elements) {
        this.elements = elements;
    }

    public void addElement(Individual individual){
        elements.add(individual);
    }

    public boolean isEmpty(){
        return elements.isEmpty();
    }
}
