import java.io.BufferedReader;
import java.io.FileReader;

import weka.clusterers.EM;
import weka.core.Instances;

public class Clustering {

	public static void main(String[] args) throws Exception {
		// load data
		Instances data = new Instances(new BufferedReader(new FileReader("data/bank-data.arff")));
		// new instance of clusterer
		EM model = new EM();
		// build the clusterer
		model.buildClusterer(data);
		System.out.println(model);
	}

}
