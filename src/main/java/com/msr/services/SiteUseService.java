package com.msr.services;

import com.msr.data.SiteUseRepository;
import com.msr.data.UseTypeRepository;
import com.msr.model.SiteUse;
import com.msr.model.UseType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SiteUseService {
    private final SiteUseRepository siteUseRepository;
    private final UseTypeRepository useTypeRepository;

    public SiteUseService(SiteUseRepository siteUseRepository, UseTypeRepository useTypeRepository) {
        this.siteUseRepository = siteUseRepository;
        this.useTypeRepository = useTypeRepository;
    }

    public Iterable<SiteUse> list() {
        Iterable<SiteUse> uses = siteUseRepository.findAll();

        for(SiteUse use: uses) {
            UseType useType = useTypeRepository.findById(use.getUseTypeId()).get();
            use.setUseType(useType);
        }

        return uses;
    }

    public Iterable<SiteUse> save(List<SiteUse> siteUses) {
        return siteUseRepository.saveAll(siteUses);
    }
}
