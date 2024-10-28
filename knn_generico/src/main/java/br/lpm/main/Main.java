package br.lpm.main;

import br.lpm.business.DataSet;
import br.lpm.business.Attribute;
import br.lpm.business.DataPoint;
import br.lpm.business.FelizMetric;
import br.lpm.business.Knn;
import br.lpm.business.Metric;
import br.lpm.business.EuclideanDistanceMetric;

public class Main {

        public static void main(String[] args) throws Exception {
                DataSet conjuntoDeDados = new DataSet();
                conjuntoDeDados.loadDataFromCSV(
                                "C:\\Users\\marco\\OneDrive\\Documentos\\GitHub\\programacao-modular\\knn_generico\\LPM - Turma 1 - Cadastro de Pessoas.csv");

                DataPoint pontoA = conjuntoDeDados.getDataPoints().get(0);
                DataPoint pontoB = conjuntoDeDados.getDataPoints().get(5);

                displayDistancesAndClassifications(conjuntoDeDados, pontoA, pontoB);

                classifyAdditionalPoints(conjuntoDeDados);
        }

        private static void displayDistancesAndClassifications(DataSet conjuntoDeDados, DataPoint pontoA,
                        DataPoint pontoB) {

                Metric metricaEuclidiana = new EuclideanDistanceMetric();
                Knn classificadorKnnEuclidiano = new Knn(conjuntoDeDados, metricaEuclidiana, 1);

                System.out.printf("Distância Euclidiana - Mesmo Ponto (A): %.2f%n",
                                metricaEuclidiana.distance(pontoA, pontoA));
                System.out.printf("Distância Euclidiana - Mesmo Ponto (B): %.2f%n",
                                metricaEuclidiana.distance(pontoB, pontoB));
                System.out.printf("Distância Euclidiana entre Ponto A e B: %.2f%n",
                                metricaEuclidiana.distance(pontoA, pontoB));

                System.out.println("Classificação de Ponto A usando Métrica Euclidiana: "
                                + getClassificationResult(classificadorKnnEuclidiano, pontoA));
                System.out.println("Classificação de Ponto B usando Métrica Euclidiana: "
                                + getClassificationResult(classificadorKnnEuclidiano, pontoB));

                Metric metricaFeliz = new FelizMetric();
                Knn classificadorKnnFeliz = new Knn(conjuntoDeDados, metricaFeliz, 1);

                System.out.printf("Distância (FelizMetric) - Mesmo Ponto (A): %.2f%n",
                                metricaFeliz.distance(pontoA, pontoA));
                System.out.printf("Distância (FelizMetric) - Mesmo Ponto (B): %.2f%n",
                                metricaFeliz.distance(pontoB, pontoB));
                System.out.printf("Distância entre Ponto A e B (FelizMetric): %.2f%n",
                                metricaFeliz.distance(pontoA, pontoB));

                System.out.println("Classificação de Ponto A usando FelizMetric: "
                                + getClassificationResult(classificadorKnnFeliz, pontoA));
                System.out.println("Classificação de Ponto B usando FelizMetric: "
                                + getClassificationResult(classificadorKnnFeliz, pontoB));
        }

        private static void classifyAdditionalPoints(DataSet conjuntoDeDados) {
                DataPoint pessoaMarcos = createDataPoint("Marcos", "10/3/2005", "Masculino", 1.60, 68, 2000.00,
                                "Belo Horizonte", "Com a família", "Solteiro",
                                "Ensino Superior", "Música", "Sim");
                DataPoint pessoaBernardo = createDataPoint("Bernardo", "3/1/2006", "Masculino", 1.72, 80, 600.00,
                                "Belo Horizonte", "Com a família", "Solteiro",
                                "Ensino Superior", "Game", "Sim");

                System.out.printf("Distância Euclidiana entre Marcos e Bernardo: %.2f%n",
                                new EuclideanDistanceMetric().distance(pessoaMarcos, pessoaBernardo));
        }

        private static DataPoint createDataPoint(String nome, String dataNascimento, String genero, double altura,
                        int peso, double renda, String cidade, String moradia,
                        String estadoCivil, String escolaridade, String hobby, String feliz) {
                DataPoint ponto = new DataPoint();
                ponto.addAttribute(new Attribute(nome));
                ponto.addAttribute(new Attribute(dataNascimento));
                ponto.addAttribute(new Attribute(genero));
                ponto.addAttribute(new Attribute(altura));
                ponto.addAttribute(new Attribute(peso));
                ponto.addAttribute(new Attribute(renda));
                ponto.addAttribute(new Attribute(cidade));
                ponto.addAttribute(new Attribute(moradia));
                ponto.addAttribute(new Attribute(estadoCivil));
                ponto.addAttribute(new Attribute(escolaridade));
                ponto.addAttribute(new Attribute(hobby));
                ponto.addAttribute(new Attribute(feliz));
                return ponto;
        }

        private static String getClassificationResult(Knn classificador, DataPoint ponto) {
                String result = classificador.classify(ponto);
                return result != null ? result : "Classe Indefinida";
        }
}
