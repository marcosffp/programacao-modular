package br.lpm.main;
import java.time.LocalDate;

import br.lpm.business.Automovel;
import br.lpm.business.Cor;
import br.lpm.business.Marca;
import br.lpm.business.NotaFiscal;
import br.lpm.business.Peca;
import br.lpm.business.Servico;
import br.lpm.business.Tipo;

public class Main {
    public static void main(String[] args) {
        Automovel automovel = new Automovel("Porshe", LocalDate.of(2014, 2, 15), Cor.AZUL);
        Servico servico = new Servico("Conserto do banco", 4);
        Peca peca = new Peca("Radio", Tipo.USADA, Marca.FIAT);

        NotaFiscal notaFiscal = new NotaFiscal("Marcos");
        notaFiscal.addAutomovel(automovel);
        notaFiscal.addPeca(peca);
        notaFiscal.addServico(servico);

        System.out.println(notaFiscal.calcularTotal());
        notaFiscal.exibirNota();
    }
}
