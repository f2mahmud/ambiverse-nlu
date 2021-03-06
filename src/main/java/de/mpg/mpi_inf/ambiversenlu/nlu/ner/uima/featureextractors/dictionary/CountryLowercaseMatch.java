/**
 * 
 */
package de.mpg.mpi_inf.ambiversenlu.nlu.ner.uima.featureextractors.dictionary;

import de.mpg.mpi_inf.ambiversenlu.nlu.ner.uima.type.CountryDictionaryMatch;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.features.FeatureExtractorResource_ImplBase;
import org.dkpro.tc.api.type.TextClassificationTarget;

import java.util.List;
import java.util.Set;

/**
 * @author Dominic Seyler (dseyler@mpi-inf.mpg.de)
 *
 * Extracts a binary feature that indicates whether the current token is
 * part of a dictionary match. 
 * 
 * REQUIREMENTS: You need to run DictionaryMatchAnnotator as part
 * of your pre-processing pipeline.
 *
 */
public class CountryLowercaseMatch extends FeatureExtractorResource_ImplBase implements FeatureExtractor {

	public static final String FEATURE_NAME = "CountryLowercaseMatch";


	@Override
	public Set<Feature> extract(JCas jCas, TextClassificationTarget unit) throws TextClassificationException {
		List<CountryDictionaryMatch>  dictMatch = JCasUtil.selectCovered(jCas, CountryDictionaryMatch.class, unit);
		if(dictMatch.size() != 0){
			return new Feature(FEATURE_NAME, true).asSet();
		}
		
		return new Feature(FEATURE_NAME, false).asSet();
	}

}
