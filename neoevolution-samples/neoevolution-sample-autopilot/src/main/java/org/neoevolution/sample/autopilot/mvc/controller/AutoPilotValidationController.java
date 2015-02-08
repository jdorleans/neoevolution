package org.neoevolution.sample.autopilot.mvc.controller;

import org.neoevolution.sample.autopilot.mvc.service.AutoPilotValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Controller
@RequestMapping("/autopilot/{id}/validate")
public class AutoPilotValidationController {

    @Autowired
    private AutoPilotValidationService service;

    @RequestMapping
    public void validate(@PathVariable Long id,
                     @RequestParam(required = false) Integer runs,
                     @RequestParam(required = false) Integer maxFitness) {
        service.validate(id, runs, maxFitness);
    }

}
