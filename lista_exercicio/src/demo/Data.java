package demo;

public class Data {
  private int dia;
  private int mes;
  private int ano;

  public Data(int dia, int mes, int ano) {
    this.dia = dia;
    this.mes = mes;
    this.ano = ano;
  }

  public Data() {}

  public int getDia() {
    return dia;
  }

  public int getMes() {
    return mes;
  }

  public int getAno() {
    return ano;
  }

  public int diasNoMes() {
    int[] diasNoMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if (mes == 2 && eAnoBisexto()) {
      return 29;
    }
    return diasNoMes[mes];
  }

  public boolean eAnoBisexto() {
    boolean divisivelPorQuatro = (ano % 4 == 0);
    boolean divisivelPorQuatrocento = (ano % 400 == 0);
    boolean naoDivisivelPorCem = (ano % 100 != 0);

    if (divisivelPorQuatro || divisivelPorQuatrocento && naoDivisivelPorCem) {
      return true;
    }
    
    return false;
  }

  public void proximoDia() {
    if (dia < diasNoMes()) {
      dia++;
    } else {
      dia = 1;
      if (mes < 12) {
        mes++;
      } else {
        mes = 1;
        ano++;
      }
    }
  }

  public String diaDaSemana() {
    int descobrirDiaSemana =
        dia + 2 * mes + (3 * (mes + 1) / 5) + ano + ano / 4 - ano / 100 + ano / 400 + 2;
    int dividirPorSete = descobrirDiaSemana % 7;
    switch (dividirPorSete) {
      case 0:
        return "Sábado";
      case 1:
        return "Domingo";
      case 2:
        return "Segunda-feira";
      case 3:
        return "Terça-feira";
      case 4:
        return "Quarta-feira";
      case 5:
        return "Quinta-feira";
      case 6:
        return "Sexta-feira";
      default:
        return "";
    }
  }

  public void adicionarDias(int dias) {
    int diaMes = diasNoMes();
    int diasRestantes;
    int x;
    int soma = dia + dias;
    if (soma <= diasNoMes()) {
      dia += dias;
    } else {
      diasRestantes = diaMes - dia + 1;
      x = dias - diasRestantes;
      dia = 1;
      if (mes < 12) {
        mes++;
      } else {
        mes = 1;
        ano++;
      }
      adicionarDias(x);
    }
  }

  public String porExtenso() {
    String[] mesString = {
      "",
      "Janeiro",
      "Fevereiro",
      "Março",
      "Abril",
      "Maio",
      "Junho",
      "Julho",
      "Agosto",
      "Setembro",
      "Outubro",
      "Novembro",
      "Dezembro"
    };
    return dia + " de " + mesString[mes] + " de " + ano;
  }
}
