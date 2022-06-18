package com.msr.services;

import com.msr.data.UseTypeRepository;
import com.msr.model.UseType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseTypeService {
    private final UseTypeRepository useTypeRepository;

    public UseTypeService(UseTypeRepository useTypeRepository) {
        this.useTypeRepository = useTypeRepository;
    }

    public Iterable<UseType> list() {
        return useTypeRepository.findAll();
    }

    public Iterable<UseType> save(List<UseType> useType) {
        return useTypeRepository.saveAll(useType);
    }
}
