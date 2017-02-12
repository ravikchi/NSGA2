package com.ravi.NSGA2.GeneticAlgorithm.Individuals;

import com.ravi.GenericGA.GeneticAlgorithm.Converter;
import com.ravi.NSGA2.GeneticAlgorithm.Objectives.Objective;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravik on 11/02/2017.
 */
public class ConeIndividual extends MultiObjectiveIndividual {
    private StringBuilder chromosome;
    private List<Object> phenoType = new ArrayList<Object>();
    private int geneSize;
    private Converter converter;
    private List<Objective> objectives = new ArrayList<Objective>();

    public ConeIndividual(String chromosome, Converter converter, int geneSize) {
        this.chromosome = new StringBuilder(chromosome);
        this.converter = converter;
        this.geneSize =geneSize;
        setVolume();
    }

    public ConeIndividual(List<Object> phenoType, Converter converter, int geneSize) {
        this.phenoType = phenoType;
        this.converter = converter;
        this.geneSize =geneSize;
        setVolume();
    }

    public void setGeneSize(int geneSize) {
        this.geneSize = geneSize;
    }

    @Override
    public String getChromosome() {
        if(chromosome == null){
            chromosome = new StringBuilder();
            for (Object pheno : phenoType) {
                chromosome.append(converter.getGene((String) pheno));
            }
        }
        return chromosome.toString();
    }

    @Override
    public List<Object> getPhenoType() {
        if(phenoType.isEmpty()){
            int i=1;
            StringBuilder gene = new StringBuilder();
            for(char c: chromosome.toString().toCharArray()){
                gene.append(c);
                if(i%geneSize == 0){
                    String phenoEle = converter.getPheno(gene.toString());
                    phenoType.add(phenoEle);
                    gene.setLength(0);
                }
                i++;
            }
        }

        return phenoType;
    }

    @Override
    public int geneSize() {
        return geneSize;
    }

    @Override
    public Converter getConverter() {
        return converter;
    }
}
