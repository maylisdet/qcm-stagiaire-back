package com.ai13qcm.services;

import com.ai13qcm.entities.Role;
import com.ai13qcm.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository repo;

    public RoleService(RoleRepository repo) {
        this.repo = repo;
    }
    public List<Role> findAll(){
        return repo.findAll();
    }
    public Role save(Role role){
        return repo.save(role);
    }
    public Role findById(Integer id){
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("No role with the id %d", id)));
    }
    public Role findByLabel(String label){
        return repo.findByLabel(label).orElseThrow(() -> new IllegalArgumentException(String.format("No role with the label %s", label)));

    }
    public void deleteById(Integer id){
        repo.deleteById(id);
    }
}
