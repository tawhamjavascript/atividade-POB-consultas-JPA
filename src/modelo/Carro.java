/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Carro {
	@Id
	private String placa;		//chave primaria
	private String modelo;
	private boolean alugado=false;
	
	@OneToMany(
			mappedBy = "carro", 
			cascade= {CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.LAZY
			)
	private List <Aluguel> alugueis = new ArrayList<>();

	public Carro(String placa, String modelo) {
		this.placa = placa;
		this.modelo = modelo;
	}
	public Carro() {}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public boolean isAlugado() {
		return alugado;
	}

	public void setAlugado(boolean alugado) {
		this.alugado = alugado;
	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}


	public void adicionar(Aluguel a){
		alugueis.add(a);
	}
	public void remover(Aluguel a){
		alugueis.remove(a);
	}

	@Override
	public String toString() {
		String texto = "placa=" + placa + ", modelo=" + modelo + ", alugado=" + alugado ;
		
		for(Aluguel a : alugueis)
			texto+= "\n    aluguel: " + a ;
		
		return texto;
	}


}
