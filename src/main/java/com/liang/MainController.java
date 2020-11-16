package com.liang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/11/16 19:21
 * @content
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/level1/{i}")
    public String l1(@PathVariable("i")String i){
        return i;
    }
    @RequestMapping("/level2/{i}")
    public String l2(@PathVariable("i")String i){
        return i;
    }
    @RequestMapping("/level3/{i}")
    public String l3(@PathVariable("i")String i){
        return i;
    }
}
