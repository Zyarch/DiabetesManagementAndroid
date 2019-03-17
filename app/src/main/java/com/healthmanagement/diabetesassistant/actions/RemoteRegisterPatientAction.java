//--------------------------------------------------------------------------------------//
//																						//
// File Name:	RemoteRegisterPatientAction.java										//
// Programmer:	J.T. Blevins (jt.blevins@gmail.com)										//
// Date:		10/08/2018																//
// Purpose:		Allows registering a user to the remote server.							//
//																						//
//--------------------------------------------------------------------------------------//

package com.healthmanagement.diabetesassistant.actions;

import android.content.Context;
import android.util.Log;

import com.healthmanagement.diabetesassistant.actions.interfaces.IRegisterPatientAction;
import com.healthmanagement.diabetesassistant.db.DB;
import com.healthmanagement.diabetesassistant.dependencies.Dependencies;
import com.healthmanagement.diabetesassistant.enums.ErrorCode;
import com.healthmanagement.diabetesassistant.repositories.DbPatientRepository;
import com.healthmanagement.diabetesassistant.repositories.interfaces.IPatientRepository;
import com.healthmanagement.diabetesassistant.singletons.PatientSingleton;
import com.healthmanagement.diabetesassistant.singletons.WebClientConnectionSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.healthmanagement.diabetesassistant.activities.MainActivity.DEBUG;

public class RemoteRegisterPatientAction implements IRegisterPatientAction
{
	private final String LOG_TAG = getClass().getSimpleName();

	// TODO: Test
	@Override
	public ErrorCode registerPatient( Context context,
									  PatientSingleton patientSingleton,
									  final String password ) throws JSONException
	{
		// Send the registration request to the server:
		WebClientConnectionSingleton webConnection =            // Get the connection manager
				WebClientConnectionSingleton.getInstance( context );
		HashMap<String, String> values = new HashMap<>();
		values.put( "Email", patientSingleton.getEmail() );
		values.put( "Password", password );
		values.put( "DoctorUserName", patientSingleton.getDoctorUserName() );
		String jsonString = webConnection.sendRegisterRequest( values );

		if( DEBUG ) Log.e( LOG_TAG, "Response String: " + jsonString );

		if( jsonString.isEmpty() )
			return ErrorCode.UNKNOWN;

		JSONObject jsonObject = new JSONObject( jsonString );   // Can throw exception

		ErrorCode errorCode = ErrorCode.interpretErrorCode( jsonObject );

		if( errorCode != ErrorCode.NO_ERROR && errorCode != ErrorCode.USER_ALREADY_LOGGED_IN )
			return errorCode;

		// Set the id/login info to the information returned:
		PatientSingleton.copyFrom( jsonString );
		patientSingleton.setLoggedIn( true );

		// The patient hasn't been created in db yet, so add to the database:
		IPatientRepository patientRepository = Dependencies.get( IPatientRepository.class );
		if( patientRepository.exists( patientSingleton.getUserName() ) )             // If exists...
			patientRepository.update( patientSingleton.getUserName(), patientSingleton ); // update
		else
			// Otherwise, just create in db
			patientRepository.create( patientSingleton );

		return ErrorCode.NO_ERROR;

	} // registerPatient

} // class
