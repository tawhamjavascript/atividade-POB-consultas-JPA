/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Aluguel;
import modelo.Carro;
import modelo.Cliente;

public class Listar {
	private EntityManager manager;

	public Listar() {
		manager = Util.conectarBanco();

		try {
			System.out.println("\n---listagem de carro:");
			TypedQuery<Carro> query1 = manager.createQuery("select c from Carro c", Carro.class); // order by p.nome
			List<Carro> resultados1 = query1.getResultList();
			for(Carro c: resultados1)
				System.out.println(c);

			System.out.println("\n---listagem de clientes:");
			TypedQuery<Cliente> query2 = manager.createQuery("select c from Cliente c", Cliente.class); // order by p.nome
			List<Cliente> resultados2 = query2.getResultList();
			for(Cliente c: resultados2)
				System.out.println(c);

			System.out.println("\n---listagem de alugueis:");
			TypedQuery<Aluguel> query3 = manager.createQuery("select a from Aluguel a", Aluguel.class); // order by p.nome
			List<Aluguel> resultados3 = query3.getResultList();
			for(Aluguel a: resultados3)
				System.out.println(a);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.fecharBanco();

		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}
