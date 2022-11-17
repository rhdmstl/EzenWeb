package Com.EzenWeb.controller.test;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {
    //76p
    @DeleteMapping("/{variable}")
    public String DeleteVariable(@PathVariable String variable) {
        return variable;
    }
    //76-2
    @DeleteMapping("/requst1")
    public String DeleteVariable2(@RequestParam String variable) {
        return variable;
    }
}
