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

    private Input input;
    public MlController(CallRequestResponseService callRequestResponseService) {
        this.callRequestResponseService = callRequestResponseService;
    }

    @PostMapping("/setMLInput")
    public ResponseEntity<String> SetMlInput(@RequestBody Input input){
        this.input = input;
        return new ResponseEntity<>("ML Set", HttpStatus.OK);
    }
    @GetMapping("/getML")
    public ResponseEntity<String> GetMlResponse() throws IOException {
        String response = callRequestResponseService.invokeRequestResponseService(input);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
