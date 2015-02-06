package org.neoevolution.sample.soundfilter.mvc.controller;

import org.neoevolution.mvc.controller.NEConfigurationController;
import org.neoevolution.sample.soundfilter.mvc.model.SFConfiguration;
import org.neoevolution.sample.soundfilter.mvc.service.SFConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/soundfilter/config")
public class SFConfigurationController extends NEConfigurationController<SFConfiguration, SFConfigurationService> {

    @Autowired
    protected SFConfigurationController(SFConfigurationService service) {
        super(service);
    }

}
