package br.lpm;

public class Zoologico {
  private static final int MAX_ANIMAIS=55;
  private int numAnimais = 0;
  private Animal[] animais = new Animal[MAX_ANIMAIS];
  private Veterinario veterinario;
  public Zoologico(Veterinario veterinario) {
    this.veterinario = veterinario;
  }
  public Veterinario getVeterinario() {
    return veterinario;
  }
  public void setVeterinario(Veterinario veterinario) {
    this.veterinario = veterinario;
  }

  public static int getMaxAnimais() {
    return MAX_ANIMAIS;
  }
  
  public void addAnimal(Animal animal) {
    if (!(numAnimais < MAX_ANIMAIS)) {
      return;
    }
    for (int i = 0; i < numAnimais; i++) {
      if (animais[i].equals(animal)) {
        return;
      }
    }
    animais[numAnimais++] = animal;
  }

  public void removeAnimal(Animal animal) {
    for (int i = 0; i < numAnimais; i++) {
      if (animais[i].equals(animal)) {
        for (int j = i; j < numAnimais - 1; j++) {
          animais[j] = animais[j + 1];
        }
      }
    }
    animais[--numAnimais] = null;
  }

  public Animal getAnimal(String nome) {
    for (int i = 0; i < numAnimais; i++) {
      if (animais[i].getNome().equalsIgnoreCase(nome)) {
        return animais[i];
      }
    }
    return null;
  }

  public void replaceAnimal(Animal velho, Animal novo) {
    for (int i = 0; i < numAnimais; i++) {
      if (animais[i].equals(novo)) {
        return;
      }
      if (animais[i].equals(velho)) {
        animais[i] = novo;
      }
    }
  }
  
  public void removeAll() {
    for (int i = 0; i < numAnimais; i++) {
      animais[i] = null;
    }
  }

  public Animal[] getAll() {
    Animal[] aux = new Animal[numAnimais];
    for (int i = 0; i < numAnimais; i++) {
      aux[i] = animais[i];
    }
    return aux;
  }

  public int size() {
    return numAnimais;
  }
}
