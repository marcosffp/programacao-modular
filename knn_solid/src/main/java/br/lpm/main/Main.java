package br.lpm.main;

import br.lpm.business.KNearestNeighbors.KnnClassifier;
import br.lpm.business.KNearestNeighbors.KnnRegressor;
import br.lpm.business.datainput.CsvReader;
import br.lpm.business.datainput.DataReader;
import br.lpm.business.datamodel.BaseDataSet;
import br.lpm.business.datamodel.DataPoint;
import br.lpm.business.datamodel.NormalizedDataSet;
import br.lpm.business.datamodel.SimpleDataSet;
import br.lpm.business.dataparser.CompositeDataParser;
import br.lpm.business.metrics.EuclideanDistanceMetric;
import br.lpm.business.metrics.Metric;

public class Main {
        private static final String PATH = "C:\\Users\\marco\\OneDrive\\Documentos\\GitHub\\programacao-modular\\knn_generico\\LPM - Turma 1 - Cadastro de Pessoas.csv";


        public static void main(String[] args) {
                try {
                        // Carregar o dataset e normalizar os dados
                        BaseDataSet conjuntoDeDados = loadDataset(PATH);
                        NormalizedDataSet conjuntoDeDadosNormalizado = normalizeDataset(conjuntoDeDados);

                        if (conjuntoDeDados.getDataPoints().size() < 6) {
                                System.err.println("Erro: O conjunto de dados contém menos de 6 pontos.");
                                return;
                        }

                        // Selecionar pontos de dados
                        DataPoint pontoA = conjuntoDeDados.getDataPoints().get(0);
                        DataPoint pontoB = conjuntoDeDados.getDataPoints().get(5);

                        // Exibir distâncias e classificações
                        displayDistancesAndClassifications(conjuntoDeDadosNormalizado, pontoA, pontoB);

                } catch (Exception e) {
                        System.err.println("Erro ao executar o programa: " + e.getMessage());
                }
        }

        private static BaseDataSet loadDataset(String path) throws Exception {
                BaseDataSet conjuntoDeDados = new SimpleDataSet(); // Replace with the actual concrete class
                DataReader leitorDeDados = new CsvReader();

                CompositeDataParser dataParser = new CompositeDataParser();
                // Adicione aqui os parsers para os diferentes tipos de dados se necessário
                leitorDeDados.loadDataFrom(path, conjuntoDeDados);

                leitorDeDados.loadDataFrom(path, conjuntoDeDados);
                return conjuntoDeDados;
        }

        private static NormalizedDataSet normalizeDataset(BaseDataSet dataset) {
                NormalizedDataSet normalizedDataSet = new NormalizedDataSet();
                normalizedDataSet.setAttributeNames(dataset.getAttributeNames());
                normalizedDataSet.setDataPoints(dataset.getDataPoints());

                for (String attributeName : dataset.getAttributeNames()) {
                        normalizedDataSet.normalizeField(attributeName);
                }
                return normalizedDataSet;
        }

        private static void displayDistancesAndClassifications(BaseDataSet conjuntoDeDados, DataPoint pontoA,
                        DataPoint pontoB) {
                Metric metricaEuclidiana = new EuclideanDistanceMetric();

                // Classificação usando KNN Classifier
                KnnClassifier classificadorKnn = new KnnClassifier(conjuntoDeDados, metricaEuclidiana, 3);
                String classificacaoA = classificadorKnn.predict(pontoA);
                String classificacaoB = classificadorKnn.predict(pontoB);

                System.out.printf("Classificação do Ponto A: %s%n", classificacaoA);
                System.out.printf("Classificação do Ponto B: %s%n", classificacaoB);

                // Distâncias Euclidianas
                System.out.printf("Distância Euclidiana - Mesmo Ponto (A): %.2f%n",
                                metricaEuclidiana.distance(pontoA, pontoA));
                System.out.printf("Distância Euclidiana - Mesmo Ponto (B): %.2f%n",
                                metricaEuclidiana.distance(pontoB, pontoB));
                System.out.printf("Distância Euclidiana - Ponto A para Ponto B: %.2f%n",
                                metricaEuclidiana.distance(pontoA, pontoB));

                // Regressão usando KNN Regressor, caso aplicável
                KnnRegressor regressorKnn = new KnnRegressor(conjuntoDeDados, metricaEuclidiana, 3);
                Double valorPreditoParaPontoA = regressorKnn.predict(pontoA);
                System.out.printf("Regressão para o Ponto A: %.2f%n", valorPreditoParaPontoA);
        }
}
