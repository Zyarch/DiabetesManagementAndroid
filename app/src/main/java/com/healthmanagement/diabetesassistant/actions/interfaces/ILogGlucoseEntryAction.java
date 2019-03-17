package com.healthmanagement.diabetesassistant.actions.interfaces;

import android.content.Context;

import com.healthmanagement.diabetesassistant.models.GlucoseEntry;
import com.healthmanagement.diabetesassistant.enums.ErrorCode;

public interface ILogGlucoseEntryAction
{
    ErrorCode logGlucoseEntry( Context context, GlucoseEntry glucoseEntry ) throws InterruptedException;

} // interface
