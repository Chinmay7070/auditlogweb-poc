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
import org.openmrs.module.auditlogweb.api.ReadEventService;
import org.openmrs.module.auditlogweb.api.utils.UtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

@Aspect
@Component
public class ReadAuditAspect {

    @Autowired
    private ReadEventService readEventService;

    @AfterReturning(
            pointcut = "execution(* org.openmrs.api.PatientService.get*(..))" +
                    " || execution(* org.openmrs.api.PersonService.get*(..))",
            returning = "result"
    )
    public void logReadAccess(JoinPoint joinPoint, Object result) {
        if (result == null) return;
        try {
            Integer userId = null;
            if (Context.isAuthenticated()) {
                userId = Context.getAuthenticatedUser().getUserId();
            }
            String entityType = result.getClass().getSimpleName();
            String entityId = UtilClass.getEntityIdAsString(result);
            String methodName = joinPoint.getSignature().getName();
            readEventService.logReadEvent(
                    userId, entityType, entityId, methodName, new Date()
            );
        } catch (Exception e) {

        }
    }
}