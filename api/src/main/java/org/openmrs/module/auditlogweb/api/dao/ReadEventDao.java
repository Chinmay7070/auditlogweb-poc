package org.openmrs.module.auditlogweb.api.dao;

import org.openmrs.module.auditlogweb.ReadAuditEvent;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class ReadEventDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(ReadAuditEvent event) {
        sessionFactory.getCurrentSession().save(event);
    }
}