import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class MainFrame extends JFrame{

	/**
	 * 
	 *
	 * javac -cp jdom-2.0.6.jar MainFrame.java -> is to compile 
	 * java -cp jdom-2.0.6.jar:. MainFrame -> is to run
	 * Last updated May 4, 2015
	 * Modified by Harshavardhan Pasala(hp279 - 31362949)
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private AbstractAction quitAction;
	private AbstractAction newAction;
	private AbstractAction openAction;
	private AbstractAction openURLAction;
	private AbstractAction saveAction;
	private AbstractAction saveAsAction;
	private AbstractAction undoAction;
	private AbstractAction redoAction;
	private AbstractAction cutAction;
	private AbstractAction copyAction;
	private AbstractAction pasteAction;
	private AbstractAction attributeAction;
	private AbstractAction elementAction;
	private AbstractAction textAction;
	private AbstractAction aboutAction;
	private JTree jRoot;
	private DefaultMutableTreeNode treeNode;
	private JLabel statusLabel;
	private Stack<String> undoStack;
	private Stack<String> redoStack;
	private DefaultMutableTreeNode copyNode;
	private DefaultMutableTreeNode pasteNode;
	private File inputFile;
	private JFileChooser fileChooser;
	private boolean isFileEdited;
	
	class NewAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performNewAction();
		}
		public NewAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class OpenAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performOpenAction();
		}
		public OpenAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class OpenURLAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performOpenURLAction();
		}
		public OpenURLAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class SaveAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performSaveAction();
		}
		public SaveAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class SaveAsAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performSaveAsAction();
		}
		public SaveAsAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class QuitAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performQuitAction();
		}
		public QuitAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class UndoAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performUndoAction();
		}
		public UndoAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class RedoAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performRedoAction();
		}
		public RedoAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	/*class EditAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performEditAction();
		}
		public EditAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}*/
	class CutAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performCutAction();
		}
		public CutAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class CopyAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performCopyAction();
		}
		public CopyAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class PasteAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performPasteAction();
		}
		public PasteAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class AttributeAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performAttributeAction();
		}
		public AttributeAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class ElementAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performElementAction();
		}
		public ElementAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class TextAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performTextAction();
		}
		public TextAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	class AboutAction extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			performAboutAction();
		}
		public AboutAction(String name,ImageIcon i, String description) {
			super(name,i);
            putValue(SHORT_DESCRIPTION, description);
		}
	}
	
	private boolean getFileEditedStatus(){
		return isFileEdited;
	}
	
	private void setFileEditedStatus(boolean isFileEdited){
		this.isFileEdited=isFileEdited;
	}
	
	private void setFile(File inputFile) {
		// TODO Auto-generated method stub
		/*JFileChooser fileChooser = new JFileChooser(".");
		fileChooser.setFileFilter(new FileNameExtensionFilter("XML Files(*.xml)", "xml"));
		//setFile();
		if(isFileOpened == true)
			fileChooser.showSaveDialog(null);
		else
			fileChooser.showOpenDialog(null);*/
		this.inputFile = inputFile;//fileChooser.getSelectedFile();
		return;
	}
	private File getFile() {
		// TODO Auto-generated method stub
		//if(this.inputFile == null)
		//	setFile();
		return this.inputFile;
	}
	private String getElement(String i){
		String p = i.substring(("[Element: <").length());
		return p.substring(0, p.length() - ("/>]").length()); 
	}
	private String getAttr(String i){
		String p = i.substring(("[Attribute:").length());
		return p.substring(0, p.length() - ("]").length()); 
	}
	private String getText(String i){
		String p = i.substring(("[Text:").length());
		return p.substring(0, p.length() - ("]").length()); 
		//return i.replace("Element","").replace("Text", "").replace("Attribute","").replace("[", "").replace(":", "").replace("]", "").replace("<", "").replace(">", "").replace("/", "").replace("  ", " ");
		//return i.replace("Text", "").replace("[", "").replace("]", "").replace("<", "").replace(">", "").replace("/", "").replace(":", "").trim();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}
	
	private MainFrame(){
		super("XML Editor");
		inputFile = null;
		resetUndoRedoStack();
		copyNode = pasteNode = null;
		quitAction = null;
		newAction = null;
		openAction = null;
		saveAction = null;
		saveAsAction = null;
		undoAction = null;
		redoAction = null;
		cutAction = null;
		copyAction = null;
		pasteAction = null;
		attributeAction = null;
		elementAction = null;
		textAction = null;
		aboutAction = null;
		fileChooser = new JFileChooser(".");
		fileChooser.setFileFilter(new FileNameExtensionFilter("XML Files(*.xml)", "xml"));
		isFileEdited = false;
		launch();
	}
	
	
	private void launch(){
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		setBounds(width/8, height/8, width*3/4, height*3/4);
		setVisible(true);
		setJMenuBar(createMenuBar());
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
		add(createJToolBar(),BorderLayout.NORTH);
		statusLabel = new JLabel("");
		add(statusLabel,BorderLayout.SOUTH);
		updateHint("Welcome To XML Editor(Press F1 for help.)");
		
		treeNode = new DefaultMutableTreeNode("[Element: <RootNode/>]");
		
		jRoot = new JTree(treeNode);
		jRoot.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jRoot.setEditable(true);
		
		JScrollPane jsp = new JScrollPane(jRoot);
		add(jsp);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				performQuitAction();
			}
		});
	
	}
	
	private void updateHint(String s){
		statusLabel.setText(s);
	}
	private JToolBar createJToolBar(){
		JToolBar jtb = new JToolBar();
		jtb.add(createNewAction());
		jtb.add(createOpenAction());
		jtb.addSeparator(); 
		jtb.addSeparator(); 
		jtb.add(createSaveAction());
		jtb.addSeparator(); 
		jtb.addSeparator(); 
		jtb.add(createUndoAction());
		jtb.add(createRedoAction());
		jtb.addSeparator(); 
		jtb.addSeparator(); 
		jtb.add(createCutAction());
		jtb.add(createCopyAction());
		jtb.add(createPasteAction());
		jtb.addSeparator(); 
		jtb.addSeparator(); 
		jtb.add(createAttributeAction());
		jtb.add(createElementAction());
		jtb.add(createTextAction());
		jtb.addSeparator(); 
		jtb.addSeparator(); 
		jtb.add(createQuitAction());
		jtb.addSeparator();

		return jtb;
	}
	private JMenuBar createMenuBar() {
		JMenuBar jmb = new JMenuBar();
		
		jmb.add(createFileJMenu());
		jmb.add(createEditJMenu());
		jmb.add(createHelpJMenu());
		
		
		return jmb;
	}
	
	private JMenu createHelpJMenu() {
		JMenu jm = new JMenu("Help");
		jm.add(createAboutJMenuItem());
		return jm;
	}
	private JMenu createEditJMenu() {
		JMenu jm = new JMenu("Edit");
		//jm.setMnemonic(KeyEvent.VK_E);
		
		jm.add(createEditUndoItem());//new JMenuItem("Undo"));
		jm.add(createEditRedoItem());//new JMenuItem("Redo"));
		jm.add(createEditCutItem());//new JMenuItem("Cut"));
		jm.add(createEditCopyItem());//new JMenuItem("Copy"));
		jm.add(createEditPasteItem());//new JMenuItem("Paste"));
		
		
		JMenu submenu = new JMenu("Add");
		submenu.add(createEditAttributeItem());//new JMenuItem("Attribute"));
		submenu.add(createEditElementItem());//new JMenuItem("Element"));
		submenu.add(createEditTextItem());//new JMenuItem("Text"));
		jm.add(submenu);
		return jm;
	}
	private JMenu createFileJMenu() {
		JMenu jm = new JMenu("File");
		
		jm.add(createFileNewMenuItem());//new JMenuItem("New"));
		
		JMenu openMenu = new JMenu("Open");
		openMenu.add(createFileOpenMenuItem());//new JMenuItem("Open File"));
		openMenu.add(createURLOpenMenuItem());//new JMenuItem("Open URL"));
		jm.add(openMenu);
		
		jm.add(createFileSaveMenuItem());//new JMenuItem("Save"));
		jm.add(createFileSaveAsMenuItem());//new JMenuItem("Save As"));
		jm.add(createFileQuitJMenuItem());//new JMenuItem("Quit"));
		return jm;
	}
	
	private JMenuItem createFileNewMenuItem() {
		JMenuItem jmi = new JMenuItem(createNewAction());
		jmi.setMnemonic(KeyEvent.VK_N);
		jmi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		return jmi;
	}
	
	private JMenuItem createFileOpenMenuItem() {
		JMenuItem jmi = new JMenuItem(createOpenAction());
		jmi.setMnemonic(KeyEvent.VK_O);
		jmi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		return jmi;
	}

	private JMenuItem createURLOpenMenuItem() {
		JMenuItem jmi = new JMenuItem(createURLAction());
		return jmi;
	}
	private JMenuItem createFileSaveMenuItem() {
		JMenuItem jmi = new JMenuItem(createSaveAction());
		jmi.setMnemonic(KeyEvent.VK_S);
		jmi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		return jmi;
	}
	private JMenuItem createFileSaveAsMenuItem() {
		JMenuItem jmi = new JMenuItem(createSaveAsAction());
		return jmi;
	}
	
	private JMenuItem createFileQuitJMenuItem() {
		JMenuItem jmi = new JMenuItem(createQuitAction());
		jmi.setMnemonic(KeyEvent.VK_Q);
		jmi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
		return jmi;
	}
	
	//edit
	private JMenuItem createEditUndoItem(){
		JMenuItem jmi = new JMenuItem(createUndoAction());
		jmi.setMnemonic(KeyEvent.VK_Z);
		jmi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
		return jmi;
	}
	
	private JMenuItem createEditRedoItem(){
		JMenuItem jmi = new JMenuItem(createRedoAction());
		jmi.setMnemonic(KeyEvent.VK_Y);
		jmi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
		return jmi;
	}
	private JMenuItem createEditCutItem(){
		JMenuItem jmi = new JMenuItem(createCutAction());
		jmi.setMnemonic(KeyEvent.VK_X);
		jmi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
		return jmi;
	}
	private JMenuItem createEditCopyItem(){
		JMenuItem jmi = new JMenuItem(createCopyAction());
		jmi.setMnemonic(KeyEvent.VK_C);
		jmi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
		return jmi;
	}
	private JMenuItem createEditPasteItem(){
		JMenuItem jmi = new JMenuItem(createPasteAction());
		jmi.setMnemonic(KeyEvent.VK_V);
		jmi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
		return jmi;
	}
	private JMenuItem createEditAttributeItem(){
		JMenuItem jmi = new JMenuItem(createAttributeAction());
		return jmi;
	}
	private JMenuItem createEditElementItem(){
		JMenuItem jmi = new JMenuItem(createElementAction());
		return jmi;
	}
	private JMenuItem createEditTextItem(){
		JMenuItem jmi = new JMenuItem(createTextAction());
		return jmi;
	}
	private JMenuItem createAboutJMenuItem(){
		JMenuItem jmi = new JMenuItem(createAboutAction());
		jmi.setMnemonic(KeyEvent.VK_F1);
		jmi.setAccelerator(KeyStroke.getKeyStroke("F1"));
		return jmi;
	}
	
	private AbstractAction createNewAction() {
		if(newAction == null)
			newAction = new NewAction("New", new ImageIcon("new.png"), "New File");
		return newAction;
	}

	private AbstractAction createOpenAction(){
		if(openAction == null)
			openAction = new OpenAction("Open File", new ImageIcon("open.png"), "Open File");
		return openAction;
	}
	private AbstractAction  createURLAction(){
		if(openURLAction == null)
			openURLAction = new OpenURLAction("Open URL", null, "Open URL");
		return openURLAction;
	}
	private AbstractAction createSaveAction(){
		if(saveAction == null)
			saveAction = new SaveAction("Save", new ImageIcon("save.png"), "Save File");
		return saveAction;
	}
	private AbstractAction createSaveAsAction(){ 
		if(saveAsAction == null)
			saveAsAction = new SaveAsAction("Save As", null, "Save As");
		return saveAsAction;
		
	}
	private AbstractAction createQuitAction(){ 
		if(quitAction == null)
			quitAction = new QuitAction("Quit", null, "Quit Application");
		return quitAction;
	}
	private AbstractAction createUndoAction(){
		if(undoAction == null)
			undoAction = new UndoAction("Undo", new ImageIcon("undo.png"), "Undo Change");
		return undoAction;
	}
	private AbstractAction createRedoAction(){
		if(redoAction == null)
			redoAction = new RedoAction("Redo", new ImageIcon("redo.png"), "Redo Change");
		return redoAction;
	}
	/*private AbstractAction createEditAction(){
		if(editAction == null)
			editAction = new EditAction("Edit", null, "Edit");
		return editAction;
	}*/
	private AbstractAction createCutAction(){
		if(cutAction == null)
			cutAction = new CutAction("Cut",new ImageIcon("cut.png"),"Cut");
		return cutAction;
	}
	private AbstractAction createCopyAction(){
		if(copyAction == null)
			copyAction = new CopyAction("Copy",new ImageIcon("copy.png"),"Copy");
		return copyAction;
	}
	private AbstractAction createPasteAction(){
		if(pasteAction == null)
			pasteAction = new PasteAction("Paste",new ImageIcon("paste.png"),"Paste");
		return pasteAction;
	}
	private AbstractAction createAttributeAction(){
		if(attributeAction == null)
			attributeAction = new AttributeAction("Attribute",null,"Add Attribute");
		return attributeAction;
	}
	private AbstractAction createElementAction(){
		if(elementAction == null)
			elementAction = new ElementAction("Element",null,"Add Element");
		return elementAction;
	}
	private AbstractAction createTextAction(){
		if(textAction == null)
			textAction = new TextAction("Text",null,"Add Text");
		return textAction;
	}
	private AbstractAction createAboutAction(){
		if(aboutAction == null)
			aboutAction = new AboutAction("About",null,"About Application");
		return aboutAction;
	}
	private void buildTreeFromString(String x){
		DefaultMutableTreeNode r = (DefaultMutableTreeNode) treeNode.getRoot();
		r.setUserObject("[Element: <RootNode/>]");
		treeNode.removeAllChildren();
        SAXBuilder builder = new SAXBuilder();
        Document document = null;
        try {
        	document = builder.build(new StringReader(x));
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	updateHint("File is not in XML format. Please check before opening.");
			e.printStackTrace();
		}
        treeNode.setUserObject(document.getRootElement().toString());
        addNodeToTree(treeNode,document.getRootElement());
        jRoot.updateUI();
        expandAll(jRoot,0,jRoot.getRowCount());
	}

	private void expandAll(JTree tree, int start, int row){
		for(int i=start;i<row;++i){
	        tree.expandRow(i);
	    }
		if(tree.getRowCount()!=row){
	    	expandAll(tree,row, tree.getRowCount());
	    }
	}
	private void addNodeToTree( DefaultMutableTreeNode jroot,Element xmlElement){
		for(Attribute at : xmlElement.getAttributes())
			jroot.add(new DefaultMutableTreeNode(at));
		for (Content co : xmlElement.getContent()) {
			DefaultMutableTreeNode e;
			if (co instanceof Text) {
				
				if(((Text) co).getTextTrim()!="")
					jroot.add(new DefaultMutableTreeNode(co));
			}
			else if (co instanceof Element) {
				if(co != null){
					e=new DefaultMutableTreeNode(co);
					jroot.add(e);
					
					addNodeToTree(e,(Element)co);
				}
			}
		}
	}
	
	private void performNewAction(){
		try{
			if(getFileEditedStatus() == true)
				performSaveAction();
				
			DefaultMutableTreeNode e = (DefaultMutableTreeNode) treeNode.getRoot();
			e.setUserObject("[Element: <RootNode/>]");
			treeNode.removeAllChildren();
			jRoot.updateUI();
			resetUndoRedoStack();
			undoStack.push(getTreeText(treeNode,new Stack<String>(),new Stack<String>()));
			setFile(null);
		}catch(Exception e){
			e.printStackTrace();
		}
		setFileEditedStatus(false);
		setFile(null);
		this.setTitle("*New");
	}
	private void performOpenAction(){
		//performSaveAction();
		String text="";
		if(getFileEditedStatus() == true)
			performSaveAction();
		if (fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
			return;
		setFile(fileChooser.getSelectedFile());
		try {
			text = new Scanner(getFile()).useDelimiter("\\A").next();
			//undoStack.push(getTreeText(treeNode,new Stack<String>(),new Stack<String>()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buildTreeFromString(text);
		resetUndoRedoStack();
		//undoStack.push(getTreeText(treeNode,new Stack<String>(),new Stack<String>()));
		undoStack.pop();
		undoStack.push(getTreeText(treeNode,new Stack<String>(),new Stack<String>()));
		setFileEditedStatus(false);
		this.setTitle("XML Editor - " + getFile().getAbsolutePath());
		//setFile(null);
		//updateHint("File Opened Successfully.");
	}
	private void performOpenURLAction(){
		
		String userURL = JOptionPane.showInputDialog(null,"Enter your URL here(Ex. \"https://web.njit.edu/~hp279/java/Input2.xml\")");//"https://web.njit.edu/~hp279/java/Input2.xml";
		URL url = null;
		URLConnection conn = null;
		try {
			url = new URL(userURL);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
        
		try {
			conn = url.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
        
        Scanner scan = null;
		try {
			scan = new Scanner(new InputStreamReader(conn.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			updateHint("Please check your Internet connection or XML is not in proper format.");
			return;
		}
		String text = "";
        while (scan.hasNext()) {
        	text += scan.nextLine();
        }
        
        buildTreeFromString(text);
		resetUndoRedoStack();
		undoStack.pop();
		undoStack.push(text);
		setFileEditedStatus(true);
		setFile(null);
		this.setTitle("XML Editor - " + userURL);
	}
	private void resetUndoRedoStack(){
		undoStack = new Stack<String>();
		redoStack = new Stack<String>();
		undoStack.push("<RootNode/>");
	}
	private void performSaveAction(){
		if(getFile() == null){// || isFileEdited==true){
			performSaveAsAction();
		}
		if(getFile() == null){
			return;
		}
		if(getFileEditedStatus() == false)
			return;	
		String x = (String)getTreeText(treeNode,new Stack<String>(),new Stack<String>());
		try {
			PrintWriter pw = new PrintWriter(getFile());
			pw.write(x);
			pw.flush();
			pw.close();
			
			SAXBuilder b = new SAXBuilder();
			Document document = b.build(getFile());
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			pw = new PrintWriter(getFile());
			outputter.output(document, pw);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setTitle("XML Editor - " + getFile().getAbsolutePath());
		updateHint("Changes have been made.");
	}
	private void performSaveAsAction(){
		if(getFileEditedStatus() == false)
			return;
		if (fileChooser.showSaveDialog(null) == JFileChooser.CANCEL_OPTION)
			return;
		setFile(fileChooser.getSelectedFile());
		performSaveAction();
		updateHint("File created Successfully.");
		this.setTitle("XML Editor - " + getFile().getAbsolutePath());
	}
	@SuppressWarnings("unchecked")
	private String getTreeText(DefaultMutableTreeNode n, Stack<String> sTag,Stack<String> eTag){
		String x= "";
		String y= "",z="";
		String d = getElement(n.getUserObject().toString());
		Enumeration<DefaultMutableTreeNode> e = n.children();
		while(e.hasMoreElements()){
			DefaultMutableTreeNode k = e.nextElement();
			if(k.getUserObject() instanceof Attribute)
				x = x + getAttr(k.getUserObject().toString().trim())+" ";
			if(k.getUserObject() instanceof Text)
				y = getText(k.getUserObject().toString().trim());
		}
		x = "<" + d + x + ">" + y;
		sTag.push(x);
		eTag.push("</" + d + ">");
		e = n.children();
		while(e.hasMoreElements()){
			DefaultMutableTreeNode k = e.nextElement();
			if(k.getUserObject() instanceof Element){
				getTreeText(k,sTag,eTag);
				z = sTag.pop() + "" + eTag.pop();
				z = sTag.pop() + z;
				sTag.push(z);
			}
		}
		return (String)sTag.peek()+"" + eTag.peek();
	}
	
	private void performQuitAction(){
		if(getFileEditedStatus() == true)
			performSaveAction();
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure?","Quit?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		//updateStatus("Click on \"YES\" to exit.");
		if(choice == JOptionPane.YES_OPTION){
			System.exit(0);
		}
		return;
	}
	private void performUndoAction(){
		//write definition here
		setFileEditedStatus(true);
		try{
			if(undoStack.isEmpty())// || redoStack.isEmpty())
				return;
			redoStack.push(undoStack.pop());
			buildTreeFromString(undoStack.peek());
			//undoStack.push();
		}catch(Exception e){
			updateHint("No changes left.");
			return;
		}
		
		updateHint("Undo Action Performed Successfully and sorted in Attributes, Text and Element order. You can perform more Undo Actions.");
	}
	private void performRedoAction(){
		//write definition here
		setFileEditedStatus(true);
		try{
			if(redoStack.isEmpty())// || undoStack.isEmpty())
				return;
			//StringBuffer x = new StringBuffer(redoStack.pop());
			undoStack.push(redoStack.pop());
			buildTreeFromString(undoStack.peek());
		}catch(Exception e){
			updateHint("No changes left.");
			return;
		}

		updateHint("Redo Action Performed Successfully and sorted in Attributes, Text and Element order. You can perform more Undo Actions.");
	}
	private void performCutAction(){
		path = jRoot.getSelectionPath();
		if(path == null)
			return;
		copyNode = (DefaultMutableTreeNode) path.getLastPathComponent();
		DefaultMutableTreeNode parent = (DefaultMutableTreeNode) copyNode.getParent();
		if(parent == null)
			return;
		updateHint("Select target and click on paste.");
	}
	TreePath path ;
	private void performCopyAction(){
		path = jRoot.getSelectionPath();
		if(path == null)
			return;
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
		copyNode = (DefaultMutableTreeNode) selectedNode.clone();
		copyNode = copyFunction(copyNode,selectedNode);
		updateHint("Node copied, select target and click on paste");
	}
	private DefaultMutableTreeNode copyFunction(DefaultMutableTreeNode p, DefaultMutableTreeNode c){
		@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> e = c.children();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode d = (DefaultMutableTreeNode) e.nextElement();
			if (d.getUserObject() instanceof Element) {
				DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) d.clone();
				p.add(newNode);
				copyFunction(newNode,d);
			}
			if (d.getUserObject() instanceof Text) {
				DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) d.clone();
				p.add(newNode);
			}
			if (d.getUserObject() instanceof Attribute) {
				DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) d.clone();
				p.add(newNode);
			}
		}
		return p;
	}
	private void performPasteAction(){
		path = jRoot.getSelectionPath();
		if(path == null){
			updateHint("Select target properly.");
			return;
		}
		pasteNode = (DefaultMutableTreeNode) path.getLastPathComponent();
		if(copyNode == null){
			updateHint("Copy/Cut the node before pasting.");
			return;
		}
		if(pasteNode == null){
			updateHint("Select target properly.");
			return;
		}
		//pasteNode.add(copyNode);
		//jRoot.expandPath(path);
		//jRoot.updateUI();
		addChildToParent(pasteNode, copyNode);
		//expandAll(jRoot,0,jRoot.getRowCount());
		setFileEditedStatus(true);
		updateHint("Pasted, tree updated.");
		undoStack.push(getTreeText(treeNode,new Stack<String>(),new Stack<String>()));
	}
	
	private void performAttributeAction(){
		TreePath path = jRoot.getAnchorSelectionPath();
		if(path == null){
			updateHint("Please select target properly.");
			return;
		}
		
		JTextField attr_name = new JTextField();
		JTextField attr_value= new JTextField();
		Object[] message = {"Enter Name",attr_name,"Enter Value", attr_value};
		
		do{
			updateHint("Attribute Name should not be empty.");
			int option = JOptionPane.showConfirmDialog(null, message, "Enter Attribute Name & Value", JOptionPane.OK_CANCEL_OPTION);
			
			if(option==JOptionPane.CANCEL_OPTION){
				updateHint("Attribute Operation cancelled");
				return;
			}
			if(!attr_name.getText().isEmpty())
				break;
		}while(true);
		addChildToParent((DefaultMutableTreeNode) path.getLastPathComponent(), new DefaultMutableTreeNode(new Attribute(attr_name.getText(), attr_value.getText())));
		updateHint("Attribute Node added succesfully.");
	}
	private void performElementAction(){
		TreePath path = jRoot.getAnchorSelectionPath();
		if(path == null){
			updateHint("Please select target properly.");
			return;
		}
		//updateHint("Element name cannot be null");
		String option;
		do{
			option = JOptionPane.showInputDialog(null,"Element Name cannot be null");
			updateHint("Element name should not be empty.");
			if(option == null){// || option == "" || option.isEmpty())
				updateHint("Element Operation cancelled");
				return;
			}
		}while(option.isEmpty());
		addChildToParent((DefaultMutableTreeNode) path.getLastPathComponent(), new DefaultMutableTreeNode(new Element(option.toString())));
		updateHint("Element Node added succesfully.");
	}
	private void performTextAction(){
		TreePath path = jRoot.getAnchorSelectionPath();
		if(path == null){
			updateHint("Please select target properly.");
			return;
		}
		
		String option;
		do{
			updateHint("Text cannot be null");
			option = JOptionPane.showInputDialog(null,"Text cannot be null");
			if(option == null){// || option == "" || option.isEmpty())
				updateHint("Text Operation cancelled");
				return;
			}
		}while(option.isEmpty());
		addChildToParent((DefaultMutableTreeNode) path.getLastPathComponent(), new DefaultMutableTreeNode(new Text(option.toString())));
		updateHint("Text Node added succesfully.");
	}
	private void addChildToParent(DefaultMutableTreeNode parent, DefaultMutableTreeNode child){
		setFileEditedStatus(true);
		parent.add(child);
		//updateHint("Node added succesfully.");
		jRoot.expandPath(path);
		jRoot.updateUI();
		expandAll(jRoot,0,jRoot.getRowCount());
		undoStack.push(getTreeText(treeNode,new Stack<String>(),new Stack<String>()));
		return;
	}
	private void performAboutAction(){
		//System.out.println("Hai Harsha");
		final JFrame contentFrame = new JFrame("About XMLEditor");
		contentFrame.setVisible(true);
		
		contentFrame.setBounds(this.getX()+this.getWidth()/2-296/2, this.getY()+this.getHeight()/2-369/2,296,369);
		contentFrame.setLayout(null);
		
		
		
		JLabel name_label = new JLabel("Author :");
		name_label.setFont(new Font("Arial", Font.BOLD, 14));
		name_label.setHorizontalAlignment(SwingConstants.RIGHT);
		name_label.setBounds(35, 46, 58, 25);
		contentFrame.add(name_label);
		
		JLabel name_label_value = new JLabel("Harshavardhan Pasala");
		name_label_value.setFont(new Font("Arial", Font.PLAIN, 14));
		name_label_value.setBounds(103, 51, 150, 14);
		contentFrame.add(name_label_value);
		
		JLabel home_label = new JLabel("Home Page :");
		home_label.setFont(new Font("Arial", Font.BOLD, 14));
		home_label.setHorizontalAlignment(SwingConstants.RIGHT);
		home_label.setBounds(5, 82, 88, 16);
		contentFrame.add(home_label);
		
		JLabel home_label_value = new JLabel("http://web.njit.edu/~hp279");
		home_label_value.setFont(new Font("Arial", Font.PLAIN, 14));
		home_label_value.setBounds(103, 83, 170, 16);
		contentFrame.add(home_label_value);
		
		
		JLabel head_label = new JLabel("XML Editor");
		head_label.setFont(new Font("Arial Black", Font.PLAIN, 16));
		head_label.setHorizontalAlignment(SwingConstants.CENTER);
		head_label.setBounds(83, 11, 114, 24);
		contentFrame.add(head_label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 109, 280, 1);
		contentFrame.add(separator);
		
		JButton ok_button = new JButton("OK");
		ok_button.setToolTipText("Click to close");
		ok_button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				contentFrame.dispose(); 
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//ok_button.addActionListener(arg0);
		ok_button.setBounds(95, 283, 90, 23);
		contentFrame.add(ok_button);
        
        JEditorPane pane_text_area = new JEditorPane();
        pane_text_area.setEditable(false);
        pane_text_area.setForeground(UIManager.getColor("TextArea.foreground"));
        pane_text_area.setFont(new Font("Arial", Font.PLAIN, 15));
        pane_text_area.setBackground(UIManager.getColor("TextArea.inactiveBackground"));
        pane_text_area.setText("Copyright \u00A9 2015.\r\nAll rights reserved.\r\n\r\nOpen XML file is used to \r\nopen in a JTree format. \r\nCut/Copy/Paste allows \r\nusers to modify nodes in the \r\nsame manner. New allows \r\nusers to create XML file. \r\nUser can also add \r\nAttributes/Elements/Text \r\ndirectly.");
        pane_text_area.setCaretPosition(0);
        contentFrame.add(pane_text_area);
        
        
        JScrollPane text_area_scroll_pane = new JScrollPane(pane_text_area);
        text_area_scroll_pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        text_area_scroll_pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        text_area_scroll_pane.setBounds(36, 135, 208, 126);
        contentFrame.add(text_area_scroll_pane);
        
        JLabel verson_label = new JLabel("XML Editor - v1.0 built on 15 Apr, 2015");
        verson_label.setFont(new Font("Arial", Font.PLAIN, 11));
        verson_label.setHorizontalAlignment(SwingConstants.CENTER);
        verson_label.setBounds(5, 317, 275, 14);
        contentFrame.add(verson_label);
		
	}
}
