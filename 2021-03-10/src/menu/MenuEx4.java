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
	String[] menuContent = {"파일", "편집", "보기", "서식"};
	JMenu[] menus = new JMenu[menuContent.length];
	String[] fileItemContents = {"열기", "새로만들기", "다른이름으로 저장", "끝내기"};
	JMenuItem[] fileItems = new JMenuItem[fileItemContents.length];
	String[] modifyItemContents = {"복사", "붙여넣기", "잘라내기", "시간.날짜" };
	JMenuItem[] modifyItems = new JMenuItem[modifyItemContents.length];
	String[] viewItemContents = {"확대", "축소" };
	JMenuItem[] viewItem = new JMenuItem[viewItemContents.length];
	String[] pormatItemContents = {"글자크기", "글자스타일", "글자색상"};
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
		
		this.setTitle("메뉴 만들기 예제1");
		this.setSize(300, 250);
		this.setFont(new Font("Serif", Font.PLAIN, 12));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 선언
		// 메뉴바
		menuBar = new JMenuBar();
		// 메뉴바 메뉴
		for(int i = 0; i < menus.length; i++) {
			menus[i] = new JMenu(menuContent[i]);
		}
		// 파일 메뉴 아이템, 편집 메뉴 아이템
		for(int i =0; i < fileItems.length; i++) {
			fileItems[i] = new JMenuItem(fileItemContents[i]);
			modifyItems[i] = new JMenuItem(modifyItemContents[i]);
			fileItems[i].addActionListener(new ActionEvent());
			modifyItems[i].addActionListener(new ActionEvent());
		}
		// 보기 메뉴 아이템
		for(int i = 0; i < viewItem.length; i++) {
			viewItem[i] = new JMenuItem(viewItemContents[i]);
			viewItem[i].addActionListener(new ActionEvent());
		}
		// 서식메뉴 아이템
		for(int i = 0; i < pormatItems.length; i++) {
			pormatItems[i] = new JMenuItem(pormatItemContents[i]);
			pormatItems[i].addActionListener(new ActionEvent());
		}
		// 창 맨 밑 날짜 판넬, 레이블
		bottomPanel = new JPanel();
		dateLabel = new JLabel("날짜");
		
		// 팝업
		popupMenu = new JPopupMenu();
		for(int i = 0; i < popItem.length; i++) {
			if(modifyItemContents[i].equals("시간.날짜"))
				popupMenu.addSeparator();
			popItem[i] = new JMenuItem(modifyItemContents[i]);
			popItem[i].addActionListener(new ActionEvent());
			popupMenu.add(popItem[i]);
		}
		
		// 대입
		// 메뉴바 대입
		this.setJMenuBar(menuBar);
		// 메뉴바 메뉴 대입
		for(int i = 0; i < menus.length; i++) {
			menuBar.add(menus[i]);
		}
		// 파일 메뉴, 편집 메뉴 대입
		for(int i =0; i < fileItems.length; i++) {
			if(i == 3) {
				menus[0].addSeparator();
				menus[1].addSeparator();	
			}
			menus[0].add(fileItems[i]);
			menus[1].add(modifyItems[i]);
		}
		// 보기 메뉴 대입
		for(int i = 0; i < viewItem.length; i++) {
			menus[2].add(viewItem[i]);
		}
		// 서식메뉴 대입
		for(int i =0; i < pormatItems.length; i++) {
			menus[3].add(pormatItems[i]);
		}
		
		// textPane 대입, 스크롤 추가
		textPane = new JTextPane();
		scrollPane = new JScrollPane(textPane);
		this.add(scrollPane, BorderLayout.CENTER);
		// 창 맨 밑 날짜 대입
		this.add(bottomPanel,BorderLayout.SOUTH);
		bottomPanel.add(dateLabel);
		
		
		
		 //단축키
		 menus[0].setMnemonic('F');
		 fileItems[0].setAccelerator(KeyStroke.getKeyStroke('N',Event.CTRL_MASK));
		 fileItems[1].setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_MASK ^ InputEvent.SHIFT_MASK));
		 
		 textPane.addMouseListener(new MouseAdapter() {
			 @Override
	            public void mousePressed(MouseEvent e) {
	                // 오른쪽 마우스 버튼을 누르면 PopupMenu를 화면에 보여준다.
	                if (e.getButton() == MouseEvent.BUTTON3)
	                    popupMenu.show(textPane, e.getX(), e.getY());
	            }
		});
//		 textPane.insertIcon(new ImageIcon("D:\\mms\\2021-03-10\\src\\menu\\abc.jpg"));
	}
	class ActionEvent extends JDialog implements ActionListener {
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			// 파일 불러오기 이벤트
			if(e.getSource() == fileItems[0]) {
				FileDialog dialog = new FileDialog(this, "불러오기", FileDialog.LOAD);
				dialog.setFile("*.txt");
				dialog.setVisible(true);
				
				path = dialog.getDirectory() + dialog.getFile();
				String s = ""; // 한문자 + 한문자
				
				try {
					FileReader r1 = new FileReader(path);
					int k; // 코드 값
					while (true) {
						k = r1.read(); // 한 문자씩 불러온다
						if(k==-1) { // -1문자 없다
							break;
						}
						s += (char) k; // 코드 값 문자로 형변환
					}
					this.setTitle(path);
					r1.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				textPane.setText(s);
			// 초기화, 새로만들기 이벤트
			} else if (e.getSource() == fileItems[1]) {
				textPane.setText("");
			// 메모장의 내용 저장
			} else if (e.getSource() == fileItems[2]) {
				FileDialog dialog = new FileDialog(this, "저장하기", FileDialog.SAVE);
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
					System.out.println("오류" + e1);
				}
			// 종료
			} else if (e.getSource() == fileItems[3]) {
				System.exit(0);
			// 메모장에 선택한 부분 복사
			} else if (e.getSource() == modifyItems[0] || e.getSource() == popItem[0]) {
				textPane.copy();
			// 메모장에 선택한 부분 붙여넣기
			} else if (e.getSource() == modifyItems[1] || e.getSource() == popItem[1]) {
				textPane.paste();
			//메모장에 선택한 부분 잘라내기
			} else if (e.getSource() == modifyItems[2] || e.getSource() == popItem[2]) {
				textPane.cut();
			// 하단에 날짜와 시간 출력
			} else if (e.getSource() == modifyItems[3] || e.getSource() == popItem[3]) {
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초 E요일");
				dateLabel.setText(dateFormat.format(date));
			// 메모장 확대, 사이즈 증가
			} else if (e.getSource() == viewItem[0]) {
				int fontSize = textPane.getFont().getSize();
				fontSize += 5;
				textPane.setFont(new Font("Serif", Font.PLAIN, fontSize));
			// 메모장 축소, 사이즈 감소
			} else if (e.getSource() == viewItem[1]) {
				int fontSize = textPane.getFont().getSize();
				fontSize -= 5;
				textPane.setFont(new Font("Serif", Font.PLAIN, fontSize));
			// 폰트 크기 설정
			} else if (e.getSource() == pormatItems[0]) {
				try {
					int size = Integer.parseInt(JOptionPane.showInputDialog("글자 크기?"));
					saset = new SimpleAttributeSet();
					StyleConstants.setFontSize(saset, size);
					textPane.setCharacterAttributes(saset, true);
				} catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "취소하셨습니다");
				}
			// 폰트 스타일 설정
			} else if (e.getSource() == pormatItems[1]) {
				try {
					String[] fonts = {"Serif", "궁서", "나눔고딕", "돋움", "문체부 궁체 정자체", "바탕"};
					Object fontObj = JOptionPane.showInputDialog(null, "폰트를 선택하세요?", "동물 선택", JOptionPane.QUESTION_MESSAGE, null, fonts, fonts[0]);
					saset = new SimpleAttributeSet();
					StyleConstants.setFontFamily(saset,fontObj.toString());
					textPane.setCharacterAttributes(saset, true);	
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "취소하셨습니다");
				}
			// 폰트 색상 설정
			} else if(e.getSource() == pormatItems[2]) {
				try {
					String[] Colors = {"BLACK", "RED", "BLUE", "GREEN"};
					Object ColorObj = JOptionPane.showInputDialog(null, "색상을 선택하세요?", "동물 선택", JOptionPane.QUESTION_MESSAGE, null, Colors, Colors[0]);
					
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
					JOptionPane.showMessageDialog(null, "취소하셨습니다");
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
