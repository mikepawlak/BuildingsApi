package com.msr;

import com.msr.services.SiteService;
import com.msr.services.SiteUseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Intended as a starting point for unit testing SitesController
 */
class SitesControllerTest {
    @Mock
    private SiteService siteService;

    @Mock
    private SiteUseService siteUseService;

    @InjectMocks
    private final SitesController sitesController = new SitesController(siteService, siteUseService);

    @Test
    void testSampleResponse_NullMessageParameter() {
        String response = sitesController.getSampleResponse(null, false);
        assertEquals(SitesController.NO_SAMPLE_PARAM_PROVIDED, response);
    }

    @Test
    void testSampleResponse_EmptyMessageParameter() {
        String response = sitesController.getSampleResponse("", false);
        assertEquals(SitesController.NO_SAMPLE_PARAM_PROVIDED, response);
    }

    @Test
    void testSampleResponse_MessageParameterProvided() {
        String expectedString = "This is the expected output parameter.";
        String response = sitesController.getSampleResponse(expectedString, false);
        assertEquals(SitesController.SAMPLE_PARAM_PROVIDED + expectedString, response);
    }

    @Test
    void testSampleResponse_ThrowsWhenThrowErrorIsTrue() {
        assertThrows(RuntimeException.class, () -> sitesController.getSampleResponse(null, true));
    }

    /**
     * Intended to test the controller's get all sites functionality.
     */
    @Test
    void testGetAllSites() {
        // TODO: Flesh out test based on implementation
        // List<Site> sites = buildingsController.getAllSites();
        // assertEquals(expectedListSize, sites.getLength());
    }

    /**
     * Intended to test the controller's get site by ID functionality.
     */
    @Test
    void testGetSiteById() {
        // TODO: Flesh out test based on implementation
        // Site site = buildingsController.getSite(siteId);
        // assertEquals(expectedSite, site);
    }
}