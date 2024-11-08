package br.lpm.loaders;

import br.lpm.data_structures.DataSet;

public interface DataLoader {
  public void load(String filename, DataSet dataSet);
}