package br.lpm.business.datainput;

import br.lpm.business.datamodel.BaseDataSet;

public interface DataReader {
  public void loadDataFrom(String filename, BaseDataSet dataSet);
}