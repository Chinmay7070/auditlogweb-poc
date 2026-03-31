package org.openmrs.module.auditlogweb.api;

import org.openmrs.module.auditlogweb.ClinicalAuditEvent;

public interface ClinicalEventService {
    void logClinicalEvent(ClinicalAuditEvent event);
}