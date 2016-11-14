package br.inpe.cap.projetoagil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

public class TestCaixaEletronico {
	
	private static final String numeroContaTeste = "123456";
	private CaixaEletronico caixa;
	private ServicoRemoto servicoRemotoMock;
	private Hardware hardwareMock;
	
	@Rule
	public JUnitRuleMockery ctx = new JUnitRuleMockery();
	
	@Before
	public void setUp() {
		caixa = new CaixaEletronico();
		servicoRemotoMock = ctx.mock(ServicoRemoto.class);
		caixa.setServicoRemoto(servicoRemotoMock);
		hardwareMock = ctx.mock(Hardware.class);
		caixa.setHardware(hardwareMock);
	}
	
	@Test
	public void exibicaoMensagensCaixaEletronico() throws HardwareMalfunctionException {
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		ctx.checking(new Expectations() {{
			oneOf(hardwareMock).pegarNumeroDaContaCartao();
			will(returnValue(numeroContaTeste));
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		
		assertEquals("Usuário Autenticado", caixa.logar());
		assertEquals("Retire seu dinheiro", caixa.sacar(0));
		assertEquals("Depósito recebido com sucesso", caixa.depositar(0));
		assertEquals("O saldo é R$ 0,00", caixa.saldo());
	}
	
	@Test
	public void recuperarConta() throws HardwareMalfunctionException {
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		ctx.checking(new Expectations() {{
			oneOf(hardwareMock).pegarNumeroDaContaCartao();
			will(returnValue(numeroContaTeste));
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		
		assertEquals("Usuário Autenticado", caixa.logar());
	}
	
	@Test
	public void logarUsuarioNaoIdentificado() throws HardwareMalfunctionException {
		String numeroContaInvalido = "123";
		ctx.checking(new Expectations() {{
			oneOf(hardwareMock).pegarNumeroDaContaCartao();
			will(returnValue(numeroContaInvalido));
			oneOf(servicoRemotoMock).recuperarConta(numeroContaInvalido);
			will(returnValue(null));
		}});
		
		assertEquals("Não foi possível autenticar o usuário", caixa.logar());
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
		String depositar = caixa.depositar(0);
		fail("Operação não deveria ser executada sem nenhum usuário logado no caixa.");
	}
	
	@Test
	public void valorDoSaldoCorrespondente() throws HardwareMalfunctionException {
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		contaCorrente.setSaldo(252.50);
		ctx.checking(new Expectations() {{
			oneOf(hardwareMock).pegarNumeroDaContaCartao();
			will(returnValue(numeroContaTeste));
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar());
		assertEquals("O saldo é R$ 252,50", caixa.saldo());
	}
	
	@Test
	public void operacaoDeSaqueComSucesso() throws HardwareMalfunctionException {
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		contaCorrente.setSaldo(252.50);
		ctx.checking(new Expectations() {{
			oneOf(hardwareMock).pegarNumeroDaContaCartao();
			will(returnValue(numeroContaTeste));
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar());
		assertEquals("Retire seu dinheiro", caixa.sacar(50));
		assertEquals("O saldo é R$ 202,50", caixa.saldo());
	}
	
	@Test
	public void operacaoDeSaqueComSaldoInsuficiente() throws HardwareMalfunctionException {
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		contaCorrente.setSaldo(252.50);
		ctx.checking(new Expectations() {{
			oneOf(hardwareMock).pegarNumeroDaContaCartao();
			will(returnValue(numeroContaTeste));
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar());
		assertEquals("Saldo insuficiente", caixa.sacar(350));
		assertEquals("O saldo é R$ 252,50", caixa.saldo());
	}
	
	@Test
	public void operacaoDeDeposito() throws HardwareMalfunctionException {
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		contaCorrente.setSaldo(252.50);
		ctx.checking(new Expectations() {{
			oneOf(hardwareMock).pegarNumeroDaContaCartao();
			will(returnValue(numeroContaTeste));
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar());
		assertEquals("Depósito recebido com sucesso", caixa.depositar(200));
		assertEquals("O saldo é R$ 452,50", caixa.saldo());
	}
	
	@Ignore
	@Test(expected=HardwareMalfunctionException.class)
	public void tentarEfetuarLoginComFalhaDeHardware() throws HardwareMalfunctionException {
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		ctx.checking(new Expectations() {{
			oneOf(hardwareMock).pegarNumeroDaContaCartao();
			will(returnValue(numeroContaTeste));
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar());
	}
	
	@Ignore
	@Test(expected=HardwareMalfunctionException.class)
	public void operacaoDeSaqueComFalhaDeHardware() throws HardwareMalfunctionException {
		fail("Implementar!");
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		contaCorrente.setSaldo(252.50);
		ctx.checking(new Expectations() {{
			oneOf(hardwareMock).pegarNumeroDaContaCartao();
			will(returnValue(numeroContaTeste));
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar());
		assertEquals("Retire seu dinheiro", caixa.sacar(50));
		assertEquals("O saldo é R$ 202,50", caixa.saldo());
	}
	
	@Ignore
	@Test(expected=HardwareMalfunctionException.class)
	public void operacaoDeDepositoComFalhaDeHardware() throws HardwareMalfunctionException {
		fail("Implementar!");
		ContaCorrente contaCorrente = new ContaCorrente(numeroContaTeste);
		contaCorrente.setSaldo(252.50);
		ctx.checking(new Expectations() {{
			oneOf(hardwareMock).pegarNumeroDaContaCartao();
			will(returnValue(numeroContaTeste));
			oneOf(servicoRemotoMock).recuperarConta(numeroContaTeste);
			will(returnValue(contaCorrente));
		}});
		assertEquals("Usuário Autenticado", caixa.logar());
		assertEquals("Depósito recebido com sucesso", caixa.depositar(200));
		assertEquals("O saldo é R$ 452,50", caixa.saldo());
	}
	
}
