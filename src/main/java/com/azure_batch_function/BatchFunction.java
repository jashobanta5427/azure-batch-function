package com.azure_batch_function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class BatchFunction {

    @Bean
    Consumer<String> processBatch() {
        return input -> {
            System.out.println("✅ Batch job executed at: " + java.time.LocalDateTime.now());
            // Your batch processing logic here
        };
    }
}

