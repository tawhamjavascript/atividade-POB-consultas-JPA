/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import jakarta.persistence.EntityManager;

/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import modelo.Aluguel;
import modelo.Carro;
import modelo.Cliente;

public class Cadastrar {
	protected EntityManager manager;

	public Cadastrar() {
		try {
			manager = Util.conectarBanco();

			manager.getTransaction().begin();
			System.out.println("cadastrando...");
			Carro carro1 = new Carro("AAA1000", "palio");
			manager.persist(carro1);
			manager.getTransaction().commit();
			
			manager.getTransaction().begin();
			Carro carro2 = new Carro("BBB2000", "onix");
			manager.persist(carro2);
			manager.getTransaction().commit();
			
			manager.getTransaction().begin();
			Carro carro3 = new Carro("CCC3000", "civic");
			manager.persist(carro3);
			manager.getTransaction().commit();

			manager.getTransaction().begin();
			Cliente cli1 = new Cliente("joao", "1111");
			manager.persist(cli1);
			manager.getTransaction().commit();
			
			manager.getTransaction().begin();
			Cliente cli2 = new Cliente("maria", "2222");
			manager.persist(cli2);
			manager.getTransaction().commit();
			
			manager.getTransaction().begin();
			Cliente cli3 = new Cliente("jose", "3333");
			manager.persist(cli3);
			manager.getTransaction().commit();
			
			manager.getTransaction().begin();
			Aluguel a1 = new Aluguel("01/05/2022", "10/05/2022",100.0);
			a1.setCarro(carro1);
			a1.setCliente(cli1);
			carro1.adicionar(a1);
			cli1.adicionar(a1);
			manager.persist(a1);
			manager.getTransaction().commit();
			
			manager.getTransaction().begin();
			Aluguel a2 = new Aluguel("01/05/2022", "10/05/2022", 200.0);
			a2.setCarro(carro2);
			a2.setCliente(cli2);
			carro2.adicionar(a2);
			cli2.adicionar(a2);
			manager.persist(a2);
			manager.getTransaction().commit();
			
			manager.getTransaction().begin();
			Aluguel a3 = new Aluguel("01/05/2022", "10/05/2022", 300.0);
			a3.setCarro(carro3);
			a3.setCliente(cli2);
			carro3.adicionar(a3);
			cli2.adicionar(a3);
			manager.persist(a3);
			manager.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.fecharBanco();
		System.out.println("\nfim do programa !");
	}


	public static void main(String[] args) {
		new Cadastrar();
	}
}
