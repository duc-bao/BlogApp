package baoduc.vn.blogapp.playload;

import java.util.function.Consumer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerPost implements Consumer<CriteriaSearchDTO> {
    private CriteriaBuilder criteriaBuilder;
    private Root root;
    private Predicate predicate;

    @Override
    public void accept(CriteriaSearchDTO criteriaSearchDTO) {
        if (criteriaSearchDTO.getOparation().equals(">")) {
            predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get(criteriaSearchDTO.getKey()),
                            criteriaSearchDTO.getValue().toString()));
        } else if (criteriaSearchDTO.getOparation().equals("<")) {
            predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get(criteriaSearchDTO.getKey()),
                            criteriaSearchDTO.getValue().toString()));
        } else {
            if (root.get(criteriaSearchDTO.getKey()).getJavaType() == String.class) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(
                                root.get(criteriaSearchDTO.getKey()),
                                "%" + criteriaSearchDTO.getValue().toString() + "%"));
            } else {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(
                                root.get(criteriaSearchDTO.getKey()),
                                criteriaSearchDTO.getValue().toString()));
            }
        }
    }
}
