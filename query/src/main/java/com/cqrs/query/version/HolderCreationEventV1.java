package com.cqrs.query.version;

import com.cqrs.events.HolderCreationEvent;
import org.axonframework.serialization.SimpleSerializedType;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;

public class HolderCreationEventV1 extends SingleEventUpcaster {
    private static SimpleSerializedType targetType = new SimpleSerializedType(HolderCreationEvent.class.getTypeName(),null);

    @Override
    public boolean canUpcast(IntermediateEventRepresentation intermediateEventRepresentation) {
        return intermediateEventRepresentation.getType().equals(targetType);
    }

    @Override
    public IntermediateEventRepresentation doUpcast(IntermediateEventRepresentation intermediateEventRepresentation) {
        return intermediateEventRepresentation.upcastPayload(
                new SimpleSerializedType(targetType.getName(), "1.0"),
                org.dom4j.Document.class,
                document -> {
                    document.getRootElement()
                            .addElement("company")
                            .setText("N/A");
                    return document;
                }
        );
    }
}
