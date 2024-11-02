package br.lpm.main;

import br.lpm.business.datainput.CsvReader;
import br.lpm.business.datainput.DataReader;
import br.lpm.business.datamodel.Attribute;
import br.lpm.business.datamodel.BaseDataSet;
import br.lpm.business.datamodel.DataPoint;
import br.lpm.business.datamodel.NormalizedDataSet;
import br.lpm.business.datamodel.SimpleDataSet;
import br.lpm.business.dataparser.BooleanParser;
import br.lpm.business.dataparser.CompositeDataParser;

import br.lpm.business.dataparser.DateParser;
import br.lpm.business.dataparser.NumericParser;
import br.lpm.business.dataparser.ImplCompositeDataParser;

public class Main {
        private static final String FILE_PATH = "C:\\Users\\marco\\OneDrive\\Documentos\\GitHub\\programacao-modular\\knn_solid\\LPM - Turma 1 - Cadastro de Pessoas.csv";

        public static void main(String[] args) {
                // Criação do dataset simples e normalizado
                BaseDataSet dataSetSimple = new SimpleDataSet();
                BaseDataSet dataSetNormalized = new NormalizedDataSet();

                // Configuração do parser composto com parsers de diferentes tipos de dados
                CompositeDataParser compositeDataParser = new ImplCompositeDataParser();
                compositeDataParser.addParser(new NumericParser());
                compositeDataParser.addParser(new BooleanParser());
                compositeDataParser.addParser(new DateParser());

                // Configuração do CsvReader com o parser composto
                DataReader dataReader = new CsvReader();
                dataReader.setCompositeDataParser(compositeDataParser);

                // Carregamento dos dados do arquivo CSV no dataset simples
                dataReader.loadDataFrom(FILE_PATH, dataSetSimple);

                // Exibição dos dados carregados no dataset simples
                System.out.println("Dados do DataSet Simples:");
                displayDataSet(dataSetSimple);

                // Criação de atributos e DataPoints para visualização
                createAttribute();
                createDataPoint();
                
        }

        private static void displayDataSet(BaseDataSet dataSet) {
                System.out.println("Atributos:");
                for (String attributeName : dataSet.getAttributeNames()) {
                        System.out.print(attributeName + " | ");
                }
                System.out.println("\n-----------------------------");

                for (DataPoint dataPoint : dataSet.getDataPoints()) {
                        System.out.println(dataPoint);
                }
        }

        private static void createAttribute() {
                Attribute attribute = new Attribute("Nome da Pessoa");
                System.out.println("Atributo criado: " + attribute);
        }

        private static void createDataPoint() {
                DataPoint dataPoint = new DataPoint();
                System.out.println("DataPoint criado: " + dataPoint);
        }
}
