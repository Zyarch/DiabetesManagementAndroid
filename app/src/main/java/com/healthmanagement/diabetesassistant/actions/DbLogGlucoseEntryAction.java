package com.healthmanagement.diabetesassistant.actions;

import android.content.Context;

import com.healthmanagement.diabetesassistant.actions.interfaces.ILogGlucoseEntryAction;
import com.healthmanagement.diabetesassistant.models.GlucoseEntry;
import com.healthmanagement.diabetesassistant.enums.ErrorCode;
import com.healthmanagement.diabetesassistant.repositories.DbGlucoseEntryRepository;
import com.healthmanagement.diabetesassistant.repositories.DbPatientRepository;
import com.healthmanagement.diabetesassistant.singletons.PatientSingleton;

public class DbLogGlucoseEntryAction implements ILogGlucoseEntryAction
{
	@Override
	public ErrorCode logGlucoseEntry( Context context, GlucoseEntry glucoseEntry ) throws InterruptedException
	{
		DbPatientRepository dbPatientRepository = new DbPatientRepository( context );
		DbGlucoseEntryRepository dbGlucoseEntryRepository = new DbGlucoseEntryRepository( context );
		PatientSingleton patientSingleton = PatientSingleton.getInstance();
		patientSingleton.glucoseEntries.add( glucoseEntry );
		dbPatientRepository.update( patientSingleton.getUserName(), patientSingleton );
		dbGlucoseEntryRepository.create( glucoseEntry );

		return ErrorCode.NO_ERROR;

	} // logGlucoseEntry

} // class
