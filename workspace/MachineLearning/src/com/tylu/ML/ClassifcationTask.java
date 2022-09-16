package com.tylu.ML;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClassifcationTask {

	public static void main(String[] args) throws Exception {
		DataSource source = new DataSource("data/iris.arff");
		Instances data = source.getDataSet();
		System.out.println(data.numInstances());

		AttributeSelection attSelection = new AttributeSelection();
		InfoGainAttributeEval eval = new InfoGainAttributeEval(); // 建立評估器
		Ranker search = new Ranker(); // 建立排行器

		attSelection.setEvaluator(eval);
		attSelection.setSearch(search);
		attSelection.SelectAttributes(data);
		int[] indices = attSelection.selectedAttributes();
		System.out.println("Selected attributes: "+weka.core.Utils.arrayToString(indices));
		
		String[] options = new String[1];
		options[0] = "-U";
		J48 treeJ48 = new J48(); // 建立 J48 決策樹演算法物件
		treeJ48.setOptions(options);
		treeJ48.buildClassifier(data); // 執行
		System.out.println(treeJ48); // 顯示結果
	}

}
