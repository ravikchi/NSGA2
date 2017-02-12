package com.ravi.NSGA2.GeneticAlgorithm;

import com.ravi.GenericGA.GeneticAlgorithm.Converter;
import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.ConeIndividual;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by ravik on 11/02/2017.
 */
public class DoubleBinaryConverter implements Converter {
    private int geneSize;

    public DoubleBinaryConverter(int geneSize) {
        this.geneSize = geneSize;
    }

    @Override
    public Individual getIndividual(String genoType) {
        ConeIndividual individual = new ConeIndividual(genoType, this,geneSize);

        return individual;
    }

    @Override
    public Individual getIndividual(List<Object> phenoType) {
        ConeIndividual individual = new ConeIndividual(phenoType, this,geneSize);

        return individual;
    }

    @Override
    public String getGene(String pheno) {
        double value = Double.parseDouble(pheno);
        long binaryRadius = Double.doubleToLongBits(value);
        String binary = Long.toBinaryString(binaryRadius);
        if(binary.length() < geneSize){
            binary = StringUtils.leftPad(binary, geneSize, "0");
        }

        return binary;
    }

    @Override
    public String getPheno(String gene) {
        long value = Long.parseLong(gene, 2);
        return Double.longBitsToDouble(value)+"";
    }
}
