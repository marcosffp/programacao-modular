package br.lpm.main;

import br.lpm.business.KnnClassifier;
import br.lpm.business.KnnRegressor;
import br.lpm.data_structures.Attribute;
import br.lpm.data_structures.DataPoint;
import br.lpm.data_structures.DataSet;
import br.lpm.data_structures.NormalizedDataSet;
import br.lpm.loaders.CsvLoader;
import br.lpm.loaders.DataLoader;
import br.lpm.metrics.EuclideanDistanceMetric;
import br.lpm.metrics.Metric;

public class Main {
        private static final String BASE_DIRECTORY = "C:\\Users\\marco\\OneDrive\\Documentos\\GitHub\\programacao-modular";
        private static final String TEST_CSV_FILE = BASE_DIRECTORY
                        + "\\knn_solid\\LPM - Turma 1 - Cadastro de Pessoas.csv";

        public static void main(String[] args) {
                DataSet dataSetSimples = new DataSet();
                DataLoader dataLoader = new CsvLoader(';');
                dataLoader.load(TEST_CSV_FILE, dataSetSimples);
                System.out.println("Conjunto de dados carregado:\n" + dataSetSimples);
                NormalizedDataSet normalizedDataSet = new NormalizedDataSet();
                normalizedDataSet.addAttributeNames(dataSetSimples.getAttributeNames());
                normalizedDataSet.addDataPoints(dataSetSimples.getDataPoints());
                DataSet dn = normalizedDataSet.normalize();
                Metric metric = new EuclideanDistanceMetric();
                System.out.println("Comparação entre pontos do conjunto de dados simples:");
                for (int i = 0; i < dataSetSimples.getDataPoints().size(); i++) {
                        DataPoint dpSimples1 = dataSetSimples.getDataPoints().get(i);
                        for (int j = i + 1; j < dataSetSimples.getDataPoints().size(); j++) {
                                DataPoint dpSimples2 = dataSetSimples.getDataPoints().get(j);
                                double distancia = metric.distance(dpSimples1, dpSimples2);
                                System.out.println("Distância entre DataPoint " + i + " e DataPoint " + j
                                                + " no conjunto simples: " + distancia);
                        }
                }
                System.out.println("Comparação entre pontos do conjunto de dados normalizado:");
                for (int i = 0; i < dn.getDataPoints().size(); i++) {
                        DataPoint dpNormalizado1 = dn.getDataPoints().get(i);
                        for (int j = i + 1; j < dn.getDataPoints().size(); j++) {
                                DataPoint dpNormalizado2 = dn.getDataPoints().get(j);
                                double distancia = metric.distance(dpNormalizado1, dpNormalizado2);
                                System.out.println("Distância entre DataPoint " + i + " e DataPoint " + j
                                                + " no conjunto normalizado: " + distancia);
                        }
                }
                KnnClassifier classifier = new KnnClassifier(dn, 3, metric);
                KnnRegressor regressor = new KnnRegressor(dn, 3, metric);

                DataPoint exemplo = dataSetSimples.getDataPoints().get(0);
                Attribute predictedClass = classifier.predict(exemplo);
                System.out.println("Classe prevista para o ponto de exemplo: " + predictedClass.getValue());

                Attribute predictedValue = regressor.predict(exemplo);
                System.out.println("Valor previsto para o ponto de exemplo: " + predictedValue.getValue());
        }
}
