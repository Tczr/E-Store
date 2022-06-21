package tczr.projects.azstore.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tczr.projects.azstore.admin.model.Admin;
import tczr.projects.azstore.admin.model.AdminType;

import javax.websocket.server.PathParam;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("admin/api/v1")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/get/{obj}")
    public ResponseEntity<Admin> getAdmin(@PathVariable(name = "obj") Object object)
    {
        Optional<Admin> admin = (Optional<Admin>) adminService.getBy(object);
        return admin.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(admin.get(),HttpStatus.OK);
    }


    @PostMapping("/insert")
    public ResponseEntity<Object> insertAdmin(@RequestBody Admin user, @RequestParam(name = "email") String adminEmail)
    {
        Optional<Admin> admin =adminService.getByEmail(adminEmail);
        if(!admin.isEmpty()&& admin.get().getAdminType().getType().equalsIgnoreCase("all privileges")){
            return new ResponseEntity<>("Not allowed to add new admin",HttpStatus.BAD_REQUEST);
        }
            return adminService.insert(user) ?
                      new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
