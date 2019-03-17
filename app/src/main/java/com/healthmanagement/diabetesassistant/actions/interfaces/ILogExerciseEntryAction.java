package com.healthmanagement.diabetesassistant.actions.interfaces;

import android.content.Context;

import com.healthmanagement.diabetesassistant.models.ExerciseEntry;
import com.healthmanagement.diabetesassistant.enums.ErrorCode;

public interface ILogExerciseEntryAction
{
    ErrorCode logExerciseEntry( Context context, ExerciseEntry exerciseEntry ) throws InterruptedException;

} // interface
