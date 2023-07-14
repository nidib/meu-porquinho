package br.univille.meuporquinho.domains.contabancaria.repositorydtos;

public class SaldoContas {

	public double ganhos;
	public double gastos;

	public SaldoContas(double ganhos, double gastos) {
		this.ganhos = ganhos;
		this.gastos = gastos;
	}

	public SaldoContas() {}

}
