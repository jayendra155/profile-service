package com.user.profile.worker.kafka.model;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import brave.propagation.Propagation;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverRecord;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
public class TraceableReceiverRecord<K, V> extends ReceiverRecord<K, V>
        implements Propagation.Getter<TraceableReceiverRecord<K, V>, String> {

    Map<String, String> headersMap = new HashMap<>();

    public TraceableReceiverRecord(ConsumerRecord<K, V> consumerRecord, ReceiverOffset receiverOffset) {
        super(consumerRecord, receiverOffset);
        getHeaders();
    }

    public TraceableReceiverRecord(ReceiverRecord<K, V> receiverRecord) {
        super(receiverRecord, receiverRecord.receiverOffset());
        getHeaders();
    }

    protected Map<String, String> getHeaders() {
        Headers headers = headers();
        headers.forEach(header -> headersMap.put(header.key(), new String(header.value())));
        return headersMap;
    }

    @Override
    public String get(TraceableReceiverRecord carrier, String key) {
        return headersMap.getOrDefault(key, "");
    }

}
