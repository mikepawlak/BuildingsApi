package com.msr.data;

import com.msr.model.SiteUse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SiteUseRepository extends JpaRepository<SiteUse, Integer> {
    @Query("SELECT su FROM SiteUse su WHERE su.siteId = :siteId")
    List<SiteUse> findAllBySiteId(@Param("siteId") int siteId);
}

