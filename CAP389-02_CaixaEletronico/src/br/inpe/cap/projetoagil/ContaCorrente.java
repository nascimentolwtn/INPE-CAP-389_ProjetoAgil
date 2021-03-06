package br.inpe.cap.projetoagil;

public class ContaCorrente {

	private String numeroConta;
	private double saldo;

	public ContaCorrente(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public void debitar(double valorSacado) {
		this.saldo = saldo - valorSacado;
	}

	public void creditar(double valorDepositado) {
		this.saldo = saldo + valorDepositado;
	}
	
}
