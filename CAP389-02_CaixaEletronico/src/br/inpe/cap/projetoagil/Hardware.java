package br.inpe.cap.projetoagil;

import br.inpe.cap.projetoagil.exception.HardwareMalfunctionException;

public interface Hardware {

	String pegarNumeroDaContaCartao() throws HardwareMalfunctionException;

	void entregarDinheiro() throws HardwareMalfunctionException;

	void lerEnvelope() throws HardwareMalfunctionException;

}
