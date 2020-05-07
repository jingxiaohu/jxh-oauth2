package com.jxh.resource2server.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源服务器2-资源接口
 */
@RestController
public class ResController {

    @GetMapping("/res")
    public ResponseEntity<String> res(){
        return ResponseEntity.ok("<h1>这是资源服务器2的受保护的资源</h1>");
    }

}
