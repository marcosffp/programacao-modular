package tarefa01.Imc;

public class Imc {
  private double peso;
  private double altura;

  public Imc(double peso, double altura) {
    this.peso = peso;
    this.altura = altura;
  }

  public void setPeso(double peso) {
    this.peso = peso;
  }

  public double getPeso(){
    return peso;
}

  public void setAltura(double altura) {
    this.altura = altura;
  }

  public double getAltura() {
    return altura;
  }

  public double getImc() {
    return peso / altura * altura;
  }
}