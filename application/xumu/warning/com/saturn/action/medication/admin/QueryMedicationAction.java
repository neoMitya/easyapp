package com.saturn.action.medication.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saturn.app.web.IAction;
import com.saturn.app.web.IView;
import com.saturn.app.web.view.JspView;
import com.saturn.medication.Medication;

public class QueryMedicationAction implements IAction {

	public IView execute(HttpServletRequest request,
			HttpServletResponse response) {

		String id = request.getParameter("id");
		Medication medication = Medication.get(id);
		
		request.setAttribute("medication", medication);
		return new JspView("/app/medication/admin/medicationEdit.jsp");
	}

	public String requestMapping() {
		return "/app/medication/queryMedication.action";
	}

}
