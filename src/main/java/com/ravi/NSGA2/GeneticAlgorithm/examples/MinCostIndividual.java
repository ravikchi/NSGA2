package com.ravi.NSGA2.GeneticAlgorithm.examples;

import com.ravi.GenericGA.GeneticAlgorithm.Converter;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.MultiObjectiveIndividual;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by rc16956 on 14/02/2017.
 */
public class MinCostIndividual extends MultiObjectiveIndividual {
    private StringBuilder chromosome;
    private List<Object> phenoType = new ArrayList<Object>();
    private int geneSize;
    private Converter converter;

    public MinCostIndividual(String chromosome, Converter converter) {
        this.chromosome = new StringBuilder();
        this.chromosome.append(chromosome);
        this.geneSize = geneSize;
        this.converter = converter;
    }

    public MinCostIndividual(List<Object> phenoType, Converter converter) {
        this.phenoType = phenoType;
        this.geneSize = geneSize;
        this.converter = converter;
    }

    public void setGeneSize(int geneSize) {
        this.geneSize = geneSize;
    }

    @Override
    public String getChromosome() {
        if(chromosome == null){
            StringBuilder pheno = new StringBuilder();
            for(Object obj : phenoType){
                pheno.append(obj+",");
            }
            chromosome.append(converter.getGene(pheno.toString().substring(0, pheno.length()-1)));
        }
        return chromosome.toString();
    }

    @Override
    public List<Object> getPhenoType() {
        if(phenoType.isEmpty()){
            String genes = converter.getPheno(this.chromosome.toString());
            String[] geneArr = genes.split(",");

            for(String gene : geneArr){
                this.phenoType.add(gene);
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
