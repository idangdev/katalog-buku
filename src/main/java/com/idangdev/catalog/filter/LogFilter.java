package com.idangdev.catalog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Hello from filter {}", request.getLocalAddr());	// getLocalAddr() digunakan untuk mencetak alamat dari Incoming Request nya Jadi request nya datang dari mana disini kita akan cetak
		chain.doFilter(request, response); // digunakan agar keluaran dari filter ini itu di CHain atau disambungkan ke Filter yang lain jadi tidak terputus jadi kita perlu menambahkan CHAIN.DOFILTER
	}

}
