package com.customer.fawvw.issues.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

import com.customer.fawvw.issues.actions.IssueSingleReportAction;
import com.teamcenter.rac.aifrcp.AIFUtility;

public class IssueSingleReportHandler extends AbstractHandler implements
		IHandler {
	public IssueSingleReportHandler (){
		
	}


	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		try
        {
            com.teamcenter.rac.aif.AbstractAIFUIApplication app = AIFUtility.getCurrentApplication();
            IssueSingleReportAction action = new IssueSingleReportAction(app, null, null);
            (new Thread(action)).start();
        }
        catch(Exception _ex) { }
        return null;
	}


}