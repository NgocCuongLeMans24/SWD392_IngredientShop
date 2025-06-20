package group2.swd392_onlineingredientsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private Integer roleId;

    @Column(nullable = false, length = 50)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<User> users;
} 