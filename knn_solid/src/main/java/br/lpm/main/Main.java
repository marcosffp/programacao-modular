package br.lpm.main;

import java.util.Arrays;

import org.jfree.data.general.Dataset;

import br.lpm.business.KNearestNeighbors.KnnClassifier;
import br.lpm.business.KNearestNeighbors.KnnRegressor;
import br.lpm.business.datamodel.Attribute;
import br.lpm.business.datamodel.DataPoint;
import br.lpm.business.datamodel.DataSet;
import br.lpm.business.datamodel.NormalizedDataSet;
import br.lpm.business.loader.CsvLoader;
import br.lpm.business.loader.DataLoader;
import br.lpm.business.metrics.EuclideanDistanceMetric;
import br.lpm.business.metrics.Metric;

public class Main {
        private static final String BASE_DIRECTORY = "C:\\Users\\marco\\OneDrive\\Documentos\\GitHub\\programacao-modular";
        private static final String TEST_CSV_FILE = BASE_DIRECTORY
                        + "\\knn_solid\\LPM - Turma 1 - Cadastro de Pessoas.csv";

        public static void main(String[] args) {
                DataSet dataSetSimples = new DataSet();

                DataLoader dataLoader = new CsvLoader(';');
                dataLoader.load(TEST_CSV_FILE, dataSetSimples);
                System.out.println(dataSetSimples);
                NormalizedDataSet normalizedDataSet = new NormalizedDataSet();
                normalizedDataSet.addAttributeNames(dataSetSimples.getAttributeNames());
                normalizedDataSet.addDataPoints(dataSetSimples.getDataPoints());
                DataSet dn = normalizedDataSet.normalize();

                Metric m = new EuclideanDistanceMetric();
                DataPoint dp1 = dataSetSimples.getDataPoints().get(0);
                DataPoint dp2 = dataSetSimples.getDataPoints().get(1);

                System.out.println("Distância entre " + dp1 + " e " + dp2 + " = " + m.distance(dp1, dp2));

                KnnClassifier Classifier = new KnnClassifier(dn, 3, m);
                KnnRegressor Regressor = new KnnRegressor(dn, 3, m);

                DataPoint newPoint = new DataPoint();
                newPoint.addAttribute(new Attribute(1.6));
                newPoint.addAttribute(new Attribute(62));
                newPoint.addAttribute(new Attribute(2000));
                newPoint.addAttribute(new Attribute(1.6));
                newPoint.addAttribute(new Attribute(62));
        }
}
