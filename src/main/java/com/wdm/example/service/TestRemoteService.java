package com.wdm.example.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("tomcat")
public interface TestRemoteService {

    @RequestMapping("test")
    String testCloudApi();
}
