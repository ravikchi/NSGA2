package com.ravi.NSGA2.GeneticAlgorithm.Individuals;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.NSGA2.GeneticAlgorithm.Objectives.Objective;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravik on 11/02/2017.
 */
public abstract class MultiObjectiveIndividual implements Individual {
    private int rank=1000;
    private int np;
    private List<Individual> sp = new ArrayList<Individual>();
    private double volume = 0.0;
    private Objective currentObjective;

    private double iDistance;

    public double getiDistance() {
        return iDistance;
    }

    public void setiDistance(double iDistance) {
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
        this.sp.clear();
    }

    public void SpUnionQ(Individual q){
        this.sp.add(q);
    }

    public void setVolume(){
        List<Object> phenoType = getPhenoType();
        double r = Double.parseDouble((String) phenoType.get(0));
        double h = Double.parseDouble((String) phenoType.get(1));

        double s = Math.sqrt((r*r)+(h*h));
        double S = Math.PI * r * s;

        volume = Math.PI / 3 * r * r * h;
    }

}
