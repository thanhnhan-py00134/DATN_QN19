package poly.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.entity.NguonTin;
import poly.repository.NguonTinRepository;

@Controller
@RequestMapping("/admin/nguontin")
@RequiredArgsConstructor
public class NguonTinController {

    private final NguonTinRepository nguonTinRepo;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("nguonTin", new NguonTin());
        return "Admin/nguontin";
    }

    @PostMapping
    public String save(@ModelAttribute NguonTin nguonTin,
                       @RequestParam(name = "logoBase64", required = false) String logoBase64) {
        if (logoBase64 != null && !logoBase64.isBlank()) {
            nguonTin.setLogo(logoBase64);
        }
        nguonTinRepo.save(nguonTin);
        return "redirect:/Admin/nguontin";
    }
}
