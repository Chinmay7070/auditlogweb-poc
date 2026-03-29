package org.openmrs.module.auditlogweb.api;

import java.util.Date;

public interface ReadEventService {
    void logReadEvent(Integer userId, String entityType,
                      String entityId, String methodName, Date accessedOn);
}