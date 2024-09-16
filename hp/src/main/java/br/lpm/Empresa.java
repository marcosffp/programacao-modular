package br.lpm;

public class Empresa {
  private static final int MAX_PESSOAS = 55;
  private Pessoa[] clientes = new Pessoa[MAX_PESSOAS];
  private Funcionario[] funcionarios = new Funcionario[MAX_PESSOAS];
  private Funcionario presidente;
  private int numClientes = 0;
  private int numFuncionarios = 0;

  public Empresa(Funcionario presidente) {
    this.presidente = presidente;
    addFuncionario(presidente);
  }

  public void addCliente(Pessoa cliente) {
    if (numClientes >= MAX_PESSOAS) {
      throw new RuntimeException("Número máximo de clientes atingido.");
    }
    for (int i = 0; i < numClientes; i++) {
      if (clientes[i].equals(cliente)) {
        return;
      }
    }
    clientes[numClientes++] = cliente;
  }

  public void addFuncionario(Funcionario funcionario) {
    if (numFuncionarios >= MAX_PESSOAS) {
      throw new RuntimeException("Número máximo de funcionários atingido.");
    }
    for (int i = 0; i < numFuncionarios; i++) {
      if (funcionarios[i].equals(funcionario)) {
        return;
      }
    }
    funcionarios[numFuncionarios++] = funcionario;
  }

  public void removeCliente(Pessoa cliente) {
    for (int i = 0; i < numClientes; i++) {
      if (clientes[i].equals(cliente)) {
        for (int j = i; j < numClientes - 1; j++) {
          clientes[j] = clientes[j + 1];
        }
        clientes[--numClientes] = null;
        return;
      }
    }
  }

  public void removeFuncionario(Funcionario funcionario) {
    for (int i = 0; i < numFuncionarios; i++) {
      if (funcionarios[i].equals(funcionario)) {
        for (int j = i; j < numFuncionarios - 1; j++) {
          funcionarios[j] = funcionarios[j + 1];
        }
        funcionarios[--numFuncionarios] = null;
        return;
      }
    }
  }

  public Pessoa[] getAllClientes() {
    Pessoa[] result = new Pessoa[numClientes];
    System.arraycopy(clientes, 0, result, 0, numClientes);
    return result;
  }

  public Funcionario[] getAllFuncionarios() {
    Funcionario[] result = new Funcionario[numFuncionarios];
    System.arraycopy(funcionarios, 0, result, 0, numFuncionarios);
    return result;
  }

  public int sizeClientes() {
    return numClientes;
  }

  public int sizeFuncionarios() {
    return numFuncionarios;
  }

  public void replaceCliente(int index, Pessoa cliente) {
    if (index >= 0 && index < numClientes) {
      clientes[index] = cliente;
    } else {
      throw new IndexOutOfBoundsException("Índice fora dos limites.");
    }
  }

  public void replaceFuncionario(int index, Funcionario funcionario) {
    if (index >= 0 && index < numFuncionarios) {
      funcionarios[index] = funcionario;
    } else {
      throw new IndexOutOfBoundsException("Índice fora dos limites.");
    }
  }

  public Pessoa getClienteByName(String nome) {
    for (int i = 0; i < numClientes; i++) {
      if (clientes[i].getNome().equalsIgnoreCase(nome)) {
        return clientes[i];
      }
    }
    return null;
  }

  public Funcionario getFuncionarioByName(String nome) {
    for (int i = 0; i < numFuncionarios; i++) {
      if (funcionarios[i].getNome().equalsIgnoreCase(nome)) {
        return funcionarios[i];
      }
    }
    return null;
  }

  public void removeAll() {
    for (int i = 0; i < numClientes; i++) {
      clientes[i] = null;
    }
    numClientes = 0;

    for (int i = 0; i < numFuncionarios; i++) {
      funcionarios[i] = null;
    }
    numFuncionarios = 0;
  }

  // Getters e Setters
  public Funcionario getPresidente() {
    return presidente;
  }
}
