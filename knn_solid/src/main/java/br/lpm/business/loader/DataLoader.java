package br.lpm.business.loader;

import br.lpm.business.datamodel.DataSet;

public interface DataLoader {
  public void load(String filename, DataSet dataSet);
}