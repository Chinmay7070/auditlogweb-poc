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
@Table(name = "auditlog_read_event")
public class ReadAuditEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private String entityType;
    private String entityId;
    private String methodName;

    @Column(name = "accessed_on")
    private Date accessedOn;
}