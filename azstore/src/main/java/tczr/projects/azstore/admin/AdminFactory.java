package tczr.projects.azstore.admin;

import org.springframework.beans.factory.annotation.Autowired;
// this class for map and collect the two objects together
public class AdminFactory {
    @Autowired
    private final AdminService adminService;

    @Autowired
    private final AdminTypeService adminTypeService;

    public AdminFactory(AdminService adminService, AdminTypeService adminTypeService){
        this.adminService=adminService;
        this.adminTypeService=adminTypeService;
    }


}
