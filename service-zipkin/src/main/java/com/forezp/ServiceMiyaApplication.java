package com.forezp;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class ServiceMiyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceMiyaApplication.class, args);
	}

	private static final Logger LOG = Logger.getLogger(ServiceMiyaApplication.class.getName());


	@RequestMapping("/hi")
	public String home(){
		LOG.log(Level.INFO, "hi is being called");
		return "hi i'm miya!";
	}

	@RequestMapping("/miya")
	public String info(){
		LOG.log(Level.INFO, "info is being called");
        String str = restTemplate.getForObject("http://SERVICE-HI/info",String.class);
		return str;
	}

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	@LoadBalanced//负载均衡
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
