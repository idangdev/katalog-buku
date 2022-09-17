package com.idangdev.catalog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idangdev.catalog.dto.HelloMessageResponseDTO;
import com.idangdev.catalog.service.GreetingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class HelloResource {

	Logger log = LoggerFactory.getLogger(HelloResource.class);
	
	private GreetingService greetingService;
	
	//memberikan @Autowired itu juga boleh. tp opsional. karena Spring udah otomatis melakukan autowired sudah dilakukan secara implicit
    public HelloResource(GreetingService greetingService) {
		super();
		this.greetingService = greetingService;
	}
	
	
    // get adalah salah satu jenis dari http method / http verb
    // POST, PUT, DELETE, OPTION, TRACE, HEAD, PATCH
	@GetMapping("/hello")
    public ResponseEntity<HelloMessageResponseDTO> helloWorld() {	// request handler helloWorld(), // ResponseEntity digunakan untuk mengubah ResponseHeader yang kita kirim dari aplikasi kepada CLIENT (1xx, 2xx, 3xx, 4xx). ResponseEntity<Object yang ingin di kirim pada body response nya>
		log.trace("this is log TRACE");
		log.debug("this is log DEBUG");
		log.info("this is log INFO");
		log.warn("this is log WARN");
		log.error("this is log ERROR");
		HelloMessageResponseDTO dto = new HelloMessageResponseDTO();
		dto.setMessage(greetingService.sayGreeting());
        return ResponseEntity.accepted().body(dto);
    }

}
