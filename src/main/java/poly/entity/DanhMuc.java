package poly.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "DanhMuc")
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maDanhMuc;
    
    @Column(name = "TenDanhMuc", nullable = false, unique = true, length = 100, columnDefinition = "nvarchar(100)")
    private String tenDanhMuc;
    
    @Column(name = "DuongDan", nullable = false, unique = true, length = 100, columnDefinition = "varchar(100)")
    private String duongDan;
    
    @Column(name = "MoTa", length = 500, columnDefinition = "nvarchar(500)")
    private String moTa;
    
    @ManyToOne
    @JoinColumn(name = "MaDanhMucCha")
    private DanhMuc danhMucCha;
    
    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();
}