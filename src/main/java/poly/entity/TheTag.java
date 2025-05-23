package poly.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "TheTag")
public class TheTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maTheTag;
    
    @Column(name = "TenTheTag", nullable = false, unique = true, length = 50)
    private String tenTheTag;
    
    @Column(name = "DuongDan", nullable = false, unique = true, length = 50)
    private String duongDan;
    
    @ManyToMany(mappedBy = "theTags")
    private Set<BaiViet> baiViets;
}