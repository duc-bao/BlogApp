package baoduc.vn.blogapp.entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", orphanRemoval = true)
    private List<Post> posts;
}
