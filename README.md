# Audit Log Web Module — GSoC 2026 POC

This is a proof of concept for the GSoC 2026 project 
"Extend Audit Log Module in OpenMRS".

## What this POC demonstrates

- Read/View Auditing using Spring AOP
- Clinical Workflow Auditing using Spring AOP

## New files added

- ReadAuditEvent.java — entity for read events
- ReadAuditAspect.java — AOP aspect for read tracking
- ClinicalAuditEvent.java — entity for clinical events  
- ClinicalAuditAspect.java — AOP aspect for clinical tracking
- Updated liquibase.xml — new audit tables
- Updated moduleApplicationContext.xml — AOP enabled

## Base module

Built on top of openmrs-module-auditlogweb (GSoC 2025)
