package com.azure_batch_function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;

@Component
public class BatchTimerHandler {

	public static String EXECUTION_CONTEXT = "executionContext";

    @Autowired
    private Consumer<Message<String>> processBatch;

    @FunctionName("processBatch")
    @ExponentialBackoffRetry(maxRetryCount = 4, maximumInterval = "00:15:00", minimumInterval = "00:00:03")
    public void executeExpRetry(@TimerTrigger(name = "keepAliveTrigger", schedule = "%TIMER_SCHEDULE%") String timerInfo,
            ExecutionContext context) {


        context.getLogger().info("==========PROCESS BATCH TRIGGERED=========");
        
        Message<String> message = MessageBuilder
                .withPayload(timerInfo)
                .setHeader(EXECUTION_CONTEXT, context)
                .build();

        this.processBatch.accept(message);
    }
}


