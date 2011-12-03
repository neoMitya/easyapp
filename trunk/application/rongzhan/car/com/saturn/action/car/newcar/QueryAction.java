package com.saturn.action.car.newcar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saturn.app.web.IAction;
import com.saturn.app.web.IView;
import com.saturn.app.web.view.JspView;
import com.saturn.car.NewCar;

public class QueryAction implements IAction {

	public IView execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String id = request.getParameter("id");
		NewCar vo = NewCar.get(id);
		
		request.setAttribute("newcar", vo);
		return new JspView("/app/car/newcar/edit.jsp");
	}

	public String requestMapping() {
		return "/app/car/newcar/query.action";
	}

}
