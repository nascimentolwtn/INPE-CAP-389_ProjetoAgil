package br.inpe.cap.projetoagil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class TestCaixaEletronico {
	
	@Test
	public void exibicaoMensagensCaixaEletronico() {
		CaixaEletronico caixa = new CaixaEletronico();
		assertEquals("Usuário Autenticado", caixa.logar());
		assertEquals("Retire seu dinheiro", caixa.sacar());
		assertEquals("Depósito recebido com sucesso", caixa.depositar());
		assertEquals("O saldo é R$xx,xx", caixa.saldo());
	}
	
	@Ignore
	@Test(expected=IllegalArgumentException.class)
	public void palavraInvalidaContendoCaracteresEspeciais() {
		fail("Caracteres especiais não deveriam ser permitidos.");
	}
	
}
