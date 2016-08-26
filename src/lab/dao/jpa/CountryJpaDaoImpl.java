package lab.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transaction;

import org.springframework.stereotype.Repository;

import lab.dao.CountryDao;
import lab.model.Country;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

	@Override
	public void save(Country country) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(country);
        entityManager.getTransaction().commit();
        entityManager.close();
	}

	@Override
	public List<Country> getAllCountries() {
        List<Country> countryList = null;
        EntityManager em = emf.createEntityManager();
        if (em != null) {
            countryList =
                    em.createQuery("from Country", Country.class)
                            .getResultList();
            em.close();
        }
        return countryList;
	}

	@Override
	public Country getCountryByName(String name) {
        EntityManager em = emf.createEntityManager();
        Country country = null;
        if (em != null) {
            country = em
                    .createQuery("SELECT c FROM Country c WHERE c.name LIKE :name",
                            Country.class).setParameter("name", name)
                    .getSingleResult();
            em.close();
        }
        return country;

    }

}
