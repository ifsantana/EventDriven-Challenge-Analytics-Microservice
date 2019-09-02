package com.eventdrive.challenge.analytics.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;

@Controller
@Api(tags = "ClickEvent Resource", description = "ClickEvent Resource")
public class ClickEventResource {

    private static final String BASE_PATH = "/api/v1/";


//    @RequestMapping(value = BASE_PATH + "/click", method = GET)
//    public ResponseEntity<Iterable<Event>> getAll() {
//        return new ResponseEntity<Iterable<Event>>(this.service.getAll(), HttpStatus.OK);
//    }

}
