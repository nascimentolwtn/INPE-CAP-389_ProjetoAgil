package br.inpe.cap.projetoagil;

public interface Hardware {

	String pegarNumeroDaContaCartao() throws HardwareMalfunctionException;

	void entregarDinheiro() throws HardwareMalfunctionException;

	void lerEnvelope() throws HardwareMalfunctionException;

}
