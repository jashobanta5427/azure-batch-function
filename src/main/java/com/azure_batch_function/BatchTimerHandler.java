package com.azure_batch_function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Consumer;

@Component
public class BatchTimerHandler {

    @Autowired
    private Consumer<String> processBatch;

    @FunctionName("batchJob")
    public void runBatchJob(
        @TimerTrigger(name = "timerInfo", schedule = "0 */5 * * * *") String timerInfo,
        final ExecutionContext context) {

        context.getLogger().info("Batch job triggered by timer: " + timerInfo);
        processBatch.accept("TriggeredByTimer");
    }
}


