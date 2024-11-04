package br.lpm.business.metrics;

import br.lpm.business.datamodel.DataPoint;

@FunctionalInterface
public interface Metric {
   public double distance(DataPoint p1, DataPoint p2);
}
