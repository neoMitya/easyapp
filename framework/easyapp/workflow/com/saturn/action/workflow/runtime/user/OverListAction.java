package com.saturn.action.workflow.runtime.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saturn.app.db.ListData;
import com.saturn.app.utils.BeanUtils;
import com.saturn.app.utils.JSONUtils;
import com.saturn.app.web.IAction;
import com.saturn.app.web.IView;
import com.saturn.app.web.easyui.DataGridInfo;
import com.saturn.app.web.view.JsonView;
import com.saturn.auth.User;
import com.saturn.workflow.runtime.WorkFlowInstance;

public class OverListAction implements IAction {

	public IView execute(HttpServletRequest request,
			HttpServletResponse response) {

		DataGridInfo dataGridInfo = new DataGridInfo(request);

		WorkFlowInstance vo = BeanUtils.getBean(request, WorkFlowInstance.class);
		
		User user = (User)request.getSession().getAttribute("authUser");
		vo.setCreater(user.getId());
		
		ListData<WorkFlowInstance> data = WorkFlowInstance.getEnd(vo, dataGridInfo.getStartPage(),
				dataGridInfo.getRows(), dataGridInfo.getSortId(),
				dataGridInfo.getOreder());

		return new JsonView(JSONUtils.getDataGridJSON(data.getTotal(),
				data.getList()));
	}
	
	public String requestMapping() {
		return "/app/workflow/runtime/user/over.action";
	}
	
}





