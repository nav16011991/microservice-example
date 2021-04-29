package com.nagarro.nagp.userservice.service;

import com.nagarro.nagp.userservice.persistent.repository.ApplicationUserRepository;
import com.nagarro.nagp.userservice.service.mapper.ApplicationUserMapper;
import com.nagarro.nagp.userservice.service.model.ApplicationUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public List<ApplicationUserDTO> getAll() {
        return applicationUserRepository.findAll().stream().map(ApplicationUserMapper::toDTO).collect(Collectors.toList());
    }

    public ApplicationUserDTO get(String id) {
        return ApplicationUserMapper.toDTO(applicationUserRepository.findById(Long.parseLong(id)).orElse(null));
    }
}
