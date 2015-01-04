package org.neoevolution.xor;

import org.neoevolution.mvc.controller.NEConfigurationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 03 2015
 */
@RestController
@RequestMapping("/xor/config")
public class XORConfigurationController extends NEConfigurationController<XORConfiguration, XORConfigurationService> {

    @Autowired
    protected XORConfigurationController(XORConfigurationService service) {
        super(service);
    }

}
