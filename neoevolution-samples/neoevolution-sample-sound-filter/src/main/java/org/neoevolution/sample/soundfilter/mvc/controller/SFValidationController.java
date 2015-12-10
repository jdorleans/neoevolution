package org.neoevolution.sample.soundfilter.mvc.controller;

import org.neoevolution.sample.soundfilter.mvc.service.SFValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/soundfilter/{id}/validate")
public class SFValidationController {

    @Autowired
    private SFValidationService service;


    public void validate(@PathVariable Long id) {
        service.validate(id);
    }

}
