package RevisaodeAed;

 public class RevisaodeAed {
  public static void main(String[] args) {
    int numCirc = Integer.parseInt(args[0]);
    double[] raios;
    double[] areas;

    raios = new double[numCirc];
    areas = new double[numCirc];

    for (int i = 0; i < numCirc; i++) {
      raios[i] = Math.random();
      areas[i] = Math.PI * raios[i] * raios[i];
      
      System.out.println("Area de circ com raio " + raios[i] + " é igual a " + areas[i] + ".");
  
    }

  }
}
