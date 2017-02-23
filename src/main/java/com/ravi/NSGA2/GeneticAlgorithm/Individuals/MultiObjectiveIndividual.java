package com.ravi.NSGA2.GeneticAlgorithm.Individuals;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.Objective;

import java.util.*;

/**
 * Created by ravik on 11/02/2017.
 */
public abstract class MultiObjectiveIndividual implements Individual{
    private int rank=1000;
    private int np;
    private List<Individual> sp = new ArrayList<Individual>();
    private Objective currentObjective;
    protected Map<Objective, Double> objectiveFitnessMap = new HashMap<Objective, Double>();
    protected List<Objective> objectives;

    private double iDistance;

    public synchronized double getiDistance() {
        return iDistance;
    }

    public synchronized void setiDistance(double iDistance) {
        this.iDistance = iDistance;
    }

    public Objective getCurrentObjective() {
        return currentObjective;
    }

    public void setCurrentObjective(Objective currentObjective) {
        this.currentObjective = currentObjective;
    }

    public double objectiveFitness(){
        return currentObjective.getFitness(this);
    }

    @Override
    public double getFitness() {
        return 1-rank;
    }

    public abstract double getFitness(Objective o);

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getNp() {
        return np;
    }

    public void incNp(){
        this.np = this.np + 1;
    }

    public void decNp(){
        this.np = this.np - 1;
    }

    public void resetNp(){
        this.np = 0;
    }

    public List<Individual> getSp() {
        return sp;
    }

    public void setSp(List<Individual> sp) {
        this.sp = sp;
    }

    public void clearSp(){
        this.sp = new ArrayList<Individual>();
    }

    public void SpUnionQ(Individual q){
        this.sp.add(q);
    }

    public List<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<Objective> objectives) {
        this.objectives = objectives;
    }

    public Map<Objective, Double> getObjectiveFitnessMap() {
        return objectiveFitnessMap;
    }
}
