package com.customer.fawvw.issues.commands.issuestatistic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customer.fawvw.issues.exception.FawvmLoaderException;
import com.customer.fawvw.issues.utils.ComponentUtils;
import com.customer.fawvw.issues.utils.DateUtil;
import com.customer.fawvw.issues.utils.StringUtil;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentProject;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;

public class IssueReportLoaderRichClient {

	private IssueReportLoaderRichClient() {
	}
	
	public static List<TCComponent> selectComponent (TCComponent[] tccomponents,HashMap<String, Object> parameters){
		
		List<TCComponent> selectedTccomponents = new ArrayList<TCComponent>();
	
		try {
		
			String projectId = ((TCComponentProject)parameters.get("projectInfo")).getProperty("project_id");  
	System.out.println("projectId = " + projectId);		 
			for (int i = 0; i < tccomponents.length; i++) {
					
				TCComponentItemRevision itemRevision = ((TCComponentItem)tccomponents[i]).getLatestItemRevision();
				String selectedtime = itemRevision.getProperty("fv9ProposedDate");  
				//��Ŀ������ĿID
				TCComponent[] projects = ComponentUtils.getItemRevisionProjectIds(
						itemRevision, "fv9ProjectLov"); 
				String[] projectIds = ComponentUtils.getProjectInfos(projects, "project_id"); 
				String projectInfos = StringUtil.ArrayToString(projectIds);
				if (DateUtil.betweenStartAndEnd(selectedtime, 
						(String)parameters.get("startTime"),  
						(String)parameters.get("endTime")) && 
						StringUtil.containsNo(projectInfos, projectId)) {
					selectedTccomponents.add(tccomponents[i]);
				}
			}
		} catch (TCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selectedTccomponents;
	}

	public static HashMap<String, Object> load(HashMap<String, Object> parameters) throws Exception{
		try {
			System.out.println("ѡ����Ŀ��" + parameters.get("projectInfo") + "\r\n" +   //$NON-NLS-3$
					"ѡ��ʱ��Σ�" + parameters.get("startTime") + " - " + parameters.get("endTime"));   //$NON-NLS-3$ //$NON-NLS-4$
			
			System.out.println("��ʼ��ȡ����"); 
			
			HashMap<String, Object> values = new HashMap<String, Object>();
			
			TCSession session = (TCSession)parameters.get("session"); 
			
//			�Զ����ѯ[issues_RichClient]
			String[] conditions = new String[] {"ID"}; 
//			String[] inputs = new String[] {"*" + ((TCComponentProject)(parameters.get("projectInfo"))).getProperty("project_id") + "*"};  
			String[] inputs = new String[] {"*"};  
			TCComponent[] tcComponents = ComponentUtils.findTCComponentByQuery(
						(TCSession)parameters.get("session"),  
						"issues_RichClient", 
						conditions, inputs);
System.out.println("tcComponents.length = " + tcComponents.length);			 
			if (tcComponents.length > 0) {
				
				List<TCComponent> rightComponent = selectComponent(tcComponents, parameters);
				
				if (rightComponent.size() > 0) {
					ArrayList<HashMap<String, Object>> issuelist = new ArrayList<HashMap<String, Object>>();
					
					for (TCComponent tcComponent : rightComponent) {
						HashMap<String, Object> item = new HashMap<String, Object>();
						TCComponentItemRevision fv9IssueRevision = ((TCComponentItem) tcComponent).getLatestItemRevision();
						System.out.println("��ȡ���⣺" + fv9IssueRevision + "����Ϣ");
						item.put("item_id", fv9IssueRevision.getProperty("item_id"));  
						item.put("fv9ProposedDate", fv9IssueRevision.getDateProperty("fv9ProposedDate"));  
						item.put("fv9AssPlacement", fv9IssueRevision.getProperty("fv9AssPlacement")); 
						item.put("fv9IssueDesc", fv9IssueRevision.getProperty("fv9IssueDesc")); 
						item.put("fv9IssueCause", fv9IssueRevision.getProperty("fv9IssueCause")); 
						item.put("fv9IssueTempSolution", fv9IssueRevision.getProperty("fv9IssueTempSolution"));  
						item.put("fv9TempSolutionDL", fv9IssueRevision.getDateProperty("fv9TempSolutionDL"));  
						item.put("fv9Solution1", fv9IssueRevision.getProperty("fv9Solution1")); 
						item.put("fv9SlResDep1", fv9IssueRevision.getProperty("fv9SlResDep1")); 
						item.put("fv9SlResOwner1", fv9IssueRevision.getProperty("fv9SlResOwner1")); 
						item.put("fv9Solution2", fv9IssueRevision.getProperty("fv9Solution2")); 
						item.put("fv9SlResDep2", fv9IssueRevision.getProperty("fv9SlResDep2")); 
						item.put("fv9SlResOwner2", fv9IssueRevision.getProperty("fv9SlResOwner2")); 
						item.put("fv9Solution3", fv9IssueRevision.getProperty("fv9Solution3")); 
						item.put("fv9SlResDep3", fv9IssueRevision.getProperty("fv9SlResDep3")); 
						item.put("fv9SlResOwner3", fv9IssueRevision.getProperty("fv9SlResOwner3")); 
						item.put("fv9Solution4", fv9IssueRevision.getProperty("fv9Solution4")); 
						item.put("fv9SlResDep4", fv9IssueRevision.getProperty("fv9SlResDep4")); 
						item.put("fv9SlResOwner4", fv9IssueRevision.getProperty("fv9SlResOwner4")); 
						item.put("fv9Solution5", fv9IssueRevision.getProperty("fv9Solution5")); 
						item.put("fv9SlResDep5", fv9IssueRevision.getProperty("fv9SlResDep5")); 
						item.put("fv9SlResOwner5", fv9IssueRevision.getProperty("fv9SlResOwner5")); 
						item.put("fv9CompletedDate", fv9IssueRevision.getDateProperty("fv9CompletedDate")); 
						item.put("fv9RGStatus", fv9IssueRevision.getProperty("fv9RGStatus")); 
						item.put("fv9IssueType", fv9IssueRevision.getProperty("fv9IssueType")); 
						
						issuelist.add(item);
					}
					
//					ArrayList<HashMap<String, Object>> issuelist = transVOToArrayList(issueVos);
					//���ո���ά��ͳ������
					//���β���
					Map<String,Object> department = DepartmentStatusLoader.load(issuelist);
					System.out.println("�����β���ͳ��" 
							+ "\r\n" + "PM:" + department.get("PM")  
							+ "\r\n" + "SU:" + department.get("SU") 
							+ "\r\n" + "PE:" + department.get("PE") 
							+ "\r\n" + "ME:" + department.get("ME") 
							+ "\r\n" + "PL:" + department.get("PL") 
							+ "\r\n" + "QA:" + department.get("QA") 
							+ "\r\n" + "VSC:" + department.get("VSC") 
							+ "\r\n" + "LO" + department.get("LO")); 
					
					//�������
					Map<String,Object> partType = PartTypeStatusLoader.load(issuelist);
					System.out.println("������ͣ�"  
							+ "\r\n" + "���״̬��" + partType.get("partStatus") 
							+ "\r\n" + "�����ߴ磺" + partType.get("bodySize") 
							+ "\r\n" + "���ƥ�䣺" + partType.get("partMatch") 
							+ "\r\n" + "PDM��" + partType.get("pdm")
							+ "\r\n" + "�ṹ��" + partType.get("structure")
							+ "\r\n" + "���������" + partType.get("quality")
							+ "\r\n" + "��ϸ����" + partType.get("detail")
							+ "\r\n" + "������" + partType.get("other")); 
				    
					//רҵ��
//					Map<String,Object> mMvalues = MajorLoader.load(issuelist);
//					System.out.println(Messages.major
//							+ "\r\n" + Messages.mm + mMvalues.get("car")  
//							+ "\r\n" + Messages.eqipment + mMvalues.get("accoutrement") 
//							+ "\r\n" + Messages.carbody + mMvalues.get("bodywork") 
//							+ "\r\n" + Messages.electronic + mMvalues.get("eleAppliances") 
//							+ "\r\n" + Messages.chassis + mMvalues.get("chassis") 
//							+ "\r\n" + Messages.total + mMvalues.get("total")); 

					//��ʱ��ͳ��
//					Map<String, Object> TimeIssues = TimeLoader.load(issuelist);
//					
					//װ��λ��
//					Map<String, Object> assPlacement = AssPlacementLoader.load(issuelist);
//					System.out.println(Messages.assplacement
//							+ "\r\n" + Messages.ReportIssueLoader_7 + assPlacement.get("front")  
//							+ "\r\n" + Messages.behind + assPlacement.get("behind") 
//							+ "\r\n" + Messages.door + assPlacement.get("door") 
//							+ "\r\n" + Messages.inner + assPlacement.get("inner") 
//							+ "\r\n" + Messages.chassis2 + assPlacement.get("chassis") 
//							+ "\r\n" + Messages.driver + assPlacement.get("driver") 
//							+ "\r\n" + Messages.electronic2 + assPlacement.get("electronik")); 
					
					values.put("issues", issuelist); 
					values.put("department", department); 
					values.put("partType", partType); 
//					values.put("mMvalues", mMvalues); 
//					values.put("TimeIssues", TimeIssues); 
//					values.put("assPlacement", assPlacement); 
					
					System.out.println("��ȡ���ݽ���"); 
					
					return values;
				}
				return null;
				
			} else {
				
				System.out.println("ϵͳ�в���������"); 
				return null;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new FawvmLoaderException(e.getMessage());
			
		}

	}
	
	
	
}