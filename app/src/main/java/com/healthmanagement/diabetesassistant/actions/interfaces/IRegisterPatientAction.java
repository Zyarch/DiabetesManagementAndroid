package com.healthmanagement.diabetesassistant.actions.interfaces;

import android.content.Context;

import com.healthmanagement.diabetesassistant.enums.ErrorCode;
import com.healthmanagement.diabetesassistant.singletons.PatientSingleton;

import org.json.JSONException;

public interface IRegisterPatientAction
{
	ErrorCode registerPatient( Context context, PatientSingleton patientSingleton, String password ) throws JSONException;
}
