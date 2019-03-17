package com.healthmanagement.diabetesassistant.actions;

import android.content.Context;

import com.healthmanagement.diabetesassistant.actions.interfaces.ILogExerciseEntryAction;
import com.healthmanagement.diabetesassistant.models.ExerciseEntry;
import com.healthmanagement.diabetesassistant.enums.ErrorCode;
import com.healthmanagement.diabetesassistant.repositories.DbExerciseEntryRepository;
import com.healthmanagement.diabetesassistant.repositories.DbPatientRepository;
import com.healthmanagement.diabetesassistant.singletons.PatientSingleton;

public class DbLogExerciseEntryAction implements ILogExerciseEntryAction
{
    @Override
    public ErrorCode logExerciseEntry( Context context, ExerciseEntry exerciseEntry ) throws InterruptedException
    {
        DbPatientRepository dbPatientRepository = new DbPatientRepository( context );
        DbExerciseEntryRepository dbExerciseEntryRepository = new DbExerciseEntryRepository( context );
        PatientSingleton patientSingleton = PatientSingleton.getInstance();
        patientSingleton.exerciseEntries.add( exerciseEntry );
        dbPatientRepository.update( patientSingleton.getUserName(), patientSingleton );
        dbExerciseEntryRepository.create( exerciseEntry );

        return ErrorCode.NO_ERROR;

    } // logExerciseEntry

} // class
