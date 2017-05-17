package com.wdm.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wdm.example.service.TestRemoteService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class CloudClientApplication {

    @Autowired  
    private LoadBalancerClient loadBalancer;
  
    @Autowired  
    private DiscoveryClient discovery;

    @Autowired
    private TestRemoteService testRemoteService;

    @RequestMapping("/discovery")
    public Object discovery() {
        System.out.println(loadBalancer.choose("tomcat"));
        return "discovery";
    }  

    @RequestMapping("/all")
    public Object all() {
        System.out.println(discovery.getServices());
        return "all";
    }

    @RequestMapping("/test")
    public String test() {
        return testRemoteService.testCloudApi();
    }

    public static void main(String[] args) {
        SpringApplication.run(CloudClientApplication.class, args);
    }
}
