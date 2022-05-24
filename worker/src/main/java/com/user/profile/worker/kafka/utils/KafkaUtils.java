package com.user.profile.worker.kafka.utils;

import com.user.profile.worker.kafka.model.TraceableReceiverRecord;
import brave.Tracing;
import brave.propagation.TraceContext;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Slf4j
public class KafkaUtils {

    public static void traceRecord(TraceableReceiverRecord<String, String> traceableReceiverRecord, Tracing tracing) {
        TraceContext.Extractor<TraceableReceiverRecord<String, String>> extractor = tracing.propagation().extractor(
                traceableReceiverRecord);
        tracing.tracer().nextSpan(extractor.extract(traceableReceiverRecord)).start();
        TraceContext traceContext = tracing.tracer().nextSpan(extractor.extract(traceableReceiverRecord)).context();
        tracing.currentTraceContext().newScope(traceContext);
    }
}
