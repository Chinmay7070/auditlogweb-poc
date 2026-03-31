package org.openmrs.module.auditlogweb.api.dao;

import org.openmrs.module.auditlogweb.ClinicalAuditEvent;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class ClinicalEventDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(ClinicalAuditEvent event) {
        sessionFactory.getCurrentSession().save(event);
    }
}