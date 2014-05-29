package com.ags.jonlive.service.impl;

import com.ags.jonlive.service.HelloService;

/**
 * @author Angel
 * @since 03/05/2014
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void saySomething(String something) {
        System.out.println(something);
    }
}
