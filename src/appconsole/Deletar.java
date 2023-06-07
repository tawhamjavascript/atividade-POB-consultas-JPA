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
import modelo.Cliente;

public class Deletar {
	protected EntityManager manager;

	public Deletar() {
		manager = Util.conectarBanco();

		try {
			System.out.println("excluindo carro");

			manager.getTransaction().begin();
			TypedQuery<Carro> q = manager.createQuery(
					"select p from Carro p where p.placa = 'BBB2000' ", Carro.class);
			Carro car = q.getSingleResult();

			// antes de apagar o carro,remover o cliente relacionado a cada 
			// aluguel do carro e apagar cada aluguel do carro
			for (Aluguel a : car.getAlugueis()) {
				Cliente cli = a.getCliente();
				cli.remover(a);
				manager.merge(cli); //aluguel orfao sera apagado
				manager.remove(a);  
			}

			// apagar o carro
			manager.remove(car);
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
		new Deletar();
	}
}
