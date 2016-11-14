package br.inpe.cap.projetoagil;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TestCaixaEletronico {
	
	private CaixaEletronico caixa;
	
	@Rule
	public JUnitRuleMockery ctx = new JUnitRuleMockery();
	
	@Before
	public void setUp() {
		caixa = new CaixaEletronico();
	}
	
	@Test
	public void exibicaoMensagensCaixaEletronico() {
		final ServicoRemoto servicoRemotoMock = ctx.mock(ServicoRemoto.class);
		caixa.setServicoRemoto(servicoRemotoMock);
		String numeroContaTeste = "123456";
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		ctx.checking(new Expectations() {{
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar(numeroContaTeste));
		assertEquals("Retire seu dinheiro", caixa.sacar());
		assertEquals("Depósito recebido com sucesso", caixa.depositar());
		assertEquals("O saldo é R$xx,xx", caixa.saldo());
	}
	
	@Test
	public void recuperarConta() {
		final ServicoRemoto servicoRemotoMock = ctx.mock(ServicoRemoto.class);
		caixa.setServicoRemoto(servicoRemotoMock);
		String numeroContaTeste = "123456";
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		ctx.checking(new Expectations() {{
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar(numeroContaTeste));
	}
	
	@Test
	public void logarUsuarioNaoIdentificado() {
		CaixaEletronico caixa = new CaixaEletronico();
		final ServicoRemoto servicoRemotoMock = ctx.mock(ServicoRemoto.class);
		caixa.setServicoRemoto(servicoRemotoMock);
		String numeroContaInvalido = "123";
		ctx.checking(new Expectations() {{
			oneOf(servicoRemotoMock).recuperarConta(numeroContaInvalido);
			will(returnValue(null));
		}});
		assertEquals("Usuário não encontrado", caixa.logar(numeroContaInvalido));
	}
	
}
