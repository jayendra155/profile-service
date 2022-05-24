package com.user.profile.api.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import com.user.profile.model.domain.mongo.model.BusinessProfileUpdateRequestDoc;
import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;


@ExtendWith(MockitoExtension.class)
public class BusinessProfileUpdateRequestPersistentServiceTest {

    @Mock
    private BusinessProfileService service;

    @Mock
    private Tracer tracer;

    @InjectMocks
    private BusinessProfileUpdateRequestPersistentService persistentService;

    @BeforeEach
    public void beforeTest() {
        Span span = Mockito.mock(Span.class);
        Mockito.when(tracer.currentSpan()).thenReturn(span);
        TraceContext context = Mockito.mock(TraceContext.class);
        Mockito.when(span.context()).thenReturn(context);
        Mockito.when(context.traceIdString()).thenReturn("aksjafbh");
        Mockito.when(service.insertNewUpdateRequest(ArgumentMatchers.any())).thenReturn(dummyUpdateObject());
    }

    private BusinessProfileUpdateRequestDoc dummyUpdateObject() {
        return new BusinessProfileUpdateRequestDoc();
    }

    @Test
    void execute() {
        BusinessProfileUpdateRequestDoc executed = persistentService.execute(
                new BusinessProfileUpdateRequestDoc());
        Assertions.assertNotNull(executed);

    }
}