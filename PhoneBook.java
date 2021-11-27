/*
 * Phone Book by garpozir@gmail.com
 * 
 */

import java.util.Scanner;
import java.util.Vector;
import java.util.HashSet;
import java.util.Iterator;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;



class PhoneInfo implements Serializable
{
	String name;
	String phoneNumber;
	
	public PhoneInfo(String name, String num)
	{
		this.name = name;
		phoneNumber = num;
	}
	
	public void showPhoneInfo()
	{
		System.out.println("اسم: "+name);
		System.out.println("تلفن: "+phoneNumber);
	}
	
	public String toString()
	{
		return "اسم: "+name+'\n'+"تلفن: "+phoneNumber+'\n';
	}
	
	public int hashCode()
	{
		return name.hashCode();
	}
	
	public boolean equals(Object obj)
	{
		PhoneInfo cmp = (PhoneInfo)obj;
		if(name.compareTo(cmp.name) == 0)
			return true;
		else
			return false;
	}
}

class PhoneUnivInfo extends PhoneInfo
{
	String major;
	int year;	
	
	public PhoneUnivInfo(String name, String num, String major, int year)
	{
		super(name, num);
		this.major=major;
		this.year=year;
	}
	
	public void showPhoneInfo()
	{
		super.showPhoneInfo();
		System.out.println("گروه: "+major);
		System.out.println("سال: "+year);
	}
	
	public String toString()
	{
		return super.toString()
		    +"گروه: "+major+'\n'+"سال: "+year+'\n';
	}
}

class PhoneCompanyInfo extends PhoneInfo
{
	String company;
	
	public PhoneCompanyInfo(String name, String num, String company)
	{
		super(name, num);
		this.company = company;
	}
	
	public void showPhoneInfo()
	{
		super.showPhoneInfo();
		System.out.println("شرکت: "+company);
	}
	
	public String toString()
	{
		return super.toString()
		    +"شرکت: "+company+'\n';
	}
}

class PhoneBookManager
{
	private final File dataFile = new File("PhoneBook.dat");
	
	HashSet<PhoneInfo> infoStorage = new HashSet<PhoneInfo>();
	
	static PhoneBookManager inst = null;
	public static PhoneBookManager createManagerInst()
	{
		if(inst == null)
			inst = new PhoneBookManager();
		return inst;
	}
	
	private PhoneBookManager()
	{
		readFromFile();
	}

	public String searchData(String name)
	{
		PhoneInfo info = search(name);
		if(info == null)
			return null;
		else
			return info.toString();
	}
	
	public boolean deleteData(String name)
	{	
		Iterator<PhoneInfo> itr=infoStorage.iterator();
		while(itr.hasNext())
		{
			PhoneInfo curInfo=itr.next();
			if(name.compareTo(curInfo.name) == 0)
			{
				itr.remove();
				return true;
			}
		}
		return false;
	}
	
	private PhoneInfo search(String name)
	{
		Iterator<PhoneInfo> itr = infoStorage.iterator();
		while(itr.hasNext())
		{
			PhoneInfo curInfo=itr.next();
			if(name.compareTo(curInfo.name) == 0)
				return curInfo;
		}
		return null;
	}
	
	public void storeToFile()
	{
		try
		{
			FileOutputStream file = new FileOutputStream(dataFile);		
			ObjectOutputStream out = new ObjectOutputStream(file);
			Iterator<PhoneInfo> itr=infoStorage.iterator();
			while(itr.hasNext())
				out.writeObject(itr.next());			
			out.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void readFromFile()
	{
		if(dataFile.exists() == false)
			return;
		try
		{
			FileInputStream file = new FileInputStream(dataFile);		
			ObjectInputStream in = new ObjectInputStream(file);
			while(true)
			{
				PhoneInfo info = (PhoneInfo)in.readObject();
				if(info == null)
					break;
				infoStorage.add(info);
			}
			in.close();
		}
		catch(IOException e)
		{
			return;
		}
		catch(ClassNotFoundException e)
		{
			return;
		}
	}
}

class SearchEventHandler implements ActionListener
{
	JTextField searchField;
	JTextArea textArea;
	
	public SearchEventHandler(JTextField field, JTextArea area)
	{
		searchField=field;
		textArea=area;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String name = searchField.getText();
		if (name.equals("") == true){
	textArea.append("اسم مخاطب را در مقدار جستجو وارد کنید\n"+"---------\n");
	return;
}
		PhoneBookManager manager=PhoneBookManager.createManagerInst();
		String srchResult = manager.searchData(name);
		if(srchResult == null)
		{assert true;
			//textArea.append("چیزی پیدا نشد\n");
		}
		else
		{assert true;
			//textArea.append("یه چیزی پیدا شد\n");
			//textArea.append(srchResult);
			//textArea.append("\n");
		}

		String fileName = "db.dbl";
        String line = null;
                StringBuffer inputBuffer = new StringBuffer();
       String textFieldValue = name;
       //String textFieldValuee = phone.getText();
	   boolean areYouBroke2 = true;
	   	   boolean areYouBroke = true;


 try {

            FileReader fr = new FileReader(fileName);

            BufferedReader br = new BufferedReader(fr);
int lineNum = 0;
while( (line = br.readLine() ) != null ) {
	//System.out.println(lineNum+"+"+line);

if (line.equals(textFieldValue)) {
areYouBroke2 = false;
}
}
  br.close();
FileReader fr2 = new FileReader(fileName);

            BufferedReader br2 = new BufferedReader(fr2);

if (areYouBroke2 == true){
	
textArea.append("چیزی پیدا نشد\n"+"---------\n");}else{
textArea.append("پیدا شد\n");
/////inja
while( (line = br2.readLine() ) != null ) {
if (areYouBroke == false){
textArea.append("شماره تلفن "+line+"\n"+"---------\n");

break;
}

if (areYouBroke == true){

if (line.equals(textFieldValue)) {
areYouBroke = false;
textArea.append("اسم "+textFieldValue+"\n");

}
}
}
}

}
        catch(IOException ex) {
            System.out.println(" ");
        }
////////asliiiiiiiiiiii
	}
}

class AddEventHandler implements ActionListener
{
	JTextField name;
	JTextField phone;
	JTextField major;
	JTextField year;
	JTextField company;
	JTextArea text;
	Vector<String> inputList = new Vector<String>();
	
	boolean isAdded;
	
	PhoneInfo info;
	public AddEventHandler(JTextField nameField, JTextField phoneField, JTextField majorField, JTextField yearField, JTextArea textArea)
	{
		name = nameField;
		phone = phoneField;
		major = majorField;
		year = yearField;
		text = textArea;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		PhoneBookManager manager = PhoneBookManager.createManagerInst();
		if(major.getText().equals("") == false && year.getText().equals("") == true)
		{
			company = major;
			info = new PhoneCompanyInfo(name.getText(), phone.getText(), company.getText());
			isAdded = manager.infoStorage.add(info);
			
		}		
		else if(major.getText().equals("") == false && year.getText().equals("") == false)
		{
			info = new PhoneUnivInfo(name.getText(), phone.getText(), major.getText(), Integer.parseInt(year.getText()));
			isAdded = manager.infoStorage.add(info);
			
			
			
		}
		else
		{
			info = new PhoneInfo(name.getText(), phone.getText());
			isAdded = manager.infoStorage.add(info);
			
		}
		
		
		
			
			//shooroooooooooooooooooooo
			String fileName = "db.dbl";
        String line = null;
                StringBuffer inputBuffer = new StringBuffer();
       String textFieldValue = name.getText();
       String textFieldValuee = phone.getText();
       //System.out.println("----------------------------------\n" + textFieldValuee);
if (textFieldValue.equals("") == true | textFieldValuee.equals("") == true){
	text.append("اسم و شماره تلفن را وارد کنید\n"+"---------\n");
	return;
}

boolean areYouBroke = true;
boolean areYouBroke2 = true;

        try {

            FileReader fr = new FileReader(fileName);

            BufferedReader br = new BufferedReader(fr);
int lineNum = 0;
//System.out.println("+"+br.readLine());
while( (line = br.readLine() ) != null ) {
	//System.out.println(lineNum+"+"+line);

if (line.equals(textFieldValue)) {
areYouBroke2 = false;
}
}
  br.close();

FileReader fr2 = new FileReader(fileName);

            BufferedReader br2 = new BufferedReader(fr2);

if (areYouBroke2 == true){
	
text.append(textFieldValue+" "+"ثبت شد\n"+"---------\n");
			//FileWriter myWriter = new FileWriter("db.dbl", true);


try {
                             FileWriter myWriter = new FileWriter("db.dbl", true);
                                      // if(isAdded){
      myWriter.write(name.getText()+"\n"+phone.getText()+"\n"+"##"+"\n");//}
      myWriter.close();
      } catch (IOException ex) {
      System.out.println(" ");
      
    }




	  
}else{


               



text.append("شماره تلفن"+" "+textFieldValue+" "+"ویرایش شد\n"+"---------\n");

            while( (line = br2.readLine() ) != null ) {
				//System.out.println("+"+inputBuffer);
            lineNum++;
            //System.out.println(areYouBroke);
             if (areYouBroke == false){
             //System.out.println("+"+areYouBroke);
             areYouBroke = true;
             //System.out.println("="+areYouBroke);
             inputBuffer.append(textFieldValuee+"\n");
             }
             else{
             
             if (line.equals(textFieldValue)) {
              areYouBroke = false;
             // System.out.println(lineNum);
             inputBuffer.append(line+"\n");
  // block of code to be executed if the condition is true
} else {
inputBuffer.append(line+"\n");

  // block of code to be executed if the condition is false
}
}

                      
            }

            br.close();
//
String inputStr = inputBuffer.toString();
FileWriter myWriterp = new FileWriter("db.dbl");
       myWriterp.write(inputStr);
      myWriterp.close();}
//
        }
        catch(IOException ex) {
            System.out.println(" ");
        }
		
			//asli
		
	}
}

class adel implements ActionListener
{
	JTextArea textArea;

	public adel( JTextArea area)
	{
		textArea = area;
	}

	public void actionPerformed(ActionEvent e)
	{
///////////////////////////////innnnnnnnn
try {

String strpath="db.dbl";
FileReader fr = new FileReader(strpath);
BufferedReader br = new BufferedReader(fr);
String ch;
int time=0;
String Conversion="";
ArrayList<String> tmp = new ArrayList<String>();

    do {
        ch = br.readLine();
        tmp.add(ch);
        //System.out.println(ch); 
    } while (ch != null);

    for(int i=tmp.size()-1;i>=0;i--) {
        //System.out.println(tmp.get(i));
		if (tmp.get(i)!=null) { 
			if (tmp.get(i).equals("##")) {textArea.append("#\n");}else{
		textArea.append(tmp.get(i)+"\n");}
		}

    }
		textArea.append("---------\n");

fr.close();

} catch (IOException ex) {
      System.out.println(" ");
      
    }  
	///////////////////////////////innnnnnnnn

	}
}




class DeleteEventHandler implements ActionListener
{
	JTextField delField;
	JTextArea textArea;

	public DeleteEventHandler(JTextField field, JTextArea area)
	{
		delField = field;
		textArea = area;
	}

	public void actionPerformed(ActionEvent e)
	{
		String name = delField.getText();
		            //System.out.println(name);
if (name.equals("") == true){
	textArea.append("اسم مخاطب را در مقدار حذف وارد کنید\n"+"---------\n");
	return;
}
		PhoneBookManager manager = PhoneBookManager.createManagerInst();
		boolean isDeleted = manager.deleteData(name);
		if(isDeleted)
			//textArea.append("حذف انجام شد\n");
			assert true;
		else
			//textArea.append("چیزی حذف نشد\n");
			assert true;
String fileName = "db.dbl";
        String line = null;
                StringBuffer inputBuffer = new StringBuffer();
       String textFieldValue = name;
       //String textFieldValuee = phone.getText();
	   boolean areYouBroke2 = true;
	   	   boolean areYouBroke = true;

String[] parts = textFieldValue.split("\\*");



//System.out.println(part1);


 try {

for (int i = 0; i < parts.length; i++) {
String part1 = parts[i];

            FileReader fr = new FileReader(fileName);

            BufferedReader br = new BufferedReader(fr);
int lineNum = 0;
while( (line = br.readLine() ) != null ) {
	//System.out.println(lineNum+"+"+line);

if (line.equals(part1)) {
areYouBroke2 = false;
break;
}
}
  br.close();
FileReader fr2 = new FileReader(fileName);

            BufferedReader br2 = new BufferedReader(fr2);

if (areYouBroke2 == true){
	
textArea.append("چیزی برای حذف پیدا نشد\n"+"---------\n");}else{
/////inja
while( (line = br2.readLine() ) != null ) {





if (line.equals(part1)) {
areYouBroke = false;
textArea.append("مخاطب "+part1+" "+"حذف شد "+"\n"+"---------\n");
br2.readLine();
br2.readLine();
}else{
inputBuffer.append(line+"\n");}

}
//

br2.close();
String inputStr = inputBuffer.toString();
FileWriter myWriterp = new FileWriter("db.dbl");
       myWriterp.write(inputStr);
      myWriterp.close();
	  inputBuffer.setLength(0);

}

}
}
        catch(IOException ex) {
            System.out.println(" ");
        }

			//////asliiiiiiiiiiiiiii
	}
}

class MainFrame extends JFrame
{
	JTextField srchField = new JTextField(15);
	JButton srchBtn = new JButton("جستجو");
	
	JButton addBtn = new JButton("ثبت/ویرایش");
		JButton adelBtn = new JButton("معکوس کردن");

	JRadioButton rbtn1 = new JRadioButton("عمومی");
	JRadioButton rbtn2 = new JRadioButton("دانشگاه");
	JRadioButton rbtn3 = new JRadioButton("شرکت");
	ButtonGroup buttonGroup = new ButtonGroup();
	
	JLabel nameLabel = new JLabel("نام و نام خانوادگی");
	JTextField nameField = new JTextField(15);
	JLabel phoneLabel = new JLabel("شماره تلفن");
	JTextField phoneField = new JTextField(15);
	JLabel majorLabel = new JLabel("گروه");
	JTextField majorField = new JTextField(15);
	JLabel yearLabel = new JLabel("سال");
	JTextField yearField = new JTextField(15);
		
	JTextField delField = new JTextField(15);
	JButton delBtn = new JButton("حذف");

		//String tooltipText = delBtn.getToolTipText();
	

        


	JTextArea textArea = new JTextArea("**********"+"خوش آمدید"+"**********\n"+"---------\n");



	



	public MainFrame(String title)
	{
		super(title);
		setBounds(100, 200, 330, 450);
		setSize(730,350);
		setLayout(new GridLayout(0,2,0,0));
		Border border = BorderFactory.createEtchedBorder();
		
		Border srchBorder = BorderFactory.createTitledBorder(border, "جستجو");
		JPanel srchPanel = new JPanel();
		srchPanel.setBorder(srchBorder);
		srchPanel.setLayout(new FlowLayout());
		srchPanel.add(srchField);
		srchPanel.add(srchBtn);
		
		Border addBorder=BorderFactory.createTitledBorder(border, "ثبت/ویرایش");
		JPanel addPanel = new JPanel();
		addPanel.setBorder(addBorder);
		addPanel.setLayout(new FlowLayout());
		
		JPanel addInputPanel = new JPanel();
		addInputPanel.setLayout(new GridLayout(0,2,5,5));
		
		buttonGroup.add(rbtn1);
		buttonGroup.add(rbtn2);
		buttonGroup.add(rbtn3);
		
		addPanel.add(rbtn1);
		addPanel.add(rbtn2);
		addPanel.add(rbtn3);
		addPanel.add(addBtn);
				addPanel.add(adelBtn);

		
		addInputPanel.add(nameLabel);
		addInputPanel.add(nameField);
		addInputPanel.add(phoneLabel);
		addInputPanel.add(phoneField);
		addInputPanel.add(majorLabel);
		addInputPanel.add(majorField);
		addInputPanel.add(yearLabel);
		addInputPanel.add(yearField);
		
		majorLabel.setVisible(false);
		majorField.setVisible(false);
		yearLabel.setVisible(false);
		yearField.setVisible(false);
		
		rbtn1.setSelected(true);
		addPanel.add(addInputPanel);
		
		rbtn1.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(e.getStateChange() == ItemEvent.SELECTED)
						{
							majorLabel.setVisible(false);
							
							majorField.setVisible(false);
							yearLabel.setVisible(false);
							yearField.setVisible(false);			
							majorField.setText("");
							yearField.setText("");
						}
					}
				}
		);
		
		rbtn2.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(e.getStateChange() == ItemEvent.SELECTED)
						{
							majorLabel.setVisible(false);
							majorLabel.setText("گروه");
							majorField.setVisible(false);
							yearLabel.setVisible(false);
							yearField.setVisible(false);
						}
					}
				}
		);
		
		rbtn3.addItemListener(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(e.getStateChange() == ItemEvent.SELECTED)
						{
							majorLabel.setVisible(false);
							majorLabel.setText("شرکت");
							majorField.setVisible(false);
							yearLabel.setVisible(false);
							yearField.setVisible(false);
							yearField.setText("");
						}
					}
				}
		);
		
		Border delBorder = BorderFactory.createTitledBorder(border, "حذف");
		JPanel delPanel = new JPanel();
		delPanel.setBorder(delBorder);
		delPanel.setLayout(new FlowLayout());
		delPanel.add(delField);
			delField.setToolTipText("برای حذف چند مخاطب آنها را با * از هم جدا کنید");

		delPanel.add(delBtn);
		
		JScrollPane scrollTextArea = new JScrollPane(textArea);	
		Border textBorder=BorderFactory.createTitledBorder(border, "اطلاعات");
		scrollTextArea.setBorder(textBorder);
		
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new BorderLayout());
		actionPanel.add(srchPanel, BorderLayout.NORTH);
		actionPanel.add(addPanel, BorderLayout.CENTER);
		actionPanel.add(delPanel, BorderLayout.SOUTH);
		
		add(actionPanel);
		add(scrollTextArea);
		
		srchBtn.addActionListener(new SearchEventHandler(srchField, textArea));
		addBtn.addActionListener(new AddEventHandler(nameField, phoneField, majorField, yearField, textArea));
		delBtn.addActionListener(new DeleteEventHandler(delField, textArea));
		adelBtn.addActionListener(new adel( textArea));

		rbtn2.setVisible(false);
							rbtn3.setVisible(false);
							
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);	
//akar
 /*
try {
String fileNamea = "db.dbl";
        String linea = null;
                StringBuffer inputBuffer2 = new StringBuffer();

FileReader fr20 = new FileReader(fileNamea);

            BufferedReader br20 = new BufferedReader(fr20);
while( (linea = br20.readLine() ) != null ) {
inputBuffer2.append(linea+"\n");
      System.out.println(inputBuffer2);

}
  br20.close();
  } catch (IOException ex) {
      System.out.println(" ");
      
    }   
*/



  




//akar
boolean areYouBroke4 = true;
String line = null;
int num = 0;
        try {

            FileReader fr = new FileReader("db.dbl");

            BufferedReader br = new BufferedReader(fr);


while( (line = br.readLine() ) != null ) {
areYouBroke4 = false;
num++;
if (line.equals("##")) 
{textArea.append("***************\n");
}else{
if (num == 1 | (num-1)%3==0)
{textArea.append("اسم"+" "+line+"\n");}
else{textArea.append("شماره تلفن "+line+"\n");}
}}
  br.close();
               if (areYouBroke4 == false){
textArea.append("---------\n");}

      } catch (IOException ex) {
      System.out.println(" ");
      
    }    


	}
}

class PhoneBook
{	
	public static void main(String[] args)
	{
		PhoneBookManager manager = PhoneBookManager.createManagerInst();
		MainFrame winFrame = new MainFrame("دفترچه تلفن حرفه ای");
	
	}
}

