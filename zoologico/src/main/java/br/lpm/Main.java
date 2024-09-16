package br.lpm;

public class Main {
    public static void main(String[] args) {
      Ave papagaio = new Ave("Rio", 10, EstadoAve.VOA);
    Animal leao = new Mamifero("mufasa", 5, EstadoMamifero.CARNIVORO);

    

    Veterinario veterinario = new Veterinario("Marcos", "animais grandes");
    veterinario.realizarCheckUp(leao);
    veterinario.realizarCheckUp(papagaio);
  }
}
