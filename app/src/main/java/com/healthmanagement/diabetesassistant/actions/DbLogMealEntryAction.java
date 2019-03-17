package com.healthmanagement.diabetesassistant.actions;

import android.content.Context;

import com.healthmanagement.diabetesassistant.actions.interfaces.ILogMealEntryAction;
import com.healthmanagement.diabetesassistant.models.MealEntry;
import com.healthmanagement.diabetesassistant.enums.ErrorCode;
import com.healthmanagement.diabetesassistant.repositories.DbMealEntryRepository;

public class DbLogMealEntryAction implements ILogMealEntryAction
{
	@Override
	public ErrorCode logMealEntry( Context context, MealEntry mealEntry ) throws InterruptedException
	{
		DbMealEntryRepository mealEntryRepository = new DbMealEntryRepository( context );
		mealEntryRepository.create( mealEntry );

		return ErrorCode.NO_ERROR;

	} // logMealEntry

} // class
