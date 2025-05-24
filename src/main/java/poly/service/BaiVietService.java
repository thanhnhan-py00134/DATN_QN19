package poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import poly.entity.BaiViet;
import poly.entity.DanhMuc;
import poly.repository.BaiVietRepository;
import poly.repository.DanhMucRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class BaiVietService {

    @Autowired
    private BaiVietRepository baiVietRepository;

    @Autowired
    private DanhMucRepository danhMucRepository;

    // Create a new post
    public BaiViet createBaiViet(BaiViet baiViet, Integer maDanhMuc, MultipartFile imageFile) throws IOException {
        // Validate and set DanhMuc
        if (maDanhMuc != null) {
            DanhMuc danhMuc = danhMucRepository.findById(maDanhMuc)
                    .orElseThrow(() -> new IllegalArgumentException("Danh mục không tồn tại"));
            baiViet.setDanhMuc(danhMuc);
        } else {
            throw new IllegalArgumentException("Danh mục là bắt buộc");
        }

        // Handle image upload
        if (imageFile != null && !imageFile.isEmpty()) {
            String base64Image = Base64.getEncoder().encodeToString(imageFile.getBytes());
            baiViet.setHinhAnh(base64Image);
        }

        // Set default values
        baiViet.setNgayTao(LocalDateTime.now());
        baiViet.setNgaySua(LocalDateTime.now());
        baiViet.setLuotXem(0);
        if (baiViet.getTrangThai() == null) {
            baiViet.setTrangThai("Nháp");
        }
        if (baiViet.getNgayDang() == null) {
            baiViet.setNgayDang(LocalDateTime.now());
        }

        return baiVietRepository.save(baiViet);
    }

    // Get all posts
    public List<BaiViet> getAllBaiViet() {
        return baiVietRepository.findAll();
    }

    // Get a post by ID
    public BaiViet getBaiVietById(Integer id) {
        return baiVietRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bài viết không tồn tại"));
    }

    // Update a post
    public BaiViet updateBaiViet(BaiViet baiViet, Integer maDanhMuc, MultipartFile imageFile) throws IOException {
        BaiViet existingBaiViet = baiVietRepository.findById(baiViet.getMaBaiViet())
                .orElseThrow(() -> new IllegalArgumentException("Bài viết không tồn tại"));

        // Update fields
        existingBaiViet.setTieuDe(baiViet.getTieuDe());
        existingBaiViet.setTomTat(baiViet.getTomTat());
        existingBaiViet.setNoiDung(baiViet.getNoiDung());
        existingBaiViet.setTrangThai(baiViet.getTrangThai());
        existingBaiViet.setNgaySua(LocalDateTime.now());

        // Update DanhMuc
        if (maDanhMuc != null) {
            DanhMuc danhMuc = danhMucRepository.findById(maDanhMuc)
                    .orElseThrow(() -> new IllegalArgumentException("Danh mục không tồn tại"));
            existingBaiViet.setDanhMuc(danhMuc);
        } else {
            throw new IllegalArgumentException("Danh mục là bắt buộc");
        }

        // Update image if provided
        if (imageFile != null && !imageFile.isEmpty()) {
            String base64Image = Base64.getEncoder().encodeToString(imageFile.getBytes());
            existingBaiViet.setHinhAnh(base64Image);
        }

        return baiVietRepository.save(existingBaiViet);
    }

    // Delete a post
    public void deleteBaiViet(Integer id) {
        if (!baiVietRepository.existsById(id)) {
            throw new IllegalArgumentException("Bài viết không tồn tại");
        }
        baiVietRepository.deleteById(id);
    }
}