package br.inpe.cap.projetoagil;

import java.text.NumberFormat;
import java.util.Locale;

public class CaixaEletronico {

	private static final NumberFormat SALDO_FORMAT = 
			NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	private ServicoRemoto servicoRemoto;
	private ContaCorrente contaCorrenteAtual;
	
	public String logar(String numeroConta) {
		this.contaCorrenteAtual = servicoRemoto.recuperarConta(numeroConta);
		if(this.contaCorrenteAtual != null) {
			return "Usuário Autenticado";
		} else {
			return "Usuário não encontrado";
		}
	}

	public String sacar() {
		if(this.contaCorrenteAtual != null) {
			return "Retire seu dinheiro";
		} else {
			throw new UsuarioNaoLogadoException();
		}
	}

	public String depositar() {
		if(this.contaCorrenteAtual != null) {
			return "Depósito recebido com sucesso";
		} else {
			throw new UsuarioNaoLogadoException();
		}
	}

	public String saldo() {
		if(this.contaCorrenteAtual != null) {
			double saldo = this.contaCorrenteAtual.getSaldo();
			return String.format("O saldo é %1s", SALDO_FORMAT.format(saldo));
		} else {
			throw new UsuarioNaoLogadoException();
		}
	}

	public void setServicoRemoto(ServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
	}

}
