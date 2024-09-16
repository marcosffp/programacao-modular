package br.lpm;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate dataPartida = LocalDate.of(2024, 9, 14);
        Viagem saoPaulo = new ViagemNacional("São Paulo", dataPartida, 3, 200);
        Viagem grecia = new ViagemInternacional("Grecia", dataPartida, 7, 1800, 3);
        
        System.out.println(saoPaulo.precoViagem());
    System.out.println(grecia.precoViagem());
    }
}