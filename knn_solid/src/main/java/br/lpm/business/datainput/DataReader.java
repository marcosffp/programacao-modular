package br.lpm.business.datainput;

import br.lpm.business.datamodel.BaseDataSet;
import br.lpm.business.dataparser.CompositeDataParser;

public abstract class DataReader {

  private CompositeDataParser compositeDataParser;

  public DataReader(CompositeDataParser compositeDataParser) {
    this.compositeDataParser = compositeDataParser;
  }
  public abstract void loadDataFrom(String filename, BaseDataSet dataSet);

  public void setCompositeDataParser(CompositeDataParser compositeDataParser) {
    this.compositeDataParser = compositeDataParser;
  }

  public CompositeDataParser getCompositeDataParser() {
    return this.compositeDataParser;
  }

  @Override
  public String toString() {
    return "DataReader: " + compositeDataParser.toString();
  }

}