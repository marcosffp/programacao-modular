package br.lpm.business.metric;

import br.lpm.business.model.DataPoint;

@FunctionalInterface
public interface Metric {
   public double distance(DataPoint p1, DataPoint p2);
}
