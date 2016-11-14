package br.inpe.cap.projetoagil;

public class CaixaEletronico {

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
		return "Retire seu dinheiro";
	}

	public String depositar() {
		return "Depósito recebido com sucesso";
	}

	public String saldo() {
		return "O saldo é R$xx,xx";
	}

	public void setServicoRemoto(ServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
	}

}
