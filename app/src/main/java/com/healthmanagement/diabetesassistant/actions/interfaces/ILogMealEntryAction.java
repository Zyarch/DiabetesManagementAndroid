package com.healthmanagement.diabetesassistant.actions.interfaces;

import android.content.Context;

import com.healthmanagement.diabetesassistant.models.MealEntry;
import com.healthmanagement.diabetesassistant.enums.ErrorCode;

public interface ILogMealEntryAction
{
	ErrorCode logMealEntry( Context context, MealEntry mealEntry ) throws InterruptedException;

} // interface
