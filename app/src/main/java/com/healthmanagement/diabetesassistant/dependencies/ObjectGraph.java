package com.healthmanagement.diabetesassistant.dependencies;

import android.content.Context;

import com.healthmanagement.diabetesassistant.actions.DbLogExerciseEntryAction;
import com.healthmanagement.diabetesassistant.actions.DbLogGlucoseEntryAction;
import com.healthmanagement.diabetesassistant.actions.DbLogMealEntryAction;
import com.healthmanagement.diabetesassistant.actions.RemoteLoginAction;
import com.healthmanagement.diabetesassistant.actions.RemoteRegisterPatientAction;
import com.healthmanagement.diabetesassistant.actions.RemoteRetrieveDoctorsAction;
import com.healthmanagement.diabetesassistant.actions.RemoteSyncPatientDataAction;
import com.healthmanagement.diabetesassistant.actions.SimulateRegisterPatientAction;
import com.healthmanagement.diabetesassistant.actions.SimulateRetrieveDoctorsAction;
import com.healthmanagement.diabetesassistant.actions.interfaces.ILogExerciseEntryAction;
import com.healthmanagement.diabetesassistant.actions.interfaces.ILogGlucoseEntryAction;
import com.healthmanagement.diabetesassistant.actions.interfaces.ILogMealEntryAction;
import com.healthmanagement.diabetesassistant.actions.interfaces.ILoginAction;
import com.healthmanagement.diabetesassistant.actions.interfaces.IRegisterPatientAction;
import com.healthmanagement.diabetesassistant.actions.interfaces.IRetrieveDoctorsAction;
import com.healthmanagement.diabetesassistant.actions.interfaces.ISyncPatientDataAction;
import com.healthmanagement.diabetesassistant.repositories.DbApplicationUserRepository;
import com.healthmanagement.diabetesassistant.repositories.DbDoctorRepository;
import com.healthmanagement.diabetesassistant.repositories.DbExerciseEntryRepository;
import com.healthmanagement.diabetesassistant.repositories.DbGlucoseEntryRepository;
import com.healthmanagement.diabetesassistant.repositories.DbMealEntryRepository;
import com.healthmanagement.diabetesassistant.repositories.DbPatientRepository;
import com.healthmanagement.diabetesassistant.repositories.interfaces.IApplicationUserRepository;
import com.healthmanagement.diabetesassistant.repositories.interfaces.IDoctorRepository;
import com.healthmanagement.diabetesassistant.repositories.interfaces.IExerciseEntryRepository;
import com.healthmanagement.diabetesassistant.repositories.interfaces.IGlucoseEntryRepository;
import com.healthmanagement.diabetesassistant.repositories.interfaces.IMealEntryRepository;
import com.healthmanagement.diabetesassistant.repositories.interfaces.IPatientRepository;

import java.util.HashMap;
import java.util.Map;

// Adapted from:
// https://softwareengineering.stackexchange.com/questions/354465/pure-dependency-injection-how-to-implement-it
class ObjectGraph
{
	private final Map<Class<?>, Object> dependencies = new HashMap<>();    // Holds all dependencies

	ObjectGraph( Context context )    // package-private
	{
		/*
			Step 1.  create dependency graph:
		 */

		// Log Actions:
		ILogMealEntryAction logMealEntryAction = new DbLogMealEntryAction();
		ILogExerciseEntryAction logExerciseEntryAction = new DbLogExerciseEntryAction();
		ILogGlucoseEntryAction logGlucoseEntryAction = new DbLogGlucoseEntryAction();

		// Sync Actions:
		ISyncPatientDataAction syncPatientDataAction = new RemoteSyncPatientDataAction();

		// Misc. Remote Actions:
		ILoginAction remoteLoginAction = new RemoteLoginAction();
		IRetrieveDoctorsAction retrieveDoctorsAction = new RemoteRetrieveDoctorsAction(); //RetrieveDoctorsAction();
		IRegisterPatientAction registerPatientAction = new RemoteRegisterPatientAction(); //RemoteRegisterPatientAction();

		// Repositories:
		IPatientRepository patientRepository = new DbPatientRepository( context );
		IApplicationUserRepository userRepository = new DbApplicationUserRepository( context );
		IDoctorRepository doctorRepository = new DbDoctorRepository( context );
		IExerciseEntryRepository exerciseEntryRepository = new DbExerciseEntryRepository( context );
		IGlucoseEntryRepository glucoseEntryRepository = new DbGlucoseEntryRepository( context );
		IMealEntryRepository mealEntryRepository = new DbMealEntryRepository( context );


		/*
			Step 2. add models which you will need later to a dependencies map
		 */

		// Log actions:
		dependencies.put( ILogMealEntryAction.class, logMealEntryAction );
		dependencies.put( ILogExerciseEntryAction.class, logExerciseEntryAction );
		dependencies.put( ILogGlucoseEntryAction.class, logGlucoseEntryAction );

		// Sync actions:
		dependencies.put( ISyncPatientDataAction.class, syncPatientDataAction );

		// Remote Actions:
		dependencies.put( ILoginAction.class, remoteLoginAction );
		dependencies.put( IRegisterPatientAction.class, registerPatientAction );
		dependencies.put( IRetrieveDoctorsAction.class, retrieveDoctorsAction );

		// Repositories:
		dependencies.put( IPatientRepository.class, patientRepository );
		dependencies.put( IApplicationUserRepository.class, userRepository );
		dependencies.put( IDoctorRepository.class, doctorRepository );
		dependencies.put( IExerciseEntryRepository.class, exerciseEntryRepository );
		dependencies.put( IGlucoseEntryRepository.class, glucoseEntryRepository );
		dependencies.put( IMealEntryRepository.class, mealEntryRepository );

	} // constructor


	<T> T get( Class<T> model )
	{
		return model.cast( dependencies.get( model ) );

	} // get


	<T> void putMock( Class<T> clazz, T object )
	{
		dependencies.put( clazz, object );

	} // putMock

} // class
