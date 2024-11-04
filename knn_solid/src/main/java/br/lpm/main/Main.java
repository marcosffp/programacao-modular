package br.lpm.main;

import java.util.List;
import br.lpm.business.KNearestNeighbors.KnnClassifier;
import br.lpm.business.KNearestNeighbors.KnnRegressor;
import br.lpm.business.datainput.CsvReader;
import br.lpm.business.datainput.DataReader;
import br.lpm.business.datamodel.DataPoint;
import br.lpm.business.datamodel.DataSet;
import br.lpm.business.datamodel.NormalizedDataSet;
import br.lpm.business.metrics.EuclideanDistanceMetric;
import br.lpm.business.metrics.FelizMetric;
import br.lpm.business.dataparser.BooleanParser;
import br.lpm.business.dataparser.CompositeDataParser;
import br.lpm.business.dataparser.DateParser;
import br.lpm.business.dataparser.NumericParser;
import br.lpm.business.dataparser.SimpleCompositeDataParser;
import br.lpm.business.metrics.Metric;

public class Main {
        private static final String FILE_PATH = "C:\\Users\\marco\\OneDrive\\Documentos\\GitHub\\programacao-modular\\knn_solid\\LPM - Turma 1 - Cadastro de Pessoas.csv";

        public static void main(String[] args) {
                CompositeDataParser parser = createParser();
                DataSet dataSet = loadDataSet(parser, FILE_PATH);
                displayDataSet(dataSet);

                NormalizedDataSet normalizedDataSet = normalizeDataSet(dataSet);
                displayNormalizedDataSet(normalizedDataSet);

                Metric euclideanMetric = new EuclideanDistanceMetric();
                Metric felizMetric = new FelizMetric();

                System.out.println("\nDistâncias Euclidianas:");
                calcularDistancias(normalizedDataSet, euclideanMetric);

                System.out.println("\nDistâncias Feliz:");
                calcularDistancias(normalizedDataSet, felizMetric);

                int k = 3;
                KnnClassifier classifier = new KnnClassifier(normalizedDataSet, euclideanMetric, k);
                DataPoint newDataPoint = createNewDataPoint(normalizedDataSet);
                String predictedClass = classifier.predict(newDataPoint);
                System.out.printf("Classe prevista para o novo DataPoint: %s%n", predictedClass);

                KnnRegressor regressor = new KnnRegressor(normalizedDataSet, euclideanMetric, k);
                Double predictedValue = regressor.predict(newDataPoint);
                System.out.printf("Valor previsto para o novo DataPoint: %.4f%n", predictedValue);
        }

        private static CompositeDataParser createParser() {
                CompositeDataParser parser = new SimpleCompositeDataParser();
                parser.addParser(new BooleanParser());
                parser.addParser(new DateParser());
                parser.addParser(new NumericParser());
                return parser;
        }

        private static DataSet loadDataSet(CompositeDataParser parser, String filePath) {
                DataSet dataSet = new DataSet();
                DataReader dataReader = new CsvReader(parser);
                dataReader.loadDataFrom(filePath, dataSet);
                return dataSet;
        }

        private static void displayDataSet(DataSet dataSet) {
                System.out.printf("Dados carregados: %d registros.%n", dataSet.getDataPoints().size());
                System.out.println("--------------------------------------------------");
                System.out.printf("| %-10s | %s%n", "Index", "State");
                System.out.println("--------------------------------------------------");
                for (int index = 0; index < dataSet.getDataPoints().size(); index++) {
                        DataPoint dataPoint = dataSet.getDataPoints().get(index);
                        System.out.printf("| %-10d | %s%n", index, dataPoint.getState());
                }
                System.out.println("--------------------------------------------------");
        }

        private static NormalizedDataSet normalizeDataSet(DataSet dataSet) {
                NormalizedDataSet normalizedDataSet = new NormalizedDataSet();
                normalizedDataSet.addDataPoints(dataSet.getDataPoints());
                normalizedDataSet.normalizeData(normalizedDataSet.getDataPoints());
                return normalizedDataSet;
        }

        private static void displayNormalizedDataSet(NormalizedDataSet normalizedDataSet) {
                List<DataPoint> normalizedDataPoints = normalizedDataSet.getDataPoints();
                System.out.println("\nDados Normalizados:");
                System.out.println("--------------------------------------------------");
                System.out.printf("| %-10s | %s%n", "Index", "State");
                System.out.println("--------------------------------------------------");
                for (int i = 0; i < normalizedDataPoints.size(); i++) {
                        DataPoint normalizedDataPoint = normalizedDataPoints.get(i);
                        System.out.printf("| %-10d | %s%n", i, normalizedDataPoint.getState());
                }
                System.out.println("--------------------------------------------------");
        }

        private static void calcularDistancias(DataSet dataSet, Metric metric) {
                System.out.println("--------------------------------------------------");
                System.out.printf("| %-6s | %-6s | %s%n", "Index1", "Index2", "Distance");
                System.out.println("--------------------------------------------------");
                for (int i = 0; i < dataSet.getDataPoints().size(); i++) {
                        for (int j = i + 1; j < dataSet.getDataPoints().size(); j++) {
                                double distance = metric.distance(dataSet.getDataPoints().get(i), dataSet.getDataPoints().get(j));
                                System.out.printf("| %-6d | %-6d | %.4f%n", i, j, distance);
                        }
                }
                System.out.println("--------------------------------------------------");
        }

        private static DataPoint createNewDataPoint(NormalizedDataSet normalizedDataSet) {
                DataPoint newDataPoint = new DataPoint();
                newDataPoint.addAttributes(normalizedDataSet.getDataPoints().get(0).getAttributes());
                return newDataPoint;
        }
}
