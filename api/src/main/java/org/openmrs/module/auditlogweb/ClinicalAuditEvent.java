package org.openmrs.module.auditlogweb;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auditlog_clinical_event")
public class ClinicalAuditEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private String eventType;
    private String description;

    @Column(name = "event_time")
    private Date eventTime;
}