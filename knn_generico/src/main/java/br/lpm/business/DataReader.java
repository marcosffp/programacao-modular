package br.lpm.business;

public interface DataReader {
  public void loadDataFrom(String filename, DataSet dataSet) throws InvalidFormatException;
}
