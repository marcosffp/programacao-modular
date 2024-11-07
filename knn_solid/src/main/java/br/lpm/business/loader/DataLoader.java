package br.lpm.business.loader;

import br.lpm.business.dataset.DataSet;

public interface DataLoader {
  public void load(String filename, DataSet dataSet);
}