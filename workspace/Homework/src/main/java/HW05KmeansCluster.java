
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HW05KmeansCluster {

	private static final String TRAINING_DATASET_FILENAME = "C:\\MachineLearning\\workspace\\Homework\\src\\main\\webapp\\data\\iris.arff";
	public static void main(String[] args) {
		try {
		process();		
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	// loadDataSet()
	public static Instances loadDataSet(String fileName) {
		Instances instances = null;
		DataSource source;
		try {
			source = new DataSource(fileName);
			instances = source.getDataSet();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return instances;
	}
	
	// process()
	public static void process() throws Exception{
		SimpleKMeans kMeans = generateClassifier();
		
		System.out.println(kMeans.preserveInstancesOrderTipText());
		System.out.println(kMeans.toString());
	}
	
	// generateClassifier()
	public static SimpleKMeans generateClassifier() throws Exception{
	Instances instances = loadDataSet(TRAINING_DATASET_FILENAME);
	SimpleKMeans kMeans = new SimpleKMeans();
	kMeans.setNumClusters(3);
	kMeans.buildClusterer(instances);
	return kMeans;
	}
}