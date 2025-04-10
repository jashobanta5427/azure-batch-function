package com.azure_batch_function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.microsoft.azure.functions.ExecutionContext;

import java.util.Locale;
import java.util.function.Consumer;

@Configuration
public class BatchFunction {

    private static final Logger logger = LoggerFactory.getLogger(BatchFunction.class);

    @Bean
    public Consumer<Message<String>> processBatch() {
        return message -> {
        	String timeInfo = message.getPayload();
			String value = timeInfo.toUpperCase(Locale.ROOT);

			logger.info("Timer is triggered with TimeInfo: " + value);

            ExecutionContext context = (ExecutionContext) message.getHeaders().get(BatchTimerHandler.EXECUTION_CONTEXT);
			context.getLogger().info("✅ Batch job executed at: " + java.time.LocalDateTime.now());
            
        };
    }
}

