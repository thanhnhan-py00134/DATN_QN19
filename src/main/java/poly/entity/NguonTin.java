package poly.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "NguonTin")
public class NguonTin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maNguonTin;
    
    @Column(name = "TenNguon", nullable = false, unique = true, length = 100)
    private String tenNguon;
    
    @Column(name = "Website")
    private String website;
    
    @Column(name = "Logo")
    private String logo;
    
    @Column(name = "MoTa", length = 500)
    private String moTa;
    
    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();
}