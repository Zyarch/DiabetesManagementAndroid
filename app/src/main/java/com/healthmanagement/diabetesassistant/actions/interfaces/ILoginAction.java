package com.healthmanagement.diabetesassistant.actions.interfaces;

import android.content.Context;

import com.healthmanagement.diabetesassistant.enums.ErrorCode;

import org.json.JSONException;

import java.net.MalformedURLException;

public interface ILoginAction
{
	ErrorCode attemptLogin( String username, String password, Context context ) throws MalformedURLException, JSONException;

} // interface
