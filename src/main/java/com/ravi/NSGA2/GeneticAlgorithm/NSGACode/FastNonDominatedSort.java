package com.ravi.NSGA2.GeneticAlgorithm.NSGACode;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.MultiObjectiveIndividual;
import com.ravi.NSGA2.GeneticAlgorithm.Objectives.Objective;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravik on 12/02/2017.
 */
public class FastNonDominatedSort {
    List<Objective> objectives = new ArrayList<Objective>();
    Front front;

    public FastNonDominatedSort(List<Objective> objectives) {
        this.objectives = objectives;
    }

    public void sort(List<Individual> P){
        Front front = new Front();
        for(Individual i : P){
            MultiObjectiveIndividual p = (MultiObjectiveIndividual) i;
            p.resetNp();
            p.clearSp();

            for(Individual j : P){
                MultiObjectiveIndividual q = (MultiObjectiveIndividual) j;
                if(dominates(p, q)){
                    p.SpUnionQ(q);
                }else if(dominates(q, p)){
                    p.incNp();
                }
            }

            if(p.getNp() == 0){
                p.setRank(1);
                front.addElement(p);
            }
        }

        if(front.isEmpty()){
            firstFrontEmpty(P);
        }

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
                    }
                }
            }
            i++;
            fi = Q;
        }
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

    private boolean dominates(MultiObjectiveIndividual p, MultiObjectiveIndividual q){
        for(Objective o : objectives){
            if(o.getFitness(p) > o.getFitness(q)){
                return false;
            }
        }

        return true;
    }
}
