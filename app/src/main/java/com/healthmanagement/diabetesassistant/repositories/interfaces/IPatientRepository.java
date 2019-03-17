package com.healthmanagement.diabetesassistant.repositories.interfaces;

import android.database.Cursor;

import com.healthmanagement.diabetesassistant.singletons.PatientSingleton;

public interface IPatientRepository extends IApplicationUserRepository<PatientSingleton>
{
	PatientSingleton getLoggedInUser();

	Cursor getCursorForLoggedInUser();

	void setAllSynced();

} // interface
