/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

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