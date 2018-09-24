package com.forezp.servicehi;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceHiApplication {

    private static final Logger LOG = Logger.getLogger(ServiceHiApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run( ServiceHiApplication.class, args );
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    @Autowired
    private RestTemplate restTemplate ;

    @Bean
    @LoadBalanced//负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hi2")
    public String callHome(){
        LOG.log(Level.INFO, "calling trace service-hi  ");
        String str = "";
//        str = restTemplate.getForObject("http://localhost:8989/hi",String.class);
        str = restTemplate.getForObject("http://SERVICE-MIYA/hi",String.class);
        return str ;
    }

    @GetMapping("/info")
    public String info(){
        LOG.info("i an hi-service");
        return "i am hi-service";
    }

     @Bean
     public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }


}



