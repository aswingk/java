package com.agk.jdbcsqlserver.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Predicates;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpRepoCustomImp implements EmpRepoCustom{

//     @PersistenceContext
    private EntityManager em;

    @Override
    public List<Employee> namebyage(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);

//         cq.select(employee);

        Predicate namePredicate = cb.equal(employee.get("name"), name);
        Predicate agePredicate = cb.equal(employee.get("age"), 32);
        
        cq.where(namePredicate, agePredicate);
        TypedQuery<Employee> query = em.createQuery(cq);
        return query.getResultList();
    }
}
