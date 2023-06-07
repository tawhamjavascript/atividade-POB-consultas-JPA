/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Aluguel;
import modelo.Carro;
import modelo.Cliente;

import java.util.List;

public class Consultar {
	protected EntityManager manager;

	public Consultar() {
		manager = Util.conectarBanco();
		
		try {
			System.out.println("consultas... \n");
			System.out.println("alugueis de carro palio");

			TypedQuery<Aluguel> q = manager.createQuery(
					"select a from Aluguel a where a.carro.modelo = 'palio'",
					Aluguel.class);
			List<Aluguel> resultados1 = q.getResultList();

				for(Aluguel a : resultados1)
					System.out.println(a);

			System.out.println("\nalugueis nao finalizados");
			TypedQuery<Aluguel> q2 = manager.createQuery(
					"select a from Aluguel a where a.finalizado = false",
					Aluguel.class);

			List<Aluguel> resultados2 = q2.getResultList();

			for(Aluguel a : resultados2)
				System.out.println(a);


			System.out.println("\ncarros que possuem 1 alugueis");
			TypedQuery<Carro> q3 = manager.createQuery(
					"select c from Carro c where size(c.alugueis) = 1",
					Carro.class);

			List<Carro> resultados4 = q3.getResultList();
			for(Carro c : resultados4)
				System.out.println(c);


			System.out.println("clientes que possuem 2 alugueis");
			TypedQuery<Cliente> q4 = manager.createQuery(
					"select c from Cliente c where size(c.alugueis) = 2",
					Cliente.class);
			List<Cliente> resultados5 = q4.getResultList();
			for (Cliente c : resultados5) {
				System.out.println(c);

			}



		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.fecharBanco();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}

