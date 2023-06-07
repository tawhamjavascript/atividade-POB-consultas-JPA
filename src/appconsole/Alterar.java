/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import modelo.Aluguel;
import modelo.Carro;

public class Alterar {
	protected EntityManager manager;

	public Alterar() {
		manager = Util.conectarBanco();
		try {
			System.out.println("devolvendo carro alugado");
			manager.getTransaction().begin();
			TypedQuery<Carro> q = manager.createQuery(
					"select p from Carro p where p.placa = 'AAA1000' ", Carro.class);
			Carro car = q.getSingleResult();

			if(!car.isAlugado())
				throw new Exception("exceção - carro nao esta alugado");
			
			//alterar a situação do carro 
			car.setAlugado(false);

			// obter o ultimo aluguel do carro
			Aluguel alug = car.getAlugueis().get( car.getAlugueis().size()-1 );

			//alterar a situação do alugel 
			alug.setFinalizado(true);

			//atualizar carro no banco
			manager.merge(car);
			manager.getTransaction().commit();
		}
		catch (NoResultException e) {
			manager.getTransaction().rollback();
			System.out.println("nao encontrou");
		}
		catch (NonUniqueResultException e) {
			manager.getTransaction().rollback();
			System.out.println("encontrou placa repetida ");
		}
		catch (Exception e) {
			manager.getTransaction().rollback();
			System.out.println(e.getMessage());
		}


		Util.fecharBanco();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Alterar();
	}
}
