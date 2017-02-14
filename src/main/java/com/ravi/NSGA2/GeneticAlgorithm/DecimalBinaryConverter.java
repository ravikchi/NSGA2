package com.ravi.NSGA2.GeneticAlgorithm;

import com.ravi.GenericGA.GeneticAlgorithm.Converter;
import com.ravi.GenericGA.GeneticAlgorithm.Individual;
import com.ravi.NSGA2.GeneticAlgorithm.Individuals.ConeIndividual;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by rc16956 on 14/02/2017.
 */
public class DecimalBinaryConverter implements Converter {
    private int geneSize;

    public DecimalBinaryConverter(int geneSize) {
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
    public String getGene(String s) {
        int index = s.indexOf('.');
        int value = Integer.parseInt(s.substring(0, index));
        int mantissa = Integer.parseInt(s.substring(index+1, Math.min(s.length(), index+4)));


        String prefix = Integer.toBinaryString(value);
        String manti = Integer.toBinaryString(mantissa);

        return StringUtils.leftPad(prefix, geneSize/2, "0") + StringUtils.leftPad(manti, geneSize/2, "0");
    }

    @Override
    public String getPheno(String s) {
        String p = s.substring(0, geneSize/2);
        String m = s.substring(geneSize/2, s.length());

        return Integer.parseInt(p, 2)+"."+Integer.parseInt(m,2);
    }
}
