package com.azure_batch_function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.microsoft.azure.functions.ExecutionContext;

import java.util.Locale;
import java.util.function.Consumer;

@Configuration
public class BatchFunction {

    @Bean
    public Consumer<Message<String>> processBatch() {
        return message -> {

            ExecutionContext context = (ExecutionContext) message.getHeaders().get(BatchTimerHandler.EXECUTION_CONTEXT);
            context.getLogger().info("========== FUNCTION STARTED ==========");

        	try {
                String timeInfo = message.getPayload();
                String value = timeInfo.toUpperCase(Locale.ROOT);

                context.getLogger().info("Timer is triggered with TimeInfo: " + value);

                context.getLogger().info("✅✅✅Batch job executed at: " + java.time.LocalDateTime.now());
            } catch (Exception e) {
                context.getLogger().severe("❎❎❎Error in function: " + e.getMessage());
                e.printStackTrace();
            }
            
            context.getLogger().info("========== FUNCTION ENDED ==========");
        };
    }
}

