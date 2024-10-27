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
        DataSet datasetFeliz = new DataSet();
        datasetFeliz.loadDataFromCSV(
                "C:\\Users\\marco\\OneDrive\\Documentos\\GitHub\\programacao-modular\\knn_generico\\LPM - Turma 1 - Cadastro de Pessoas.csv");

        DataPoint primeiroPonto = datasetFeliz.getDataPoints().get(0);
        DataPoint segundoPonto = datasetFeliz.getDataPoints().get(3);

        Metric metricFeliz = new FelizMetric();
        System.out.println(
                primeiroPonto + "\nDistância (mesmo ponto): " + metricFeliz.distance(primeiroPonto, primeiroPonto));
        System.out.println(
                segundoPonto + "\nDistância (mesmo ponto): " + metricFeliz.distance(segundoPonto, segundoPonto));
        System.out.println(
                "\nDistância entre o primeiro e o segundo ponto: " + metricFeliz.distance(primeiroPonto, segundoPonto));
        System.out.println(
                "\nDistância entre o segundo e o primeiro ponto: " + metricFeliz.distance(segundoPonto, primeiroPonto));

        Knn classificadorKnn = new Knn(datasetFeliz, metricFeliz, 3);
        System.out.println("Classificação do primeiro ponto: " + classificadorKnn.classify(primeiroPonto));
        System.out.println("Classificação do segundo ponto: " + classificadorKnn.classify(segundoPonto));

        DataPoint pessoaTeste1 = new DataPoint();
        pessoaTeste1.addAttribute(new Attribute("Bruna"));
        pessoaTeste1.addAttribute(new Attribute("6/23/1999"));
        pessoaTeste1.addAttribute(new Attribute("Feminino"));
        pessoaTeste1.addAttribute(new Attribute(1.60));
        pessoaTeste1.addAttribute(new Attribute(62));
        pessoaTeste1.addAttribute(new Attribute(2000.00));
        pessoaTeste1.addAttribute(new Attribute("Belo Horizonte"));
        pessoaTeste1.addAttribute(new Attribute("Com a família"));
        pessoaTeste1.addAttribute(new Attribute("Solteiro"));
        pessoaTeste1.addAttribute(new Attribute("Ensino Médio"));
        pessoaTeste1.addAttribute(new Attribute("Música"));
        pessoaTeste1.addAttribute(new Attribute("Sim"));

        System.out.println("Classificação de Bruna: " + classificadorKnn.classify(pessoaTeste1));

        DataPoint pessoaTeste2 = new DataPoint();
        pessoaTeste2.addAttribute(new Attribute("Paulo André Sousa"));
        pessoaTeste2.addAttribute(new Attribute("1/25/2002"));
        pessoaTeste2.addAttribute(new Attribute("Masculino"));
        pessoaTeste2.addAttribute(new Attribute(1.82));
        pessoaTeste2.addAttribute(new Attribute(90));
        pessoaTeste2.addAttribute(new Attribute(5000.00));
        pessoaTeste2.addAttribute(new Attribute("Paracatu"));
        pessoaTeste2.addAttribute(new Attribute("Com a família"));
        pessoaTeste2.addAttribute(new Attribute("Solteiro"));
        pessoaTeste2.addAttribute(new Attribute("Ensino Superior"));
        pessoaTeste2.addAttribute(new Attribute("Game"));
        pessoaTeste2.addAttribute(new Attribute("Sim"));

        System.out.println("Classificação de Paulo: " + classificadorKnn.classify(pessoaTeste2));

        Metric metricEuclidiana = new EuclideanDistanceMetric();
        System.out.println("Distância entre primeiro e segundo ponto (Euclidiana): "
                + metricEuclidiana.distance(primeiroPonto, segundoPonto));
        System.out.println("Distância entre pessoaTeste1 e pessoaTeste2 (Euclidiana): "
                + metricEuclidiana.distance(pessoaTeste1, pessoaTeste2));
    }
}
