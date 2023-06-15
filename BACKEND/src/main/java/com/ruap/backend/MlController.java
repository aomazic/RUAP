package com.ruap.backend;

import com.ruap.backend.model.Input;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class MlController {
    private final CallRequestResponseService callRequestResponseService;

    public MlController(CallRequestResponseService callRequestResponseService) {
        this.callRequestResponseService = callRequestResponseService;
    }

    @GetMapping("/getML")
    public ResponseEntity<String> GetMlResponse(@RequestBody Input input) throws IOException {
        String response = callRequestResponseService.invokeRequestResponseService(input);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
