/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.auditlogweb.api.impl;

import org.openmrs.module.auditlogweb.ClinicalAuditEvent;
import org.openmrs.module.auditlogweb.api.ClinicalEventService;
import org.openmrs.module.auditlogweb.api.dao.ClinicalEventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClinicalEventServiceImpl implements ClinicalEventService {

    @Autowired
    private ClinicalEventDao clinicalEventDao;

    @Override
    public void logClinicalEvent(ClinicalAuditEvent event) {
        clinicalEventDao.save(event);
    }
}