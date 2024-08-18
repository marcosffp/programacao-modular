package CadastroDePessoas.business.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import CadastroDePessoas.business.EstadoCivil;
import CadastroDePessoas.business.FormacaoAcademica;
import CadastroDePessoas.business.Pessoa;
import CadastroDePessoas.business.Profissao;

import java.time.LocalDate;
import org.junit.Test;

public class PessoaTest {

  /*
   * Testa nome com símbolo.
   */
  @Test
  public void testSetNomeSimbolo() {
    Pessoa pessoa = new Pessoa();
    String nome = "M@rco$";
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(nome);
        });
  }

  /*
   * Testa nome com número.
   */
  @Test
  public void testSetNomeNumero() {
    Pessoa pessoa = new Pessoa();
    String nome = "M1rc05";
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(nome);
        });
  }

  /*
   * Testa nome null.
   */
  @Test
  public void testSetNomeNull() {
    Pessoa pessoa = new Pessoa();
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(null);
        });
  }

  /*
   * Testa nome vazio.
   */
  @Test
  public void testSetNomeVazio() {
    Pessoa pessoa = new Pessoa();
    String nome = "";
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setNome(nome);
        });
  }

  /*
   * Testa nome válido.
   */
  @Test
  public void testSetNomeValido() {
    Pessoa pessoa = new Pessoa();
    String nome = "Marcos Alberto";
    pessoa.setNome(nome);
    assertEquals(nome, pessoa.getNome());
  }

  /*
   * Testa altura menor que 0.
   */
  @Test
  public void testSetAlturaValorMenorZero() {
    Pessoa pessoa = new Pessoa();
    float altura = -2f;
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setAltura(altura);
        });
  }

  /*
   * Testa altura maior que 3.
   */
  @Test
  public void testSetAlturaValorMaiorTres() {
    Pessoa pessoa = new Pessoa();
    float altura = 5f;
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setAltura(altura);
        });
  }

  /*
   * Testa altura igual a 0.
   */
  @Test
  public void testSetAlturaValorIgualZero() {
    Pessoa pessoa = new Pessoa();
    float altura = 0f;
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setAltura(altura);
        });
  }

  /*
   * Testa altura igual a 3.
   */
  @Test
  public void testSetAlturaValorIgualTres() {
    Pessoa pessoa = new Pessoa();
    float altura = 3f;
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setAltura(altura);
        });
  }

  /*
   * Testa altura válida.
   */
  @Test
  public void testSetAlturaValida() {
    Pessoa pessoa = new Pessoa();
    float altura = 1.70f;
    pessoa.setAltura(altura);
    assertEquals(altura, pessoa.getAltura(), 0.1f);
  }

  /*
   * Testa peso menor que 0.
   */
  @Test
  public void testSetPesoValorMenorZero() {
    Pessoa pessoa = new Pessoa();
    float peso = -40f;
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setPeso(peso);
        });
  }

  /*
   * Testa peso maior que 500.
   */
  @Test
  public void testSetPesoValorMaiorQuinhentos() {
    Pessoa pessoa = new Pessoa();
    float peso = 900f;
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setPeso(peso);
        });
  }

  /*
   * Testa peso igual a 0.
   */
  @Test
  public void testSetPesoValorIgualZero() {
    Pessoa pessoa = new Pessoa();
    float peso = 0f;
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setPeso(peso);
        });
  }

  /*
   * Testa peso igual a 500.
   */
  @Test
  public void testSetPesoValorIgualQuinhentos() {
    Pessoa pessoa = new Pessoa();
    float peso = 500f;
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setPeso(peso);
        });
  }

  /*
   * Testa peso válido.
   */
  @Test
  public void testSetPesoValido() {
    Pessoa pessoa = new Pessoa();
    float peso = 60.5f;
    pessoa.setPeso(peso);
    assertEquals(peso, pessoa.getPeso(), 0.1f);
  }

  /*
   * Testa data de nascimento hoje.
   */
  @Test
  public void testSetDataNascimentoHoje() {
    Pessoa pessoa = new Pessoa();
    LocalDate dataNascimento = LocalDate.now();
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setDataNascimento(dataNascimento);
        });
  }

  /*
   * Testa data de nascimento no futuro.
   */
  @Test
  public void testSetDataNascimentoFuturo() {
    Pessoa pessoa = new Pessoa();
    LocalDate dataNascimento = LocalDate.now().of(2050, 10, 3);
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setDataNascimento(dataNascimento);
        });
  }

  /*
   * Testa data de nascimento válido.
   */
  @Test
  public void testSetDataNascimentoValido() {
    Pessoa pessoa = new Pessoa();
    LocalDate dataNascimento = LocalDate.now().of(2005, 10, 3);
    pessoa.setDataNascimento(dataNascimento);
    assertEquals(dataNascimento, pessoa.getDataNascimento());
  }

  /*
   * Testa estado civil nulo.
   */
  @Test
  public void testSetEstadoCivilNulo() {
    Pessoa pessoa = new Pessoa();
    assertThrows(IllegalArgumentException.class, () -> {
      pessoa.setEstadoCivil(null);
    });
  }

  /*
   * Testa estado civil válido.
   */
  @Test
  public void testSetEstadoCivilValido() {
    Pessoa pessoa = new Pessoa();
    String estadoCivil = "SOLTEIRO";
    pessoa.setEstadoCivil(EstadoCivil.valueOf(estadoCivil));
    assertEquals(EstadoCivil.valueOf(estadoCivil), pessoa.getEstadoCivil());
  }

  /*
   * Testa formação acadêmica nulo.
   */
  @Test
  public void testsetFormacaoAcademicaNulo() {
    Pessoa pessoa = new Pessoa();
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          pessoa.setFormacaoAcademica(null);
        });
  }

  /*
   * Testa formação acadêmica válido.
   */
  @Test
  public void testSetFormacaoAcademicaValido() {
    Pessoa pessoa = new Pessoa();
    String formacaoAcademica = "MEDIA";
    pessoa.setFormacaoAcademica(FormacaoAcademica.valueOf(formacaoAcademica));
    assertEquals(FormacaoAcademica.valueOf(formacaoAcademica), pessoa.getFormacaoAcademica());
  }

  /*
   * Testa profissão nulo.
   */
  @Test
  public void testSetProfissaoNulo() {
    Pessoa pessoa = new Pessoa();
    String profissao = "ESTUDANTE";
    assertThrows(IllegalArgumentException.class, () -> {
      pessoa.setProfissao(null);
    });
  }

  /*
   * Testa profissão válido
   */
  @Test
  public void testSetProfissaoValido() {
    Pessoa pessoa = new Pessoa();
    String profissao = "ESTUDANTE";
    pessoa.setProfissao(Profissao.valueOf(profissao));
    assertEquals(Profissao.valueOf(profissao), pessoa.getProfissao());
  }

  /*
   * Testa vida social 
   */
 
}
