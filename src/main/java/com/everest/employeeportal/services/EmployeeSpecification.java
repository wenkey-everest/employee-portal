package com.everest.employeeportal.services;

import com.everest.employeeportal.models.Employee;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class EmployeeSpecification {
    public static Specification<Employee> employeeSpecification(String name){

        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.or(criteriaBuilder.like(root.get("firstName"), "%"+name+"%"),
                        criteriaBuilder.like(root.get("lastName"),"%"+name+"%"));
            }
        };
    }
}
