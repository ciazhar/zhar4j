package com.ciazhar.postgres.controller;

import com.ciazhar.postgres.model.ApiResponse;
import com.ciazhar.postgres.model.Role;
import com.ciazhar.postgres.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role/v1")
@RequiredArgsConstructor
@Validated
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRoles() {
        return ResponseEntity.ok().body(new ApiResponse("success", roleService.findAll(), roleService.count()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getRoleById(@PathVariable Integer id) {
        Role role = roleService.findById(id);
        return ResponseEntity.ok().body(new ApiResponse("success", role));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createRole(@RequestBody Role role) {
        Role createdRole = roleService.save(role);
        return ResponseEntity.ok().body(new ApiResponse("success", createdRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateRole(@PathVariable Integer id, @RequestBody Role role) {
        Role updatedRole = roleService.update(id, role);
        return ResponseEntity.ok().body(new ApiResponse("success", updatedRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRole(@PathVariable Integer id) {
        roleService.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse("success"));
    }
}
