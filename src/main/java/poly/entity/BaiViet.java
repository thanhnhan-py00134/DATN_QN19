package poly.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "BaiViet")
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maBaiViet;
    
    @Column(name = "TieuDe", nullable = false)
    private String tieuDe;
    
    @Column(name = "DuongDan", nullable = false, unique = true)
    private String duongDan;
    
    @Column(name = "TomTat", length = 500)
    private String tomTat;
    
    @Column(name = "NoiDung", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String noiDung;
    
    @Column(name = "HinhAnh")
    private String hinhAnh;
    
    @Column(name = "LuotXem")
    private Integer luotXem = 0;
    
    @Column(name = "TrangThai", length = 20)
    private String trangThai = "Nháp";
    
    @Column(name = "NgayDang")
    private LocalDateTime ngayDang;
    
    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();
    
    @Column(name = "NgaySua")
    private LocalDateTime ngaySua;
    
    @ManyToOne
    @JoinColumn(name = "MaDanhMuc")
    private DanhMuc danhMuc;
    
    @ManyToOne
    @JoinColumn(name = "MaNguonTin")
    private NguonTin nguonTin;
    
    @ManyToOne
    @JoinColumn(name = "MaTacGia")
    private NguoiDung tacGia;
    
    @ManyToMany
    @JoinTable(
        name = "BaiViet_TheTag",
        joinColumns = @JoinColumn(name = "MaBaiViet"),
        inverseJoinColumns = @JoinColumn(name = "MaTheTag")
    )
    private Set<TheTag> theTags;
}