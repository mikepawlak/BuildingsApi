package com.msr.services;

import com.msr.data.SiteRepository;
import com.msr.data.SiteUseRepository;
import com.msr.data.UseTypeRepository;
import com.msr.model.Site;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SiteService {
    private final SiteRepository siteRepository;
    private final SiteUseRepository siteUseRepository;
    private final UseTypeRepository useTypeRepository;

    public SiteService(SiteRepository siteRepository,
                       SiteUseRepository siteUseRepository,
                       UseTypeRepository useTypeRepository) {
        this.siteRepository = siteRepository;
        this.siteUseRepository = siteUseRepository;
        this.useTypeRepository = useTypeRepository;
    }

    public Iterable<Site> list() {
        Iterable<Site> sites =  siteRepository.findAll();
        for(Site site: sites) {
            site = hydrateSite(site);
        }
        return sites;
    }

    public Optional<Site> findById(int siteId) {
        Optional<Site> site = siteRepository.findById(siteId);
        site.ifPresent(this::hydrateSite);

        return site;
    }

    public Iterable<Site> save(List<Site> site) {
        return siteRepository.saveAll(site);
    }

    private Site hydrateSite(Site site) {

        site.setSiteUses(siteUseRepository.findAllBySiteId(site.getId()));
        site.setTotalSize(site.getSiteUses().stream().mapToInt(siteUse -> (int) siteUse.getSizeSqft()).sum());

        //this fun little thing - TODO more than one primary use type
        Map<Integer, Long> usesAndSizes =  new HashMap<Integer, Long>();
        site.getSiteUses().stream().forEach(siteUse -> {
            if (usesAndSizes.containsKey(siteUse.getUseTypeId())) {
                usesAndSizes.put(siteUse.getUseTypeId(), usesAndSizes.get(siteUse.getUseTypeId()) + siteUse.getSizeSqft());
            } else {
                usesAndSizes.put(siteUse.getUseTypeId(), siteUse.getSizeSqft());
            }
        });

        int primaryUseId = Collections.max(usesAndSizes.entrySet(), Map.Entry.comparingByValue()).getKey();
        site.setPrimaryType(useTypeRepository.findById(primaryUseId).get());

        return site;
    }
}
