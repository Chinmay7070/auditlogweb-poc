package org.openmrs.module.auditlogweb.api.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.openmrs.api.context.Context;
import org.openmrs.module.auditlogweb.ClinicalAuditEvent;
import org.openmrs.module.auditlogweb.api.ClinicalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

@Aspect
@Component
public class ClinicalAuditAspect {

    @Autowired
    private ClinicalEventService clinicalEventService;

    @AfterReturning(
            pointcut = "execution(* org.openmrs.api.EncounterService.saveEncounter(..))" +
                    " || execution(* org.openmrs.api.OrderService.saveOrder(..))",
            returning = "result"
    )
    public void logClinicalEvent(JoinPoint joinPoint, Object result) {
        if (result == null) return;
        try {
            Integer userId = null;
            if (Context.isAuthenticated()) {
                userId = Context.getAuthenticatedUser().getUserId();
            }
            String eventType = joinPoint.getSignature().getName();
            String entityType = result.getClass().getSimpleName();

            ClinicalAuditEvent event = new ClinicalAuditEvent();
            event.setUserId(userId);
            event.setEventType(eventType);
            event.setDescription(entityType + " was " + eventType);
            event.setEventTime(new Date());
            clinicalEventService.logClinicalEvent(event);
        } catch (Exception e) {
            // never break main flow
        }
    }
}