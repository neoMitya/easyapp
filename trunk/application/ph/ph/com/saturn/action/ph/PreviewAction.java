package com.saturn.action.ph;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saturn.app.web.IAction;
import com.saturn.app.web.IView;
import com.saturn.app.web.view.JspErrorView;
import com.saturn.app.web.view.JspView;
import com.saturn.ph.FormManager;
import com.saturn.ph.PH;
import com.teamcenter.soa.client.model.ModelObject;
import com.teamcenter.soa.exceptions.NotLoadedException;

public class PreviewAction implements IAction {

	public String requestMapping() {
		return "/app/pep/do/preview.do";
	}

	public IView execute(HttpServletRequest request,
			HttpServletResponse response) {

		String uid = (String)request.getParameter("uid");
		/*Integer current = Integer.parseInt(request.getParameter("current").toString());
		if(current != null){
			int curr = current.intValue();
			if(curr >= 0){
				request.setAttribute("current", String.valueOf(curr));
			}
		}*/
		ModelObject object = PH.getDataService().loadModelObjectRefresh(uid);
		
		if (object == null) {
			return new JspErrorView("Uid=[" + uid + "] 不存在");
		}

		String type = object.getType().getName();
		if(type.equalsIgnoreCase("JPEG")){
			try {
				type = object.getProperty("object_name").getDisplayableValue();
			} catch (NotLoadedException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("uid", uid);
		request.setAttribute("type", type);
		request.setAttribute("form", FormManager.getFormValue(type, uid, true));
		
		return new JspView(FormManager.getJspPath(type));
	}
}