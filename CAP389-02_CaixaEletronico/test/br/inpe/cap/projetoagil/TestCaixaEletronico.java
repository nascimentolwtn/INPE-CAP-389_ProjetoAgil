package br.inpe.cap.projetoagil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TestCaixaEletronico {
	
	private CaixaEletronico caixa;
	private ServicoRemoto servicoRemotoMock;
	
	@Rule
	public JUnitRuleMockery ctx = new JUnitRuleMockery();
	
	@Before
	public void setUp() {
		caixa = new CaixaEletronico();
		servicoRemotoMock = ctx.mock(ServicoRemoto.class);
		caixa.setServicoRemoto(servicoRemotoMock);
	}
	
	@Test
	public void exibicaoMensagensCaixaEletronico() {
		String numeroContaTeste = "123456";
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		ctx.checking(new Expectations() {{
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar(numeroContaTeste));
		assertEquals("Retire seu dinheiro", caixa.sacar(0));
		assertEquals("Depósito recebido com sucesso", caixa.depositar());
		assertEquals("O saldo é R$ 0,00", caixa.saldo());
	}
	
	@Test
	public void recuperarConta() {
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
		String numeroContaInvalido = "123";
		ctx.checking(new Expectations() {{
			oneOf(servicoRemotoMock).recuperarConta(numeroContaInvalido);
			will(returnValue(null));
		}});
		assertEquals("Usuário não encontrado", caixa.logar(numeroContaInvalido));
	}
	
	@SuppressWarnings("unused")
	@Test(expected = UsuarioNaoLogadoException.class)
	public void executarOperacaoSaldoSemUsuarioLogado() {
		String saldo = caixa.saldo();
		fail("Operação não deveria ser executada sem nenhum usuário logado no caixa.");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = UsuarioNaoLogadoException.class)
	public void executarOperacaoSacarSemUsuarioLogado() {
		String sacar = caixa.sacar(0);
		fail("Operação não deveria ser executada sem nenhum usuário logado no caixa.");
	}
	
	@SuppressWarnings("unused")
	@Test(expected = UsuarioNaoLogadoException.class)
	public void executarOperacaoDepositarSemUsuarioLogado() {
		String depositar = caixa.depositar();
		fail("Operação não deveria ser executada sem nenhum usuário logado no caixa.");
	}
	
	@Test
	public void valorDoSaldoCorrespondente() {
		String numeroContaTeste = "123456";
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		contaCorrente.setSaldo(252.50);
		ctx.checking(new Expectations() {{
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar(numeroContaTeste));
		assertEquals("O saldo é R$ 252,50", caixa.saldo());
	}
	
	@Test
	public void operacaoDeSaqueComSucesso() {
		String numeroContaTeste = "123456";
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		contaCorrente.setSaldo(252.50);
		ctx.checking(new Expectations() {{
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar(numeroContaTeste));
		assertEquals("Retire seu dinheiro", caixa.sacar(50d));
		assertEquals("O saldo é R$ 202,50", caixa.saldo());
	}
	
}
