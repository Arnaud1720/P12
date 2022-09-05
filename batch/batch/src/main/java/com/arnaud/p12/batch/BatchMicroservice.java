package com.arnaud.p12.batch;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "taskOrdonnanceur", url = "localhost:8080")

public interface BatchMicroservice {

}
