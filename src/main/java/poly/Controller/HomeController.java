package poly.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.service.DanhMucService;

@Controller
public class HomeController {
   
    
    @Autowired
    private DanhMucService danhMucService;
    
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("danhmucs", danhMucService.getAllDanhMuc());
        return "index";
    }
}