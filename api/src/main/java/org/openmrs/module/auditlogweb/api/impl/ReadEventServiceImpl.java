/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.auditlogweb.api.impl;

import org.openmrs.module.auditlogweb.ReadAuditEvent;
import org.openmrs.module.auditlogweb.api.ReadEventService;
import org.openmrs.module.auditlogweb.api.dao.ReadEventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
@Transactional
public class ReadEventServiceImpl implements ReadEventService {

    @Autowired
    private ReadEventDao readEventDao;

    @Override
    public void logReadEvent(Integer userId, String entityType,
                             String entityId, String methodName, Date accessedOn) {
        ReadAuditEvent event = new ReadAuditEvent();
        event.setUserId(userId);
        event.setEntityType(entityType);
        event.setEntityId(entityId);
        event.setMethodName(methodName);
        event.setAccessedOn(accessedOn);
        readEventDao.save(event);
    }
}