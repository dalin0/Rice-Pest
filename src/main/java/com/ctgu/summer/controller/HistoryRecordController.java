package com.ctgu.summer.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ctgu
 * @since 2021-07-15
 */
@RestController
@RequestMapping("/summer/historyRecord")
public class HistoryRecordController {

    public static void main(String[] args) {
        String s = null;
        String ss = new String("0");
        if(StringUtils.equals(ss, "0")){
            s = "caonima";
        }
        System.out.println(s);
    }
}

