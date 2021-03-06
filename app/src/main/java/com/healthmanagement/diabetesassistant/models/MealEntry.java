package com.healthmanagement.diabetesassistant.models;

import com.healthmanagement.diabetesassistant.db.DB;
import com.healthmanagement.diabetesassistant.enums.WhichMeal;
import com.healthmanagement.diabetesassistant.models.interfaces.Syncable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MealEntry implements Syncable
{
	private int       id;
	private String    remoteId;
	private String    userName;
	private boolean   synced;
	private int       totalCarbs;
	private Date      createdAt;
	private Date      updatedAt;
	private long      timestamp;
	private WhichMeal whichMeal;

	private ArrayList<MealItem> mealItems;


	public MealEntry()
	{
		mealItems = new ArrayList<>();
		id = -1;
		remoteId = "";
		userName = "";
		totalCarbs = 0;
		whichMeal = WhichMeal.OTHER;
		Date date = new Date();
		this.createdAt = date;
		updatedAt = createdAt;
		timestamp = date.getTime();

	} // constructor

	public int getId()
	{
		return id;
	}

	public void setId( int id )
	{
		this.id = id;
	}

	public String getRemoteId()
	{
		return remoteId;
	}

	public void setRemoteId( String remoteId )
	{
		this.remoteId = remoteId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName( String userName )
	{
		this.userName = userName;
	}

	public boolean isSynced()
	{
		return synced;
	}

	public void setSynced( boolean synced )
	{
		this.synced = synced;
	}

	public int getTotalCarbs()
	{
		return totalCarbs;
	}

	public void setTotalCarbs( int totalCarbs )
	{
		this.totalCarbs = totalCarbs;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt( Date createdAt )
	{
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt( Date updatedAt )
	{
		this.updatedAt = updatedAt;
	}

	public long getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp( long timestamp )
	{
		this.timestamp = timestamp;
	}

	public ArrayList<MealItem> getMealItems()
	{
		return mealItems;
	}

	public void setMealItems( ArrayList<MealItem> mealItems )
	{
		this.mealItems = mealItems;
	}

	public WhichMeal getWhichMeal()
	{
		return whichMeal;
	}

	public void setWhichMeal( WhichMeal whichMeal )
	{
		this.whichMeal = whichMeal;
	}


	@Override
	public String toString()
	{
		try
		{
			return toJSONObject().toString();
		}
		catch( JSONException e )
		{
			e.printStackTrace();
			return "";
		}

	} // toString


	public JSONObject toJSONObject() throws JSONException
	{
		JSONObject mealEntry = new JSONObject();

		if( !remoteId.isEmpty() )
			mealEntry.put( DB.KEY_REMOTE_ID, remoteId );
		if( timestamp > 0 )
			mealEntry.put( DB.KEY_TIMESTAMP, timestamp );
		mealEntry.put( DB.KEY_USERNAME, userName );
		mealEntry.put( DB.KEY_MEAL_ENTRY_TOTAL_CARBS, totalCarbs );
		mealEntry.put( DB.KEY_WHICH_MEAL, whichMeal.getValue() );

		try
		{
			DateFormat df = new SimpleDateFormat( "MM/dd/yyyy HH:mm a", Locale.US );
			mealEntry.put( DB.KEY_CREATED_AT, df.format( createdAt ) );
			mealEntry.put( DB.KEY_UPDATED_AT, df.format( updatedAt ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

		if( mealItems.size() > 0 )
		{
			JSONArray mItems = new JSONArray();

			for( MealItem mealItem : mealItems )
			{
				mItems.put( mealItem.toJSONObject() );
			}

			mealEntry.put( DB.KEY_MEAL_ITEMS, mItems );
		}

		return mealEntry;

	} // toJSONObject

} // class
