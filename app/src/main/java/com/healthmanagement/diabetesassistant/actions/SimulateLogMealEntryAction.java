package com.healthmanagement.diabetesassistant.actions;

import android.content.Context;

import com.healthmanagement.diabetesassistant.actions.interfaces.ILogMealEntryAction;
import com.healthmanagement.diabetesassistant.models.MealEntry;
import com.healthmanagement.diabetesassistant.enums.ErrorCode;

public class SimulateLogMealEntryAction implements ILogMealEntryAction
{
	@Override
	public ErrorCode logMealEntry( Context context, MealEntry mealEntry ) throws InterruptedException
	{
		Thread.sleep( 2000 );		// Simulate working for 2 seconds
		return ErrorCode.NO_ERROR;

	} // logMealEntry

} // class
