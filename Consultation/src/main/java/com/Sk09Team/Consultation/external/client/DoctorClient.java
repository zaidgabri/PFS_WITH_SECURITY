package com.Sk09Team.Consultation.external.client;
import com.Sk09Team.Consultation.external.client.response.DoctorResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@CircuitBreaker(name="external",fallbackMethod = "fallback")
@FeignClient(name = "DOCTOR-SERVICE")
public interface DoctorClient {

    default void fallback(Exception e){
        throw new RuntimeException("Doctor Service is not available!");

    }


}
