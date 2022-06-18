package com.msr.data;

import com.msr.model.Site;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * A sample JPA repository for querying and storing sites
 */
@Repository
public interface SiteRepository extends PagingAndSortingRepository<Site, Integer> {
}