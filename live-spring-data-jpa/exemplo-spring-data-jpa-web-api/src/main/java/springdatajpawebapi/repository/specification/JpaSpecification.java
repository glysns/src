package springdatajpawebapi.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class JpaSpecification<T> implements Specification<T> {

    private List<Condition> list;

    public JpaSpecification() {
        this.list = new ArrayList<>();
    }

    private void add(String field, Object value, Operator operator) {
        Condition criteria = new Condition(field, value, operator);
        list.add(criteria);
    }

    public void equal(String field, Object value) {
        this.add(field, value, Operator.EQUAL);
    }

    public void like(String field, Object value) {
        this.add(field, value, Operator.LIKE);
    }

    public void startWith(String field, Object value) {
        this.add(field, value, Operator.START_WITH);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
        for (Condition criteria : list) {
            if (criteria.operation.equals(Operator.EQUAL))
                predicates.add(builder.equal(root.get(criteria.field), criteria.value));
            else if (criteria.operation.equals(Operator.LIKE))
                predicates.add(builder.like(root.get(criteria.field), "%" + criteria.value.toString() + "%"));
            else if (criteria.operation.equals(Operator.START_WITH))
                predicates.add(builder.like(root.get(criteria.field),  criteria.value.toString() + "%"));

        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private  enum Operator{
        EQUAL,
        LIKE,
        START_WITH
    }
    class Condition {
        private String field;
        private Object value;
        private Operator operation;

        public Condition(String field, Object value, Operator operation) {
            this.field = field;
            this.value = value;
            this.operation = operation;
        }
    }

}
