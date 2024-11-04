package br.lpm.business.datainput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import br.lpm.business.datamodel.SimpleAttribute;
import br.lpm.business.datamodel.BaseDataPoint;
import br.lpm.business.datamodel.BaseDataSet;
import br.lpm.business.datamodel.SimpleDataPoint;
import br.lpm.business.dataparser.CompositeDataParser;

public class CsvReader extends DataReader {
  
  public CsvReader(CompositeDataParser compositeDataParser) {
    super(compositeDataParser);
  }

  @Override
  public void loadDataFrom(String filename, BaseDataSet dataSet) {

    if (super.getCompositeDataParser() == null) {
      return;
    }

    try (BufferedReader file = new BufferedReader(new FileReader(filename))) {
      String line = file.readLine();
      if (line == null) {
        System.err.println("Erro: Arquivo CSV vazio.");
        return;
      }
      String[] nomesAtributos = line.split(";");
      if (nomesAtributos.length < 2) {
        System.err.println("Erro: Cabeçalho CSV com formato inválido.");
        return;
      }
      for (int i = 0; i < nomesAtributos.length; i++) {
        dataSet.addAttributeName(nomesAtributos[i].trim());
      }
      DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      symbols.setDecimalSeparator(',');
      DecimalFormat format = new DecimalFormat("0.#");
      format.setDecimalFormatSymbols(symbols);
      while ((line = file.readLine()) != null) {
        String[] fields = line.split(";");
        if (fields.length != nomesAtributos.length) {
          System.err.println("Erro: Número de campos incorreto na linha: " + line);
          continue;
        }
        BaseDataPoint dataPoint = new SimpleDataPoint();
        for (int i = 0; i < fields.length - 1; i++) {
          try {
            double value = format.parse(fields[i].trim()).doubleValue();
            dataPoint.addAttribute(new SimpleAttribute(super.getCompositeDataParser().parse(String.valueOf(value))));
          } catch (Exception e) {
            System.err.println("Erro no formato de campo: " + fields[i] + ". Ignorando o atributo.");
          }
        }
        dataPoint.setState(fields[fields.length - 1].trim());
        dataSet.addDataPoint(dataPoint);
      }
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
    }
  }

  @Override
  public String toString() {
    return "CsvReader: " + super.toString();
  }
}
