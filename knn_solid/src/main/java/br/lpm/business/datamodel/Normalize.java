package br.lpm.business.datamodel;

import java.util.List;

public interface Normalize {
  public List<DataPoint> normalizeData(List<DataPoint> data); 
}
