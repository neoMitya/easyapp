package com.saturn.ph.views.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.teamcenter.rac.common.TCTypeRenderer;
import com.teamcenter.rac.common.lov.LOVUIComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentListOfValues;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.DateButton;
import com.teamcenter.rac.util.FilterDocument;
import com.teamcenter.rac.util.iTextArea;
import com.teamcenter.rac.util.iTextField;

public class SelectPropertyTableCellEditor extends AbstractCellEditor
implements TableCellEditor
{
	/** table model */
	protected SelectTableModel tm;
	/** text filed cell editor */
	protected iTextArea textField;
	/** filter document for text filed */
	protected FilterDocument document;
	/** LOVUIComponent for property has relation lov */
	protected LOVUIComponent lovUIComponent;
	/** date button */
	protected DateButton dateButton;
	/** JComboBox for logical property */
	protected JComboBox logicalSelection;
	/** label */
	protected JLabel label;
	
	protected JCheckBox checkBox;
	
	protected JScrollPane scrollPane;
	/**
	 *  ArrayPropertyTableCellEditor construct
	 * @param session	IMAN session
	 * @param table	table
	 */
    public SelectPropertyTableCellEditor(TCSession session,JTable table)
    {
    	tm = (SelectTableModel)table.getModel();
        String encoding =
            TCSession.getServerEncodingName(session);
        //initialize common swing component
        document = new FilterDocument(32, encoding);
        textField = new iTextArea(document, "", 15, 0);
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);
        textField.setOpaque(true);
        textField.setBackground(Color.white);
        
        scrollPane = new JScrollPane(textField);
       
//        textField.addMouseListener(new MouseAdapter()
//        {
//        	public void mouseClicked(MouseEvent evt)
//        	{
//        		mouseEvent(evt);
//        	}
//        });
        //lovUIComponent = new LOVUIComponent();
        dateButton = new DateButton();
        logicalSelection =
           new JComboBox(new Object[] { Boolean.TRUE, Boolean.FALSE });
        label = new JLabel();

        checkBox = new JCheckBox();
        checkBox.setOpaque(false);
    }

    /** current select culumn */
    protected ArrayPropertyTableColumn currentColumn = null;
    
   /**
    *  get cell editor of  specific row and column
	* @param table	table
	* @param value	cell value
	* @param selected	is table cell selected
	* @param row	select row
	* @param col	select column
	* @return cell editor
    */
   public Component getTableCellEditorComponent(
      JTable table,
      Object value,
      boolean selected,
      int row,
      int col)
   {
	   int factcol = table.convertColumnIndexToModel(col);

	   if(factcol > 0)
		   currentColumn = tm.getColumn(factcol);
	   else
		   currentColumn = null;
	   	   
       String s = value == null ? "" : value.toString();
       
       
       if(currentColumn == null)
       {
		   if(value.toString().equalsIgnoreCase("Y"))
			   checkBox.setSelected(true);
		   else
			   checkBox.setSelected(false);

		   return checkBox;
       }

       //if column is not exist, return label
       
       
       try
       {
    	   //get lov of property if property relate lov 
           TCComponentListOfValues lov = currentColumn.getProperty().getLOV();
         if (lov != null)
         {
            lovUIComponent =
               new LOVUIComponent(
                  lov,
                  s,
                  -1,
                  false);
            lovUIComponent.setSelectedValue(value);
            lovUIComponent.setText(s);
            return lovUIComponent;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      //for each column set default swing component editor
      switch (currentColumn.getColumnPropertyType())
      {
         case 1 :
            document.setAcceptedChars(null);
            document.setLength(1);
            textField.setText(s);
            return scrollPane;
         case 2 :
            dateButton.setDate((Date) value);
            return dateButton;
         case 3 :
         case 4 :
            document.setAcceptedChars("0123456789.eE");
            textField.setText(s);
            return scrollPane;
         case 5 :
            document.setAcceptedChars("9876543210");
            textField.setText(s);
            return scrollPane;
         case 6 :
            logicalSelection.setSelectedItem((Boolean) value);
            return logicalSelection;
         case 12 :
            document.setAcceptedChars(null);
            document.setLength(
               currentColumn
                  .getProperty()
                  .getPropertyDescriptor()
                  .getMaxStringLength());
            textField.setText(s);
            return scrollPane;
         case 7 :
            document.setAcceptedChars("1023456789");
            textField.setText(s);
            return scrollPane;
         case 8 :
            document.setAcceptedChars(null);
            document.setLength(
               currentColumn
                  .getProperty()
                  .getPropertyDescriptor()
                  .getMaxStringLength());
            textField.setText(s);
            return scrollPane;
         case 9 :
         case 10 :
         case 11 :
         case 13 :
         case 14 :
            label.setText(s);
            if (value instanceof TCComponent)
            {
               label.setIcon(
                  TCTypeRenderer.getIcon((TCComponent) value, false));
            }
            return label;
      }
      return null;
   }

   /**
    * get value of cell editor
    * @return	value
    */
   public Object getCellEditorValue()
   {
      TCComponentListOfValues lov = null;
      if(currentColumn == null)
      {
    	  if(checkBox.isSelected())
    	  {
    		  return "Y";
    	  }
    	  else
    		  return "N";
      }
      try
      {
    	  //get lov of property if property relate lov 
         lov = currentColumn.getProperty().getLOV();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      Object obj = null;
      String s;
      if (lov != null)
      {
         obj = lovUIComponent.getValue();
         s = obj == null ? "" : obj.toString();
      }
      else
      {
         s = textField.getText();
      }
      switch (currentColumn.getColumnPropertyType())
      {
         case 1 :
            if (s != null && s.length() > 0)
               return new Character(s.charAt(0));
            return new Character('?');
         case 2 :
            return dateButton.getDate();
         case 3 :
            if (s != null && s.length() > 0)
               return new Double(Double.parseDouble(s));
            return new Double(0.0D);
         case 4 :
            if (s != null && s.length() > 0)
               return new Float(Float.parseFloat(s));
            return new Float(0.0);
         case 5 :
            if (s != null && s.length() > 0)
               return new Integer(Integer.parseInt(s));
            return new Integer(0);
         case 6 :
            return logicalSelection.getSelectedItem();
         case 12 :
            return s;
         case 7 :
            if (s != null && s.length() > 0)
               return new Short(Short.parseShort(s));
            return new Short((short) 0);
         case 8 :
            return s;
      }
      return null;
   }

   /**
    *  is celll editable
    *  @param evt	cell editor 's event
    */
   public boolean isCellEditable(EventObject evt)
   {
      if (!super.isCellEditable(evt))
         return false;
      
      if(evt.getSource() instanceof JCheckBox)
      {
    	  return true;
      }
      
      if (evt != null && evt instanceof MouseEvent)
         return ((MouseEvent) evt).getClickCount() >= 2;
      return true;
   }
   
//   public void mouseEvent(MouseEvent evt)
//   {
//		int cc = evt.getClickCount();
//		//if(textField.get)
//		System.out.println("mousce click diss");
//		if(cc >= 2)
//		{
//			System.out.println("mousce click diss  000 1");
//			popupEditor.display();
//		}
//   }
//   
}