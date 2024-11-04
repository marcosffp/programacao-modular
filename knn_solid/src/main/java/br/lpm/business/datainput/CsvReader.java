package br.lpm.business.datainput;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import br.lpm.business.datamodel.Attribute;
import br.lpm.business.datamodel.DataPoint;
import br.lpm.business.datamodel.DataSet;
import br.lpm.business.dataparser.CompositeDataParser;

public class CsvReader extends DataReader {

  public CsvReader(CompositeDataParser compositeDataParser) {
    super(compositeDataParser);
  }

  @Override
  public void loadDataFrom(String filename, DataSet dataSet) {
    if (super.getCompositeDataParser() == null) {
      return;
    }

    try (BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
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

      for (String nome : nomesAtributos) {
        dataSet.addAttributeName(nome.trim());
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

        DataPoint dataPoint = new DataPoint();
        for (int i = 0; i < fields.length - 1; i++) {
          String trimmedField = fields[i].trim();
          try {
            dataPoint.addAttribute(new Attribute(super.getCompositeDataParser().parse(trimmedField)));
          } catch (Exception e) {
            System.err.println("Erro ao processar o campo: " + trimmedField + ". Ignorando o atributo.");
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
