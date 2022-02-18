package br.org.recode.persistence;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional
public abstract class DaoAbstrato<Model, PK> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@SuppressWarnings("unchecked")
	private final Class<Model> entityClass = (Class<Model>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	public void create(Model model) {
		entityManager.persist(model);
	}
	
	public void update(Model model) {
		entityManager.merge(model);
	}
	
	public void delete(PK id) {
		entityManager.remove(entityManager.getReference(entityClass, id));
	}
	
	public Model findByPk(PK id) {
		return entityManager.find(entityClass, id);
	}
	
	public List<Model> findAll() {
		return entityManager
				.createQuery("from " + entityClass.getSimpleName(), entityClass)
				.getResultList();
	}
}