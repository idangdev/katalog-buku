package com.idangdev.catalog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.idangdev.catalog.dto.BookDetailResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Component // agar menjadikan LoggingAspect ini sebagai bean yang dikelola oleh SPRING CONTAINER
@Slf4j
@Aspect	// untuk menandakan bahwa ini kelas Aspect
public class LoggingAspect {

	/*
	 * CATATAN
	 - POINTCUT adalah Method mana yang ingin di intercept oleh Aspect yang anda buat
	 - pointcut desaineter EXECUTION ini berarti kita memerintahkan kepada framework untuk melakukan trigger 
	   saat terdapat eksekusi dari sebuah method yang nantinya kita deklarasikan didalam argumen poincut designator ini
	 - execution(*) -> * yang berarti method tersebut dapat memberikan return apapun
	 - Menjadikan method findBookDetail ini sebagai method yang mjd TRIGGER dari Aspect nya
	 - findBookDetail(..) -> .. berarti argumen nya bisa apa saja
	 - Jika ingin method findBookDetail secara khusus pada kelas BookResource
	 	com.idangdev.catalog.web.BookResource.findBooKDetail(..)
	 - Jika ingin semua method dalam kelas BookResource, maka
	 	com.idangdev.catalog.web.BookResource.*(..)
	 - Jika ingin semua kelas dalam package web juga sama
	    com.idangdev.catalog.web.*.*(..)
	 
	 - Setelah selesain menentukan POINTCUT expression nya, Sekarang tinggal menentukan ADVICE dari ASPECT tersebut
	 - ADVICE ini merujuk kepada kapan dari ASPECT yang anda buat akan di eksekusi
	 */
	@Pointcut("execution(* com.idangdev.catalog.web.*.*(..))")	// kalo argumen nya bisa apa aja bisa ditulis (..) jadi argumen apa saja, tapi kalo misal kriterianya harus ada argumen nya ya ditulis (com.dll). cara lainnya ya pake ARGS DESIGNATOR
	private void restAPI() {}	// argumen dan body nya kosong. karena disini saya hanya mendeklarasikan signature nya saja, dan untuk bodynya itu irelevan shg dpt kita kosongi saja
	
	@Pointcut("within(com.idangdev.catalog.web.*)")
	private void withinPointcutExample() {}
	
	@Pointcut("args(com.idangdev.catalog.dto.PublisherCreateRequestDTO)") // argumen PublisherCreateRequestDTO
	private void argsPointcutExample() {}
	
	@Pointcut("@args(com.idangdev.catalog.annotation.LogThisArg)")
	private void argsAnnotationPointcutExample() {}
	
	@Pointcut("@annotation(com.idangdev.catalog.annotation.LogThisMethod)")
	private void annotationPointcutExample() {}
	
	// ADVICE Before, saya ingin ASPECT ini di eksekusi sebelum sebuah method yg ada pada PointCut ini (line 39) di eksekusi
	// dan juga tambahkan signature nya (penanda)
	@Before("annotationPointcutExample()")
	public void beforeExecutedLogging() {
		log.info("this is log from aspect before method executed");
	}
	
	@After("annotationPointcutExample()")
	public void afterExecutedLogging() {
		log.info("this is log from aspect after method executed");
	}
	
	@AfterReturning("annotationPointcutExample()")
	public void afterReturnExecutedLogging() {
		log.info("this is log from aspect after returning method executed");
	}

	@AfterThrowing("annotationPointcutExample()")
	public void afterThrowingExecutedLogging() {
		log.info("this is log from aspect after throwing method executed");
	}
	
	@Around("restAPI()")
	public Object processingTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		
		try {
			log.info("********** start {}.{} **********", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());	// memanggil nama class dari join point nya & memanggil signature dari method join point nya
			stopWatch.start();
			return joinPoint.proceed(); // di antara start dan stop tambahkan ini untuk menyimbolkan method join pointnya
		}finally {
			stopWatch.stop();
			log.info("********** finish {}.{} execution time = {} **********", joinPoint.getTarget().getClass().getName(), 
					joinPoint.getSignature().getName(),
					stopWatch.getTotalTimeMillis());
		}
		

	}
	
	
}
