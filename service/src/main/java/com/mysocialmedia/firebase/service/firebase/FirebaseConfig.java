package com.mysocialmedia.firebase.service.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

@Configuration
public class FirebaseConfig {

    @Value("${bucket.name}")
    private String bucketname;


    @PostConstruct
    public void init() throws IOException {
        Resource resource = new ClassPathResource("ejemplos-keys.json");
        var serviceAccount =  resource.getInputStream();
//
//        FileInputStream serviceAccount =
//                new FileInputStream("src/main/resources/ejemplos-keys.json");


        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(bucketname)
                .build();
        FirebaseApp.initializeApp(options);
    }
}