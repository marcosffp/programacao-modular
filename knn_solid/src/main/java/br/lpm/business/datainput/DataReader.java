package br.lpm.business.datainput;

import br.lpm.business.datamodel.BaseDataSet;
import br.lpm.business.dataparser.CompositeDataParser;

public interface DataReader {
  public void loadDataFrom(String filename, BaseDataSet dataSet);
  public void setCompositeDataParser(CompositeDataParser compositeDataParser);
}