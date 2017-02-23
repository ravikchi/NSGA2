package com.ravi.NSGA2.GeneticAlgorithm.NSGACode;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.Objective;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.MultiObjectiveIndividual;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ravik on 12/02/2017.
 */
public class FastNonDominatedSort{
    List<Objective> objectives = new ArrayList<Objective>();
    Front front;
    long totalTime = 0;

    public FastNonDominatedSort(List<Objective> objectives) {
        this.objectives = objectives;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    private void calculateObjectiveFitness(List<Individual> P){
        CountDownLatch latch = new CountDownLatch(objectives.size());

        for(Objective m : objectives){
            FindObjectiveFitness fitness = new FindObjectiveFitness(m, latch, P);
            Thread t = new Thread(fitness);
            t.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sort(List<Individual> P){
        Calendar start = Calendar.getInstance();
        Front front = new Front();
        int n =0;

        calculateObjectiveFitness(P);

        for(Individual i : P){
            MultiObjectiveIndividual p = (MultiObjectiveIndividual) i;
            p.resetNp();
            p.clearSp();
            p.setRank(1000);

            for(Individual j : P){
                if(i.equals(j)){
                    continue;
                }
                MultiObjectiveIndividual q = (MultiObjectiveIndividual) j;
                dominates(p, q);
            }

            if(p.getNp() == 0){
                p.setRank(1);
                front.addElement(p);
                n++;
            }
        }

        Calendar end = Calendar.getInstance();

        /*if(front.isEmpty()){
            firstFrontEmpty(P);
        }*/

        n = front.getElements().size();

        Front fi = front;
        int i = 1;
        while(!fi.isEmpty()) {
            Front Q = new Front();
            for (Individual pi : fi.getElements()) {
                MultiObjectiveIndividual p = (MultiObjectiveIndividual) pi;
                for (Individual pj : p.getSp()) {
                    MultiObjectiveIndividual q = (MultiObjectiveIndividual) pj;
                    q.decNp();
                    if (q.getNp() == 0) {
                        q.setRank(i + 1);
                        Q.addElement(q);
                        n++;
                    }
                }
            }
            if(n >= (P.size()+1)/2)
                break;
            i++;
            fi = Q;
        }

        //Calendar end = Calendar.getInstance();

        totalTime = totalTime + (end.getTimeInMillis() - start.getTimeInMillis());
    }

    private void firstFrontEmpty(List<Individual> population){
        for(Individual i:population){
            MultiObjectiveIndividual p = (MultiObjectiveIndividual) i;

            p.decNp();
            if(p.getNp() == 0){
                p.setRank(1);
                front.addElement(p);
            }
        }
    }

    private void dominates(MultiObjectiveIndividual p, MultiObjectiveIndividual q){
        boolean pDominates = true;
        boolean qDominates = true;
        for(Objective o : objectives){
            if(p.getFitness(o) > q.getFitness(o)){
                pDominates = false;
            }else if(q.getFitness(o) > p.getFitness(o)){
                qDominates = false;
            }
        }

        if(pDominates){
            p.SpUnionQ(q);
        }else if(qDominates){
            p.incNp();
        }
    }
}
