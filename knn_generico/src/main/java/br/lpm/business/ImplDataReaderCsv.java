package br.lpm.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.jfree.data.general.Dataset;

public class ImplDataReaderCsv implements DataReader  {

  @Override
  public void loadDataFrom(String filename, DataSet dataSet) throws InvalidFormatException {
    try (BufferedReader file = new BufferedReader(new FileReader(filename))) {
      String line = file.readLine();
      if (line == null) {
        throw new IOException("Arquivo CSV vazio");
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
            dataPoint.addAttribute(new Attribute(dataPoint.parse(fields[i].trim())));
          } catch (InvalidFormatException e) {
            throw new InvalidFormatException("Formato inválido no campo: " + fields[i]);
          }
        }

        dataPoint.setState(fields[fields.length - 1].trim());
        dataSet.addDataPoint(dataPoint);
      }
    } catch (IOException e) {
      throw new InvalidFormatException("Erro ao ler arquivo CSV: " + e.getMessage());
    }
  }


  
}
