package org.neoevolution.xor.mvc.controller;

import org.neoevolution.mvc.controller.NEConfigurationController;
import org.neoevolution.xor.mvc.model.XORConfiguration;
import org.neoevolution.xor.mvc.service.XORConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/xor/config")
public class XORConfigurationController extends NEConfigurationController<XORConfiguration, XORConfigurationService> {

    @Autowired
    protected XORConfigurationController(XORConfigurationService service) {
        super(service);
    }

}
