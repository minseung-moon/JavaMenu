package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Event;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FileDialog;
import java.awt.event.InputEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JDialog;

class Menu extends JFrame {
	JMenuBar menuBar;
	String[] menuContent = {"����", "����", "����", "����"};
	JMenu[] menus = new JMenu[menuContent.length];
	String[] fileItemContents = {"����", "���θ����", "�ٸ��̸����� ����", "������"};
	JMenuItem[] fileItems = new JMenuItem[fileItemContents.length];
	String[] modifyItemContents = {"����", "�ٿ��ֱ�", "�߶󳻱�", "�ð�.��¥" };
	JMenuItem[] modifyItems = new JMenuItem[modifyItemContents.length];
	String[] viewItemContents = {"Ȯ��", "���" };
	JMenuItem[] viewItem = new JMenuItem[viewItemContents.length];
	String[] pormatItemContents = {"����ũ��", "���ڽ�Ÿ��", "���ڻ���"};
	JMenuItem[] pormatItems = new JMenuItem[pormatItemContents.length];
	JTextPane textPane;
	JPanel bottomPanel;
	JLabel dateLabel;
	SimpleAttributeSet saset;
	String path;
	JPopupMenu popupMenu;
	JMenuItem[] popItem = new JMenuItem[modifyItemContents.length];
	JScrollPane scrollPane;
	
	public Menu() {
		// TODO Auto-generated constructor stub
		
		this.setTitle("�޴� ����� ����1");
		this.setSize(300, 250);
		this.setFont(new Font("Serif", Font.PLAIN, 12));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ����
		// �޴���
		menuBar = new JMenuBar();
		// �޴��� �޴�
		for(int i = 0; i < menus.length; i++) {
			menus[i] = new JMenu(menuContent[i]);
		}
		// ���� �޴� ������, ���� �޴� ������
		for(int i =0; i < fileItems.length; i++) {
			fileItems[i] = new JMenuItem(fileItemContents[i]);
			modifyItems[i] = new JMenuItem(modifyItemContents[i]);
			fileItems[i].addActionListener(new ActionEvent());
			modifyItems[i].addActionListener(new ActionEvent());
		}
		// ���� �޴� ������
		for(int i = 0; i < viewItem.length; i++) {
			viewItem[i] = new JMenuItem(viewItemContents[i]);
			viewItem[i].addActionListener(new ActionEvent());
		}
		// ���ĸ޴� ������
		for(int i = 0; i < pormatItems.length; i++) {
			pormatItems[i] = new JMenuItem(pormatItemContents[i]);
			pormatItems[i].addActionListener(new ActionEvent());
		}
		// â �� �� ��¥ �ǳ�, ���̺�
		bottomPanel = new JPanel();
		dateLabel = new JLabel("��¥");
		
		// �˾�
		popupMenu = new JPopupMenu();
		for(int i = 0; i < popItem.length; i++) {
			if(modifyItemContents[i].equals("�ð�.��¥"))
				popupMenu.addSeparator();
			popItem[i] = new JMenuItem(modifyItemContents[i]);
			popItem[i].addActionListener(new ActionEvent());
			popupMenu.add(popItem[i]);
		}
		
		// ����
		// �޴��� ����
		this.setJMenuBar(menuBar);
		// �޴��� �޴� ����
		for(int i = 0; i < menus.length; i++) {
			menuBar.add(menus[i]);
		}
		// ���� �޴�, ���� �޴� ����
		for(int i =0; i < fileItems.length; i++) {
			if(i == 3) {
				menus[0].addSeparator();
				menus[1].addSeparator();	
			}
			menus[0].add(fileItems[i]);
			menus[1].add(modifyItems[i]);
		}
		// ���� �޴� ����
		for(int i = 0; i < viewItem.length; i++) {
			menus[2].add(viewItem[i]);
		}
		// ���ĸ޴� ����
		for(int i =0; i < pormatItems.length; i++) {
			menus[3].add(pormatItems[i]);
		}
		
		// textPane ����, ��ũ�� �߰�
		textPane = new JTextPane();
		scrollPane = new JScrollPane(textPane);
		this.add(scrollPane, BorderLayout.CENTER);
		// â �� �� ��¥ ����
		this.add(bottomPanel,BorderLayout.SOUTH);
		bottomPanel.add(dateLabel);
		
		
		
		 //����Ű
		 menus[0].setMnemonic('F');
		 fileItems[0].setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));
		 fileItems[1].setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_MASK ^ InputEvent.SHIFT_MASK));
		 
		 textPane.addMouseListener(new MouseAdapter() {
			 @Override
	            public void mousePressed(MouseEvent e) {
	                // ������ ���콺 ��ư�� ������ PopupMenu�� ȭ�鿡 �����ش�.
	                if (e.getButton() == MouseEvent.BUTTON3)
	                    popupMenu.show(textPane, e.getX(), e.getY());
	            }
		});
//		 textPane.insertIcon(new ImageIcon("D:\\mms\\2021-03-10\\src\\menu\\abc.jpg"));
	}
	class ActionEvent extends JDialog implements ActionListener {
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			// ���� �ҷ����� �̺�Ʈ
			if(e.getSource() == fileItems[0]) {
				FileDialog dialog = new FileDialog(this, "�ҷ�����", FileDialog.LOAD);
				dialog.setFile("*.txt");
				dialog.setVisible(true);
				
				path = dialog.getDirectory() + dialog.getFile();
				String s = ""; // �ѹ��� + �ѹ���
				
				try {
					FileReader r1 = new FileReader(path);
					int k; // �ڵ� ��
					while (true) {
						k = r1.read(); // �� ���ھ� �ҷ��´�
						if(k==-1) { // -1���� ����
							break;
						}
						s += (char) k; // �ڵ� �� ���ڷ� ����ȯ
					}
					this.setTitle(path);
					r1.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				textPane.setText(s);
			// �ʱ�ȭ, ���θ���� �̺�Ʈ
			} else if (e.getSource() == fileItems[1]) {
				textPane.setText("");
			// �޸����� ���� ����
			} else if (e.getSource() == fileItems[2]) {
				FileDialog dialog = new FileDialog(this, "�����ϱ�", FileDialog.SAVE);
				dialog.setFile("*.txt");
				dialog.setVisible(true);
				
				path = dialog.getDirectory() + dialog.getFile();
				
				try {
					FileWriter w = new FileWriter(path);
					String s = textPane.getText();
					w.write(s);
					this.setTitle(path);
					w.close();
				} catch (Exception e1) {
					System.out.println("����" + e1);
				}
			// ����
			} else if (e.getSource() == fileItems[3]) {
				System.exit(0);
			// �޸��忡 ������ �κ� ����
			} else if (e.getSource() == modifyItems[0] || e.getSource() == popItem[0]) {
				textPane.copy();
			// �޸��忡 ������ �κ� �ٿ��ֱ�
			} else if (e.getSource() == modifyItems[1] || e.getSource() == popItem[1]) {
				textPane.paste();
			//�޸��忡 ������ �κ� �߶󳻱�
			} else if (e.getSource() == modifyItems[2] || e.getSource() == popItem[2]) {
				textPane.cut();
			// �ϴܿ� ��¥�� �ð� ���
			} else if (e.getSource() == modifyItems[3] || e.getSource() == popItem[3]) {
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm�� ss�� E����");
				dateLabel.setText(dateFormat.format(date));
			// �޸��� Ȯ��, ������ ����
			} else if (e.getSource() == viewItem[0]) {
				int fontSize = textPane.getFont().getSize();
				fontSize += 5;
				textPane.setFont(new Font("Serif", Font.PLAIN, fontSize));
			// �޸��� ���, ������ ����
			} else if (e.getSource() == viewItem[1]) {
				int fontSize = textPane.getFont().getSize();
				fontSize -= 5;
				textPane.setFont(new Font("Serif", Font.PLAIN, fontSize));
			// ��Ʈ ũ�� ����
			} else if (e.getSource() == pormatItems[0]) {
				try {
					int size = Integer.parseInt(JOptionPane.showInputDialog("���� ũ��?"));
					saset = new SimpleAttributeSet();
					StyleConstants.setFontSize(saset, size);
					textPane.setCharacterAttributes(saset, true);
				} catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "����ϼ̽��ϴ�");
				}
			// ��Ʈ ��Ÿ�� ����
			} else if (e.getSource() == pormatItems[1]) {
				try {
					String[] fonts = {"Serif", "�ü�", "�������", "����", "��ü�� ��ü ����ü", "����"};
					Object fontObj = JOptionPane.showInputDialog(null, "��Ʈ�� �����ϼ���?", "���� ����", JOptionPane.QUESTION_MESSAGE, null, fonts, fonts[0]);
					saset = new SimpleAttributeSet();
					StyleConstants.setFontFamily(saset,fontObj.toString());
					textPane.setCharacterAttributes(saset, true);	
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "����ϼ̽��ϴ�");
				}
			// ��Ʈ ���� ����
			} else if(e.getSource() == pormatItems[2]) {
				try {
					String[] Colors = {"BLACK", "RED", "BLUE", "GREEN"};
					Object ColorObj = JOptionPane.showInputDialog(null, "������ �����ϼ���?", "���� ����", JOptionPane.QUESTION_MESSAGE, null, Colors, Colors[0]);
					
					saset = new SimpleAttributeSet();
					
					if(ColorObj.equals("BLACK"))
						StyleConstants.setForeground(saset, Color.BLACK);
					else if(ColorObj.equals("RED"))
						StyleConstants.setForeground(saset, Color.RED);
					else if(ColorObj.equals("BLUE"))
						StyleConstants.setForeground(saset, Color.BLUE);
					else if(ColorObj.equals("GREEN"))
						StyleConstants.setForeground(saset, Color.GREEN);
					textPane.setCharacterAttributes(saset, true);
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "����ϼ̽��ϴ�");
				}
			}
			
			
		}
		
	}
	
}

public class MenuEx4 {
	public static void main(String[] args) {
		Menu menu = new Menu();
	}
}
