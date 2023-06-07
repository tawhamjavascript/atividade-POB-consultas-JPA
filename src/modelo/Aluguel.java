/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Aluguel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	//autogerado
	private String datainicio;
	private String datafim;
	private int dias;
	private double diaria;
	private double valor;
	private boolean finalizado=false;

	@ManyToOne(	
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.LAZY)
	private Carro carro;
	
	@ManyToOne(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.LAZY)
	private Cliente cliente;

	public Aluguel() {}

	public Aluguel(String datainicio, String datafim, double diaria) {
		super();
		this.datainicio = datainicio;
		this.datafim = datafim;
		this.diaria = diaria;
		
		LocalDate data1 = LocalDate.parse(datainicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate data2 = LocalDate.parse(datafim, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.dias = Period.between(data1, data2).getDays();
		this.valor = dias*diaria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDatainicio() {
		return datainicio;
	}

	public void setDatainicio(String datainicio) {
		this.datainicio = datainicio;
	}

	public String getDatafim() {
		return datafim;
	}

	public void setDatafim(String datafim) {
		this.datafim = datafim;
	}
	public double getDiaria() {
		return diaria;
	}

	public void setDiaria(double diaria) {
		this.diaria = diaria;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getDias() {
		return dias;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Carro getCarro() {
		return carro;
	}


	public void setCarro(Carro carro) {
		this.carro = carro;
		carro.setAlugado(true);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "id=" + id + ", "+ datainicio + " a " + datafim + ", valor=" + valor
				+ ", finalizado=" + finalizado + ", carro=" + carro.getPlaca() + ", cliente=" + cliente.getCpf();
	}

}
