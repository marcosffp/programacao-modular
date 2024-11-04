package br.lpm.business.metrics;

import br.lpm.business.datamodel.BaseDataPoint;

@FunctionalInterface
public interface Metric {
   public double distance(BaseDataPoint p1, BaseDataPoint p2);
}
