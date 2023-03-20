/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.truteq.ccpgw.frontend.adapter.json.api.controller;

import com.truteq.ccpgw.communication.server.logging.wrapper.LogWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */
@RestController
@RequestMapping("/ccpgwjsonadapter/")
public class Controller {
    
    private LogWrapper mLogger = new LogWrapper(Controller.class);
    /**
     * =========================================================================
     * getVersion
     * =========================================================================
     * @return
     * =========================================================================
     */
    @GetMapping("version")
    public String getVersion() {

        return "CCPGW Transaction Manager Restful Web service. PlatformPAC(c) 2021 version 1.0.0.";
    }
        
}
