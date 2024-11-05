package com.practice.asset.assettesting.controller;


import com.practice.asset.assettesting.model.AssetRequest;
import com.practice.asset.assettesting.model.AssetResponse;
import com.practice.asset.assettesting.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/assets")
public class ApplicationController {

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<AssetResponse> createAsset(HttpServletRequest request, @RequestBody AssetRequest assetRequest){
        AssetResponse response = new AssetResponse();


        String token = request.getHeader("Authorization");
        String userName = request.getHeader("userName");

        if (token == null || !isValidToken(token, userName)) {
            throw new RuntimeException("Invalid session. Please log in again.");
        }

        response.setAssetId(UUID.randomUUID().toString());
//        Map<String, String> headers = new HashMap<>();

        return new ResponseEntity<>(response,HttpStatusCode.valueOf(200));
    }

    private boolean isValidToken(String token, String userName) {
        // Implement your token validation logic (e.g., decode JWT and check validity)
        return jwtUtil.validateToken(token, userName);
//        return true; // Replace with actual validation logic
    }

}
