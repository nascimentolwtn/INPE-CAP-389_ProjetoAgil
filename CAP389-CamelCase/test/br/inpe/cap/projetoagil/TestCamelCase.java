package br.inpe.cap.projetoagil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

public class TestCamelCase {

	@Test
	public void palavraUnica() {
		List<String> palavras = CamelCase.converterCamelCase("nome");
		String resultado = palavras.get(0);
		assertEquals("nome", resultado);
	}
	
	@Test
	public void primeiraLetraMaiuscula() {
		List<String> palavras = CamelCase.converterCamelCase("Nome");
		String resultado = palavras.get(0);
		assertEquals("nome", resultado);
	}
	
	@Test
	public void nomeCompostoPrimeiraLetraMinuscula() {
		List<String> palavras = CamelCase.converterCamelCase("nomeComposto");
		String resultado1 = palavras.get(0);
		String resultado2 = palavras.get(1);
		assertEquals("nome", resultado1);
		assertEquals("composto", resultado2);
	}
	
	@Test
	public void nomeCompostoPrimeiraLetraMaiuscula() {
		List<String> palavras = CamelCase.converterCamelCase("NomeComposto");
		String resultado1 = palavras.get(0);
		String resultado2 = palavras.get(1);
		assertEquals("nome", resultado1);
		assertEquals("composto", resultado2);
	}
	
	@Test
	public void nomeCompostoSeparadoPorSublinhado() {
		List<String> palavras = 
				CamelCase.converterCamelCase("Nome_Separado_Por_Sublinhado");
		String resultado1 = palavras.get(0);
		String resultado2 = palavras.get(1);
		String resultado3 = palavras.get(2);
		String resultado4 = palavras.get(3);
		assertEquals("nome", resultado1);
		assertEquals("separado", resultado2);
		assertEquals("por", resultado3);
		assertEquals("sublinhado", resultado4);
	}
	
	@Test
	public void sigla() {
		List<String> palavras = CamelCase.converterCamelCase("CPF");
		String resultado1 = palavras.get(0);
		assertEquals("CPF", resultado1);
	}
	
	@Test
	public void palavraESigla() {
		List<String> palavras = CamelCase.converterCamelCase("númeroCPF");
		String resultado1 = palavras.get(0);
		String resultado2 = palavras.get(1);
		assertEquals("número", resultado1);
		assertEquals("CPF", resultado2);
	}
	
	@Test
	public void adicionadaPalavraAposASigla() {
		List<String> palavras = CamelCase.converterCamelCase("númeroCPFContribuinte");
		String resultado1 = palavras.get(0);
		String resultado2 = palavras.get(1);
		String resultado3 = palavras.get(2);
		assertEquals("número", resultado1);
		assertEquals("CPF", resultado2);
		assertEquals("contribuinte", resultado3);
	}
	
	@Test
	public void palavrasComNumeroIntercalado() {
		List<String> palavras = CamelCase.converterCamelCase("recupera10Primeiros");
		String resultado1 = palavras.get(0);
		String resultado2 = palavras.get(1);
		String resultado3 = palavras.get(2);
		assertEquals("recupera", resultado1);
		assertEquals("10", resultado2);
		assertEquals("primeiros", resultado3);
	}
	
	@Test
	public void apenasLetraE() {
		List<String> palavras = CamelCase.converterCamelCase("E");
		String resultado1 = palavras.get(0);
		assertEquals("e", resultado1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void palavraInvalidaComecandoComNumero() {
		CamelCase.converterCamelCase("10Primeiros");
		fail("Palavra não deveria começar com números.");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void palavraInvalidaContendoCaracteresEspeciais() {
		CamelCase.converterCamelCase("nome#Composto");
		fail("Caracteres especiais não deveriam ser permitidos.");
	}
	
}
