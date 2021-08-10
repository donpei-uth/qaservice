package edu.uth.deidentifier;

import com.alibaba.fastjson.util.TypeUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication


public class ArxApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ArxApplication.class);
	

    public ArxApplication() {
        TypeUtils.compatibleWithFieldName = true;
    }

    public static void main(String[] args) {
   
        SpringApplication.run(ArxApplication.class, args);
    }

}

