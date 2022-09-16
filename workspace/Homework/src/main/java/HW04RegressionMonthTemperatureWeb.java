
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.M5P;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

@WebServlet("/HW04RegressionMonthTemperatureWeb")
public class HW04RegressionMonthTemperatureWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HW04RegressionMonthTemperatureWeb() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		/* Load data */
		CSVLoader loader = new CSVLoader();
		loader.setFieldSeparator(",");
		loader.setSource(new File(
				"C:\\MachineLearning\\workspace\\Homework\\src\\main\\webapp\\data\\month_temperature - data.csv"));
		Instances data = loader.getDataSet();
		System.out.println(data);
		response.getWriter().write(data.toString());
		/* Build regression models */

		// set class index to Y1 (temperature)
		data.setClassIndex(data.numAttributes() - 2);

		// remove last attribute Y2
		Remove remove = new Remove();
		try {
			remove.setOptions(new String[] { "-R", data.numAttributes() + "" });
			remove.setInputFormat(data);
			data = Filter.useFilter(data, remove);

			// build a regression model
			LinearRegression model = new LinearRegression();
			model.buildClassifier(data);
			System.out.println(model);
			response.getWriter().write(model.toString());

			// 10-fold cross-validation
			Evaluation eval = new Evaluation(data);
			eval.crossValidateModel(model, data, 10, new Random(1), new String[] {});
			System.out.println(eval.toSummaryString());
			double coef[] = model.coefficients();
			System.out.println();
			response.getWriter().write("<br>");

			// build a regression tree model
			M5P md5 = new M5P();
			md5.setOptions(new String[] { "" });
			md5.buildClassifier(data);
			System.out.println(md5);
			response.getWriter().write(md5.toString());


			// 10-fold cross-validation
			eval.crossValidateModel(md5, data, 10, new Random(1), new String[] {});
			System.out.println(eval.toSummaryString());
			response.getWriter().write(eval.toSummaryString());
			System.out.println();
			response.getWriter().write("<br>");

		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
