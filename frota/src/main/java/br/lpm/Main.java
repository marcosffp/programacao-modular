package br.lpm;
import java.time.LocalDate;
import br.lpm.business.Estado;
import br.lpm.business.Frota;
import br.lpm.business.Manutencao;
import br.lpm.business.Mecanico;
import br.lpm.business.Motorista;
import br.lpm.business.Oficina;
import br.lpm.business.Pessoa;
import br.lpm.business.Rota;
import br.lpm.business.Veiculo;

public class Main {
    public static void main(String[] args) {
        Veiculo veiculo = new Veiculo("V8", 2024, "ASASDAD12", 250, Estado.TRANSITO);
        Motorista motorista = new Motorista("Marcos", 1, veiculo);
        Frota frota = new Frota();
        Rota rota = frota.newRota(motorista, veiculo, "bh", "rj");
        Manutencao manutencao = new Manutencao(LocalDate.now());
        Oficina oficina=new Oficina("São José", "Rua A");
        Mecanico mecanico = new Mecanico("Thadeu", 2, oficina);
        Pessoa pessoa = new Pessoa("Maysa", 3);

        System.out.println(veiculo);
        System.out.println();
        System.out.println();
        System.out.println(motorista);
        System.out.println();
        System.out.println();
        System.out.println(frota);
        System.out.println();
        System.out.println();
        System.out.println(rota);

    }
}