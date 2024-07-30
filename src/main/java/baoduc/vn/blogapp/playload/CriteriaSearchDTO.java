package baoduc.vn.blogapp.playload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriteriaSearchDTO {
    private String key;
    private String oparation;
    private Object value;
}
