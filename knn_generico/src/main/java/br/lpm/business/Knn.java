package br.lpm.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Knn {
    private DataSet dataset;
    private Metric metric;
    private int k;

    public Knn(DataSet dataset, Metric metric, int k) {
        this.setDataset(dataset);
        this.setMetric(metric);
        this.setK(k);
    }

    public void setK(int k) {
        if (k <= 0 || k > dataset.getDataPoints().size()) {
            return;
        }
        this.k = k;
    }

    public int getK() {
        return k;
    }

    public DataSet getDataset() {
        return dataset;
    }

    public void setDataset(DataSet dataset) {
        if (dataset == null) {
            return;
        }
        this.dataset = dataset;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        if (metric == null) {
            return;
        }
        this.metric = metric;
    }

    private List<Double> calculateDistances(DataPoint attributes) {
        List<Double> distances = new ArrayList<>();
        for (DataPoint dataPoint : dataset.getDataPoints()) {
            distances.add(metric.distance(attributes, dataPoint));
        }
        return distances;
    }

    public String classify(DataPoint attributes) {
        List<DataPoint> pontosDeDados = dataset.getDataPoints();
        int numeroDePontos = pontosDeDados.size();
        List<Double> distancias = this.calculateDistances(attributes);

        Integer[] indices = new Integer[numeroDePontos];
        for (int i = 0; i < numeroDePontos; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, Comparator.comparingDouble(i -> distancias.get(i)));

        Map<Object, Integer> contagemDeClasses = new HashMap<>();

        for (int i = 0; i < k; i++) {
            Integer contagemAtual = contagemDeClasses.get(pontosDeDados.get(indices[i]).getState());
            if (contagemAtual == null) {
                contagemDeClasses.put(pontosDeDados.get(indices[i]).getState(), 1);
            } else {
                contagemDeClasses.put(pontosDeDados.get(indices[i]).getState(), contagemAtual + 1);
            }
        }

        Set<Map.Entry<Object, Integer>> conjuntoDeEntradas = contagemDeClasses.entrySet();
        Map.Entry<Object, Integer> vencedor = conjuntoDeEntradas.stream()
                .max((entrada1, entrada2) -> entrada1.getValue().compareTo(entrada2.getValue()))
                .get();

        return (String) vencedor.getKey();
    }

}
