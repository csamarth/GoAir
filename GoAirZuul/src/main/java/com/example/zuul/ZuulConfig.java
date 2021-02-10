package com.example.zuul;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;


@Configuration
public class ZuulConfig {
	
	@Bean
	public FallbackProvider fallbackProvider() {
		
		System.out.println("reaching fallback Provider");
		return new FallbackProvider() {
			
			@Override
			public String getRoute() {
				// TODO Auto-generated method stub
				return "*";
			}
			
			@Override
			public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
				
				// TODO Auto-generated method stub
				return new ClientHttpResponse() {
					
					@Override
					public HttpHeaders getHeaders() {
						// TODO Auto-generated method stub
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.TEXT_PLAIN);
						return headers;
					}
					
					@Override
					public InputStream getBody() throws IOException {
						// TODO Auto-generated method stub
						System.out.println("Reaching print statement");
						return new ByteArrayInputStream("Sorry, something went wrong".getBytes());
					}
					
					@Override
					public String getStatusText() throws IOException {
						// TODO Auto-generated method stub
						return "OKI";
					}
					
					@Override
					public HttpStatus getStatusCode() throws IOException {
						// TODO Auto-generated method stub
						return HttpStatus.ACCEPTED;
					}
					
					@Override
					public int getRawStatusCode() throws IOException {
						// TODO Auto-generated method stub
						return 201;
					}
					
					@Override
					public void close() {
						// TODO Auto-generated method stub
						
					}
				};
			}
		};
	}

}
