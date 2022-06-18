package com.msr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msr.model.Site;
import com.msr.model.SiteUse;
import com.msr.model.UseType;
import com.msr.services.SiteService;
import com.msr.services.SiteUseService;
import com.msr.services.UseTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class BuildingsApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BuildingsApiApplication.class, args);
	}

	private Logger logger = LoggerFactory.getLogger(BuildingsApiApplication.class);

	@Bean
	CommandLineRunner runner(SiteService siteService, UseTypeService useTypeService, SiteUseService siteUseService) {
		return args -> {
			ObjectMapper objectMapper = new ObjectMapper();

			TypeReference<List<Site>> sitesTypeReference = new TypeReference<List<Site>>(){};
			InputStream sitesInputStream = TypeReference.class.getResourceAsStream("/data/sites.json");
			try {
				List<Site> sites = objectMapper.readValue(sitesInputStream, sitesTypeReference);
				siteService.save(sites);
				logger.info("Sites saved...");
			}
			catch (IOException e) {
				logger.error("Unable to save sites: " + e.getMessage());
			}

			TypeReference<List<UseType>> useTypeTypeReference = new TypeReference<List<UseType>>(){};
			InputStream useTypeInputStream = TypeReference.class.getResourceAsStream("/data/use_types.json");
			try {
				List<UseType> useTypes = objectMapper.readValue(useTypeInputStream, useTypeTypeReference);
				useTypeService.save(useTypes);
				logger.info("Use type saved...");
			}
			catch (IOException e) {
				logger.error("Unable to save use types: " + e.getMessage());
			}

			TypeReference<List<SiteUse>> siteUseTypeReference = new TypeReference<List<SiteUse>>(){};
			InputStream siteUseInputStream = TypeReference.class.getResourceAsStream("/data/site_uses.json");
			try {
				List<SiteUse> siteUses = objectMapper.readValue(siteUseInputStream, siteUseTypeReference);
				siteUseService.save(siteUses);
				logger.info("Site uses saved...");
			}
			catch (IOException e) {
				logger.error("Unable to save site uses: " + e.getMessage());
			}
		};
	}
}
