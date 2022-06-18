package com.msr;

import com.msr.model.Site;
import com.msr.model.SiteUse;
import com.msr.services.SiteService;
import com.msr.services.SiteUseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Respond to site requests
 */
@RestController
@RequestMapping("/sites")
public class SitesController {

    /* Sample Output messages. */
    private final static String SAMPLE_RESPONSE_BASE = "This is a sample response to test if SitesController is responding appropriately. ";
    static final String SAMPLE_PARAM_PROVIDED = SAMPLE_RESPONSE_BASE + "The request param you passed was: ";
    static final String NO_SAMPLE_PARAM_PROVIDED = SAMPLE_RESPONSE_BASE + "No request param was provided.";
    static final String SAMPLE_EXCEPTION_MESSAGE = SAMPLE_RESPONSE_BASE + "An expected error was thrown.";

    private final SiteService siteService;

    private final SiteUseService siteUseService;

    public SitesController(SiteService siteService, SiteUseService siteUseService) {
        this.siteService = siteService;
        this.siteUseService = siteUseService;
    }


    @GetMapping(value = "", params = "siteId")
    public Site getSiteById(@ApiParam("Id of the site being requested") @RequestParam final int siteId) {
        Optional<Site> site = siteService.findById(siteId);

        if (!site.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "site not found");
        }

        return site.get();
    }

    @GetMapping("")
    public Iterable<Site> getAllSites() {
        return siteService.list();
    }

    @GetMapping("/uses")
    public Iterable<SiteUse> listUses() { return siteUseService.list(); }

    /**
     * Used simply to check if this controller is responding to requests.
     * Has no function other than echoing.
     *
     * @return A sample message based on the input parameters.
     * @throws RuntimeException Only when 'throwError' is true.
     */
    @ApiOperation("Returns a sample message for baseline controller testing.")
    @GetMapping("/sample")
    public String getSampleResponse(@ApiParam("The message that will be echoed back to the user.")
                                    @RequestParam(required = false) final String message,
                                    @ApiParam("Forces this endpoint to throw a generic error.")
                                    @RequestParam(required = false) final boolean throwError) {
        String response;
        if (throwError) {
            throw new RuntimeException(SAMPLE_EXCEPTION_MESSAGE);
        } else if (!StringUtils.hasLength(message)) {
            response = NO_SAMPLE_PARAM_PROVIDED;
        } else {
            response = SAMPLE_PARAM_PROVIDED + message;
        }
        return response;
    }
}