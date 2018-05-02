package util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Contacto;

public class Conexion<T> {
	
	public static EntityManager em = null;
	
	public Conexion() {
		em = this.getEm();
	}
	
	public static EntityManager getEm(){
		if ( em == null ) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajemodel");
            em = emf.createEntityManager();
        }
		return em;
	}
	
	public <E> T find(Class<T> c, E id){
		T object = (T) em.find(c, id);
		return object;
	}
	
	public List<T> list(Class<T> c){
		TypedQuery<T> consulta= em.createNamedQuery(c.getSimpleName()+".findAll", c);
		List<T> lista = (List<T>) consulta.getResultList();
		return lista;
	}
	
	
	public void insert(T o){
		try {
			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//em.close();
		}
		
	}
	
	public void update(T o){
		try {
			em.getTransaction().begin();
			em.merge(o);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//em.close();
		}
		
	}
	
	public void delete(T o){
		try {
			em.getTransaction().begin();
			em.remove(o);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//em.close();
		}
		
	}
}