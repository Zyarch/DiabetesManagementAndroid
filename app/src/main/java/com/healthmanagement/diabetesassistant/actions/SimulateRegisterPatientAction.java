package com.healthmanagement.diabetesassistant.actions;

import android.content.Context;

import com.healthmanagement.diabetesassistant.actions.interfaces.IRegisterPatientAction;
import com.healthmanagement.diabetesassistant.enums.ErrorCode;
import com.healthmanagement.diabetesassistant.singletons.PatientSingleton;

public class SimulateRegisterPatientAction implements IRegisterPatientAction
{
	@Override
	public ErrorCode registerPatient( Context context, PatientSingleton patientSingleton, String password )
	{
		try
		{
			Thread.sleep(2000);
		}
		catch( InterruptedException e )
		{
			e.printStackTrace();
		}
		return ErrorCode.NO_ERROR;

	} // registerPatient

} // class
