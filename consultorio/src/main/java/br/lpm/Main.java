package br.lpm;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Veiculo veiculoEmTransito = new Veiculo("V8", 2017, "SFFFF123", 600, Status.EM_TRANSITO);

        Veiculo veiculoEmManutencao = new Veiculo("M7", 2015, "ASD23", 500, Status.EM_MANUTENCAO);

        Motorista motorista = new Motorista("Marcos", 1, veiculoEmTransito);
        LocalDate data = LocalDate.of(2024, 9, 15);
        RotaEntrega rotaEntrega = new RotaEntrega("Santa Luzia", "Belo Horizonte", data, veiculoEmTransito, motorista);

        Oficina oficina = new Oficina("Alto tanque", "Rua a");
        Manutencao manutencao = new Manutencao(data, veiculoEmManutencao, oficina);
        oficina.addVeiculo(veiculoEmTransito);
        
    }
}