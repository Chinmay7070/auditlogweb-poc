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