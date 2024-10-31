package br.lpm.business.datainput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import br.lpm.business.datamodel.Attribute; 
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import br.lpm.business.datamodel.BaseDataSet;
import br.lpm.business.datamodel.DataPoint;
import br.lpm.business.dataparser.CompositeDataParser;


public class CsvReader implements DataReader {
  private CompositeDataParser compositeDataParser;

  @Override
  public void loadDataFrom(String filename, BaseDataSet dataSet) {
    try (BufferedReader file = new BufferedReader(new FileReader(filename))) {
      String line = file.readLine();
      if (line == null) {
        System.err.println("Erro: Arquivo CSV vazio.");
        return;
      }

      String[] nomesAtributos = line.split(";");
      for (int i = 0; i < nomesAtributos.length - 1; i++) {
        dataSet.addAttributeName(nomesAtributos[i].trim());
      }
      dataSet.setStateName(nomesAtributos[nomesAtributos.length - 1].trim());

      DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      symbols.setDecimalSeparator(',');
      DecimalFormat format = new DecimalFormat("0.#");
      format.setDecimalFormatSymbols(symbols);

      while ((line = file.readLine()) != null) {
        String[] fields = line.split(";");
        DataPoint dataPoint = new DataPoint();

        for (int i = 0; i < fields.length - 1; i++) {
          try {
            dataPoint.addAttribute(new Attribute(compositeDataParser.parse(fields[i].trim())));
          } catch (IllegalArgumentException e) {
            System.err.println("Formato inválido no campo: " + fields[i] + ". Ignorando o atributo.");
          }
        }

        dataPoint.setState(fields[fields.length - 1].trim());
        dataSet.addDataPoint(dataPoint);
      }
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
    }

  }
}