package me.twis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kalizsolt on 10/01/16.
 */
@Controller
public class TvSeriesDbController {

    @RequestMapping("/welcome/{searchPattern}")
    @ResponseBody
    public String hello(@PathVariable String searchPattern) {
        return searchPattern;
    }
}
