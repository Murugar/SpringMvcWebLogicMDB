package com.iqmsoft.spring.mdb.wls.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iqmsoft.spring.mdb.wls.model.PatientDetails;


@Repository
public class PatientRecordsInserterDAO {

	private static final Logger logger = LoggerFactory.getLogger(PatientRecordsInserterDAO.class);

	@PersistenceContext
	private EntityManager em;

	public void saveDetails(PatientDetails patientDetails) {
		// TODO Auto-generated method stub

		logger.info("DAO LAYER :" + patientDetails.getlName());

		em.persist(patientDetails);
	}

}
