package TestDeSoftware;

public class CalculadoraIMC {
  private double peso, altura, imc;

  public CalculadoraIMC(double peso, double altura) {
    this.peso = peso;
    this.altura = altura;
  }

  public void calcular() {
    this.imc = peso / (altura * altura);
  }

  public boolean comSobrepeso() {
    return (imc > 25);
  }

  public double getImc() {
    return this.imc;
  }
}
