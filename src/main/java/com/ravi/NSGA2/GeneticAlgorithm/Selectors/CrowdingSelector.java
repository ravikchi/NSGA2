package com.ravi.NSGA2.GeneticAlgorithm.Selectors;

import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.GenericGA.GeneticAlgorithm.SelectionOperator;
import com.ravi.GenericGA.Utils.RandomUtils;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.MultiObjectiveIndividual;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravik on 12/02/2017.
 */
public class CrowdingSelector implements SelectionOperator {

    @Override
    public Individual selection(List<Individual> list) {
        List<MultiObjectiveIndividual> tournament = new ArrayList<MultiObjectiveIndividual>();
        while(tournament.size() < (list.size()/10)) {
            tournament.add((MultiObjectiveIndividual) list.get(RandomUtils.randomIntWithRange(0, list.size() - 1)));
        }

        int best = 0;
        for(int i=0; i<tournament.size();i++){
            if(tournament.get(i).getSp().size() > best){
                best = tournament.get(i).getSp().size();
            }
        }

        List<MultiObjectiveIndividual> winners = new ArrayList<MultiObjectiveIndividual>();
        for(int i=0; i<tournament.size();i++){
            if(tournament.get(i).getSp().size() == best){
                winners.add(tournament.get(i));
            }
        }

        MultiObjectiveIndividual winner = winners.get(0);
        boolean equalIDistance = true;
        for(MultiObjectiveIndividual i : winners){
            if(i.getiDistance() > winner.getiDistance()){
                winner = i;
                equalIDistance = false;
            }
        }

        if(equalIDistance){
            return winners.get((int) (Math.random() * winners.size()-1));
        }

        return winner;

        //return winners.get((int) (Math.random() * winners.size()-1));
    }
}
