package br.lpm;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Marcos", 1);
        Livro livro = new Livro("Java para iniciantes", "Afonso", 1234, 200);
        Bibliotecario bibliotecario = new Bibliotecario("Dani", 564);
        Sistema sistema = new Sistema();
        sistema.setBibliotecario(bibliotecario);
        bibliotecario.addLivros(livro);
        LocalDate dataInicio=LocalDate.of(2024, 9, 14);
        LocalDate dataTermino=LocalDate.of(2024,9,17);
        bibliotecario.registrarEmprestimo(livro, usuario, dataInicio, dataTermino);
        bibliotecario.registarDevolucao(livro, usuario);
     
    }
}