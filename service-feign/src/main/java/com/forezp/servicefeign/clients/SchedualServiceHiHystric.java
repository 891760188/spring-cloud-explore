package com.forezp.servicefeign.clients;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        String str = "feign断路器接收到的参数为name="+name+new Date();
        return str;
    }
}
