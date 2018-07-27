import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class GUI extends JFrame{
	
	//INITIALIZE ALL THE GUI COMPONENTS
	private boolean Axes=true,Grid=true;
	private double xmin,xmax,ymin,ymax;
	private Color graphColor=Color.WHITE;
	private Color axesColor=Color.BLACK;
	private Color gridColor=Color.DARK_GRAY;
	private int axisWidth=2,gridWidth=1,rows;
	private Double End,Start,Inc;
	
	
	public GUI() {
		init();
	}

	EvaluateString es= new EvaluateString();
	protected JTextField textField;
	protected JTextField textField_1;
	protected JTextField txtEnterFxHere;
	protected JTextField textField_2;
	private JTextField Xmin;
	private JTextField Xmax;
	private JTextField Ymin;
	private JTextField Ymax;
	private JTextField start;
	private JTextField end;
	private JTextField increment;
	
	int function;
	double firstNum;
	double secondNum;
	double result;
	String operations;
	String answer;
	String expr;
	
	private JLabel lblEquation;
	private JLabel lblYFx;
	private JLabel lblSelectColor;
	private JLabel lblHistory;
	private JLabel lblXmin;
	private JLabel lblXmax;
	private JLabel lblYmin;
	private JLabel lblYmax;
	private JLabel fColor;
	private JLabel aColor;
	private JLabel bColor;
	private JLabel cColor;
	private JLabel dColor;
	private JLabel eColor;
	private JLabel gColor; 
	private JLabel lblstart;
	private JLabel lblEnd;
	private JLabel lblIncrement;
	
	private JButton btnDelete;
	private JButton btnPlot;
	private JButton btnLoad;
	private JButton btn9;
	private JButton btn6;
	private JButton btn3;
	private JButton btnDot;
	private JButton btn8;
	private JButton btn7;
	private JButton btnPlus;
	private JButton btnPower;
	private JButton openBracket;
	private JButton btnSin;
	private JButton btn5;
	private JButton btn2;
	private JButton btn0;
	private JButton btnCos;
	private JButton closeBracket;
	private JButton btn4;
	private JButton btnMinus;
	private JButton btnRoot;
	private JButton btn1;
	private JButton btnDivide;
	private JButton btnC;
	private JButton btnMultiply;
	private JButton btnE;
	private JButton btnPi;
	private JButton btnTan;
	private JButton btnLn;
	private JButton btnEnter;
	private JButton btnAnswer;
	
	private JPanel screenPanel;
	private JPanel graph;
	private JPanel GraphPanel;
	private JPanel keyboardPanel;
	
	private JTable table;

	
	void init() {
		
		 //SET BORDER LAYOUT
		 getContentPane().setLayout(new BorderLayout(0, 0));
		 screenPanel = new JPanel();
		 screenPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		 screenPanel.setBounds(0, 0, 362, 390);
		 getContentPane().add(screenPanel);
		 
		 setSize(new Dimension(1500, 700));
		 setLocation(new Point(0, 0));
		 setTitle("CALCULATOR !");
		 setBounds(100, 100, 1000, 700);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 screenPanel.setLayout(null);

		//EQUATION TEXTFIELD
		lblEquation = new JLabel("Equation :-");
		lblEquation.setBounds(120, 16, 85, 16);
		lblEquation.setFont(new Font("Arial", Font.PLAIN, 12));
		screenPanel.add(lblEquation);
		
		lblYFx = new JLabel("y= f(x)=");
		lblYFx.setBounds(34, 40, 85, 16);
		lblYFx.setFont(new Font("Arial", Font.PLAIN, 12));
		screenPanel.add(lblYFx);
		
		txtEnterFxHere = new JTextField();
		txtEnterFxHere.setBounds(81, 35, 263, 26);
		txtEnterFxHere.setToolTipText("Enter f(x) here");
		txtEnterFxHere.setFont(new Font("Arial", Font.PLAIN, 13));
		screenPanel.add(txtEnterFxHere);
		txtEnterFxHere.setColumns(10);
		
		//FUNCTIONS FOR THE HISTORY FIELD
		DefaultListModel<String> model = new DefaultListModel<>();
		JList<String> history = new JList<String>(model);
		history.setBounds(22, 6, 255, 61);
		screenPanel.add(history);

		JScrollPane scroll = new JScrollPane(history);
		scroll.setBounds(22, 170, 322, 94);
		screenPanel.add(scroll);
		
		btnPlot = new JButton("Plot");
		btnPlot.setBounds(159, 68, 67, 29);
		btnPlot.setToolTipText("click to add equations to history");
		btnPlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtEnterFxHere.getText().isEmpty()) {
				               model.addElement("y="+txtEnterFxHere.getText()+"\n");
				               setColors();
				               plotting();
			                   drawtheTable();
				               }
				            }
				         }
				      );
		btnPlot.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		screenPanel.add(btnPlot);
		
		btnDelete = new JButton("Del");
		btnDelete.setBounds(277, 68, 67, 29);
		btnDelete.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnDelete.setToolTipText("click to delete an equation from the history ");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int SelectedIndex=history.getSelectedIndex();

				model.remove(SelectedIndex);
			}
		});
		screenPanel.add(btnDelete);
		
		lblSelectColor = new JLabel("Select Color :-");
		lblSelectColor.setBounds(120, 116, 85, 16);
		lblSelectColor.setFont(new Font("Arial", Font.PLAIN, 12));
		screenPanel.add(lblSelectColor);
		
		//SETTING COLORS IN THE EQUATION TEXTFIELD
		JComboBox comboBox;
		String[] strColors = {"Red", "Blue", "Green",
                "Cyan", "Magenta", "Yellow", "Black"};
		 Color[] colors = {Color.RED, Color.BLUE, Color.GREEN,
		            Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.BLACK};
		comboBox = new JComboBox(strColors);
		comboBox.setBounds(217, 111, 94, 27);
		comboBox.addItemListener(new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			txtEnterFxHere.setForeground(colors[comboBox.getSelectedIndex()]);
		}
		}
		});
		screenPanel.add(comboBox);
		
		
		lblHistory = new JLabel("History :-");
		lblHistory.setBounds(22, 153, 85, 16);
		lblHistory.setFont(new Font("Arial", Font.PLAIN, 12));
		screenPanel.add(lblHistory);
		
		btnLoad = new JButton("Load");
		btnLoad.setBounds(120, 281, 67, 29);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int SelectedIndex=history.getSelectedIndex();
				String m = model.getElementAt(SelectedIndex);
				m=m.substring(m.lastIndexOf('=') + 1);
				txtEnterFxHere.setText(m);
				
			}
		});
		btnLoad.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		screenPanel.add(btnLoad);
		
		//LABEL FOR SETTING THE GRAPH RANGE
		lblXmin = new JLabel("Xmin");
		lblXmin.setBounds(6, 322, 48, 26);
		screenPanel.add(lblXmin);
		
		lblXmax = new JLabel("Xmax");
		lblXmax.setBounds(68, 322, 48, 26);
		screenPanel.add(lblXmax);
		
		lblYmin = new JLabel("Ymin");
		lblYmin.setBounds(139, 322, 48, 26);
		screenPanel.add(lblYmin);
		
		lblYmax = new JLabel("Ymax");
		lblYmax.setBounds(202, 322, 48, 26);
		screenPanel.add(lblYmax);
		
		
		//TEXTFIELD FOR SETTING THE GRAPH RANGE
		Xmin = new JTextField();
		Xmin.setBounds(0, 346, 48, 38);
		Xmin.setText("-10");
		screenPanel.add(Xmin);
		Xmin.setColumns(10);
		
		Xmax = new JTextField();
		Xmax.setBounds(62, 346, 48, 38);
		Xmax.setColumns(10);
		Xmax.setText("10");
		screenPanel.add(Xmax);
		
		Ymin = new JTextField();
		Ymin.setBounds(128, 346, 48, 38);
		Ymin.setColumns(10);
		Ymin.setText("-10");
		screenPanel.add(Ymin);
		
		Ymax = new JTextField();
		Ymax.setBounds(199, 346, 48, 38);
		Ymax.setColumns(10);
		Ymax.setText("10");
		screenPanel.add(Ymax);
		
		JButton ClearGraph = new JButton("Clear Graph");
		ClearGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleargraph();
			}
		});
		ClearGraph.setToolTipText("click to add equations to history");
		ClearGraph.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ClearGraph.setBounds(28, 68, 79, 29);
		screenPanel.add(ClearGraph);
		
		//TEXTFIELD FOR SIMPLE CALCULATOR
		textField_2 = new JTextField();
		textField_2.setBounds(10, 402, 346, 44);
		textField_2.setEditable(false);
		 getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		//BUTTONS AND ACTION LISTENERS FOR THE SIMPLE CALCULATOR
		btn9 = new JButton("9");
		btn9.setBounds(57, 447, 50, 44);
		btn9.setForeground(Color.BLACK);
		btn9.setFont(new Font("Arial", Font.PLAIN, 13));
		btn9.setBackground(Color.WHITE);
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+"9");
			}
		});
		 getContentPane().add(btn9);
		
		btn6 = new JButton("6");
		btn6.setBounds(57, 492, 50, 44);
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+"6");
			}
		});
		btn6.setForeground(Color.BLACK);
		btn6.setFont(new Font("Arial", Font.PLAIN, 13));
		btn6.setBackground(Color.WHITE);
		 getContentPane().add(btn6);
		
		btn3 = new JButton("3");
		btn3.setBounds(57, 537, 50, 44);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+"3");
			}
		});
		btn3.setForeground(Color.BLACK);
		btn3.setFont(new Font("Arial", Font.PLAIN, 13));
		btn3.setBackground(Color.WHITE);
		 getContentPane().add(btn3);
		
		btnDot = new JButton(".");
		btnDot.setBounds(57, 580, 50, 44);
		btnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+".");
			}
		});
		btnDot.setForeground(Color.BLACK);
		btnDot.setFont(new Font("Arial", Font.PLAIN, 13));
		btnDot.setBackground(Color.WHITE);
		 getContentPane().add(btnDot);
		
		btn8 = new JButton("8");
		btn8.setBounds(108, 447, 50, 44);
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+"8");
			}
		});
		btn8.setForeground(Color.BLACK);
		btn8.setFont(new Font("Arial", Font.PLAIN, 13));
		btn8.setBackground(Color.WHITE);
		 getContentPane().add(btn8);
		
		btn7 = new JButton("7");
		btn7.setBounds(158, 447, 50, 44);
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+"7");
			}
		});
		btn7.setForeground(Color.BLACK);
		btn7.setFont(new Font("Arial", Font.PLAIN, 13));
		btn7.setBackground(Color.WHITE);
		 getContentPane().add(btn7);
		
		btnPlus = new JButton("+");
		btnPlus.setBounds(209, 447, 50, 44);
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" + ");
			}
		});
		btnPlus.setForeground(Color.BLACK);
		btnPlus.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPlus.setBackground(Color.WHITE);
		 getContentPane().add(btnPlus);
		
		btnPower = new JButton("^");
		btnPower.setBounds(261, 447, 50, 44);
		btnPower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" ^ ");
			}
		});
		btnPower.setForeground(Color.BLACK);
		btnPower.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPower.setBackground(Color.WHITE);
		 getContentPane().add(btnPower);
		
		btnSin = new JButton("sin");
		btnSin.setBounds(312, 447, 50, 44);
		btnSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" sin (");
			}
		});
		btnSin.setForeground(Color.BLACK);
		btnSin.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSin.setBackground(Color.WHITE);
		 getContentPane().add(btnSin);
		
		btn5 = new JButton("5");
		btn5.setBounds(108, 492, 50, 44);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+"5");
			}
		});
		btn5.setForeground(Color.BLACK);
		btn5.setFont(new Font("Arial", Font.PLAIN, 13));
		btn5.setBackground(Color.WHITE);
		 getContentPane().add(btn5);
		
		btn2 = new JButton("2");
		btn2.setBounds(108, 537, 50, 44);
		
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+"2");
			}
		});
		btn2.setForeground(Color.BLACK);
		btn2.setFont(new Font("Arial", Font.PLAIN, 13));
		btn2.setBackground(Color.WHITE);
		 getContentPane().add(btn2);
		
		btn0 = new JButton("0");
		btn0.setBounds(108, 580, 50, 44);
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+"0");
			}
		});
		btn0.setForeground(Color.BLACK);
		btn0.setFont(new Font("Arial", Font.PLAIN, 13));
		btn0.setBackground(Color.WHITE);
		 getContentPane().add(btn0);
		
		btnCos = new JButton("cos");
		btnCos.setBounds(312, 492, 50, 44);
		btnCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" cos (");
			}
		});
		btnCos.setForeground(Color.BLACK);
		btnCos.setFont(new Font("Arial", Font.PLAIN, 13));
		btnCos.setBackground(Color.WHITE);
		 getContentPane().add(btnCos);
		
		btn4 = new JButton("4");
		btn4.setBounds(158, 492, 50, 44);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+"4");
			}
		});
		btn4.setForeground(Color.BLACK);
		btn4.setFont(new Font("Arial", Font.PLAIN, 13));
		btn4.setBackground(Color.WHITE);
		 getContentPane().add(btn4);
		
		btnMinus = new JButton("-");
		btnMinus.setBounds(209, 492, 50, 44);
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" - ");
			}
		});
		btnMinus.setForeground(Color.BLACK);
		btnMinus.setFont(new Font("Arial", Font.PLAIN, 13));
		btnMinus.setBackground(Color.WHITE);
		 getContentPane().add(btnMinus);
		
		btnRoot = new JButton("√");
		btnRoot.setBounds(261, 492, 50, 44);
		btnRoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" √ ");
			}
		});
		btnRoot.setForeground(Color.BLACK);
		btnRoot.setFont(new Font("Arial", Font.PLAIN, 13));
		btnRoot.setBackground(Color.WHITE);
		 getContentPane().add(btnRoot);
		
		btn1 = new JButton("1");
		btn1.setBounds(158, 537, 50, 44);
		
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+"1");
			}
		});
		btn1.setForeground(Color.BLACK);
		btn1.setFont(new Font("Arial", Font.PLAIN, 13));
		btn1.setBackground(Color.WHITE);
		 getContentPane().add(btn1);
		
		btnC = new JButton("C");
		btnC.setBounds(158, 580, 50, 44);
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText("");
			}
		});
		btnC.setForeground(Color.BLACK);
		btnC.setFont(new Font("Arial", Font.PLAIN, 13));
		btnC.setBackground(Color.WHITE);
		 getContentPane().add(btnC);
		
		btnDivide = new JButton("/");
		btnDivide.setBounds(209, 537, 50, 44);
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" / ");
			}
		});
		btnDivide.setForeground(Color.BLACK);
		btnDivide.setFont(new Font("Arial", Font.PLAIN, 13));
		btnDivide.setBackground(Color.WHITE);
		 getContentPane().add(btnDivide);
		
		btnMultiply = new JButton("* ");
		btnMultiply.setBounds(209, 580, 50, 44);
		btnMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" * ");
			}
		});
		btnMultiply.setForeground(Color.BLACK);
		btnMultiply.setFont(new Font("Arial", Font.PLAIN, 13));
		btnMultiply.setBackground(Color.WHITE);
		 getContentPane().add(btnMultiply);
		
		btnE = new JButton("e");
		btnE.setBounds(261, 537, 50, 44);
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" e ");
				
			}
		});
		btnE.setForeground(Color.BLACK);
		btnE.setFont(new Font("Arial", Font.PLAIN, 13));
		btnE.setBackground(Color.WHITE);
		 getContentPane().add(btnE);
		
		btnPi = new JButton("π");
		btnPi.setBounds(261, 580, 50, 44);
		btnPi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" π ");
			}
		});
		btnPi.setForeground(Color.BLACK);
		btnPi.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPi.setBackground(Color.WHITE);
		 getContentPane().add(btnPi);
		
		btnTan = new JButton("tan");
		btnTan.setBounds(312, 537, 50, 44);
		btnTan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" tan (");
			}
		});
		btnTan.setForeground(Color.BLACK);
		btnTan.setFont(new Font("Arial", Font.PLAIN, 13));
		btnTan.setBackground(Color.WHITE);
		 getContentPane().add(btnTan);
		
		btnLn = new JButton("ln");
		btnLn.setBounds(312, 580, 50, 44);
		btnLn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" ln ");
			}
		});
		btnLn.setForeground(Color.BLACK);
		btnLn.setFont(new Font("Arial", Font.PLAIN, 13));
		btnLn.setBackground(Color.WHITE);
		 getContentPane().add(btnLn);
		
		btnEnter = new JButton("ENTER");
		btnEnter.setBounds(184, 628, 178, 44);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String test= textField_2.getText();
				double c=es.evaluate(test,es,null);
				if(es.flag==-1) {
					textField_2.setText("invalid input");
				}
				else{
					String source = String.valueOf(c);
					BigDecimal number = new BigDecimal(source);  
					source=number.stripTrailingZeros().toPlainString();
					textField_2.setText(source);
					answer=source;
				}
			}
		});
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setFont(new Font("Arial", Font.PLAIN, 13));
		btnEnter.setBackground(Color.WHITE);
		 getContentPane().add(btnEnter);
		
		btnAnswer = new JButton("ANSWER");
		btnAnswer.setBounds(6, 628, 178, 44);
		btnAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(answer);

			}
		});
		btnAnswer.setForeground(Color.BLACK);
		btnAnswer.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAnswer.setBackground(Color.WHITE);
		 getContentPane().add(btnAnswer);
		
		openBracket = new JButton("(");
		openBracket.setBounds(6, 447, 50, 44);
		openBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" ( ");
			}
		});
		openBracket.setForeground(Color.BLACK);
		openBracket.setFont(new Font("Arial", Font.PLAIN, 13));
		openBracket.setBackground(Color.WHITE);
		 getContentPane().add(openBracket);
		
		closeBracket = new JButton(")");
		closeBracket.setBounds(6, 492, 50, 44);
		closeBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText(textField_2.getText()+" ) ");
			}
		});
		closeBracket.setForeground(Color.BLACK);
		closeBracket.setFont(new Font("Arial", Font.PLAIN, 13));
		closeBracket.setBackground(Color.WHITE);
		 getContentPane().add(closeBracket);	
		 
		 
		 //INITIALIZE GRAPH PANEL
		 GraphPanel = new JPanel();
		 GraphPanel.setToolTipText("for graphing");
		 GraphPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		 GraphPanel.setBounds(368, 0, 626, 672);
		 getContentPane().add(GraphPanel);
		 GraphPanel.setLayout(null);
		 
		 graph = new JPanel();
		 graph.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 graph.setBackground(Color.WHITE);
		 graph.setBounds(104, 6, 309, 660);
		 GraphPanel.add(graph);
		 
		 JRadioButton DrawAxes = new JRadioButton("Draw Axes");
		 DrawAxes.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		drawaxisAction(e);
		 		
		 	}

		
		 });
		 DrawAxes.setBounds(6, 28, 99, 23);
		 GraphPanel.add(DrawAxes);
		 DrawAxes.setSelected(true);
		 
		 JRadioButton DrawGrid = new JRadioButton("Draw Grid");
		 DrawGrid.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		drawgridAction(e);
		 	}

		 });
		 DrawGrid.setBounds(6, 63, 99, 23);
		 GraphPanel.add(DrawGrid);
		 DrawGrid.setSelected(true);
		 
		 //TO LIMIT NO OF GRAPHS DRAWN SIMULTANEOUSLY TO 7
	 	JComboBox comboBoxGraph;
		String[] equation = {"a(x)", "b(x)",
                "c(x)", "d(x)", "e(x)", "f(x)", "g(x)"};
		comboBoxGraph = new JComboBox(equation);
		comboBoxGraph.setBounds(6, 122, 86, 16);
		comboBoxGraph.addItemListener(new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			function=comboBoxGraph.getSelectedIndex();
			System.out.println("function:"+function);
			}
		}
		});
		GraphPanel.add(comboBoxGraph);
		 
		 //LABELS FOR DIFFERENT GRAPHS
		 fColor = new JLabel("f(x):");
		 fColor.setBounds(6, 396, 61, 16);
		 GraphPanel.add(fColor);
		 
		 aColor = new JLabel("a(x):");
		 aColor.setBounds(6, 165, 61, 16);
		 GraphPanel.add(aColor);
		 
		 bColor = new JLabel("b(x):");
		 bColor.setBounds(6, 210, 61, 16);
		 GraphPanel.add(bColor);
		 
		 cColor = new JLabel("c(x):");
		 cColor.setBounds(6, 259, 61, 16);
		 GraphPanel.add(cColor);
		 
		 dColor = new JLabel("d(x):");
		 dColor.setBounds(6, 306, 61, 16);
		 GraphPanel.add(dColor);
		 
		 eColor = new JLabel("e(x):");
		 eColor.setBounds(6, 351, 61, 16);
		 GraphPanel.add(eColor);
		 
		 gColor = new JLabel("g(x):");
		 gColor.setBounds(6, 445, 61, 16);
		 GraphPanel.add(gColor);
		 
		 //SET RANGE OF VALUES 
		 lblstart = new JLabel("Start");
		 lblstart.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		 lblstart.setBounds(425, 17, 34, 16);
		 GraphPanel.add(lblstart);
		 
		 lblEnd = new JLabel("End");
		 lblEnd.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		 lblEnd.setBounds(489, 18, 34, 16);
		 GraphPanel.add(lblEnd);
		 
		 lblIncrement = new JLabel("Increment");
		 lblIncrement.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		 lblIncrement.setBounds(535, 17, 81, 16);
		 GraphPanel.add(lblIncrement);
		 
		 start = new JTextField();
		 start.setBounds(417, 38, 52, 40);
		 start.setText("0");
		 GraphPanel.add(start);
		 start.setColumns(10);
		 
		 end = new JTextField();
		 end.setColumns(10);
		 end.setText("15");
		 end.setBounds(481, 38, 52, 40);
		 GraphPanel.add(end);
		 
		 increment = new JTextField();
		 increment.setColumns(10);
		 increment.setText("1");
		 increment.setBounds(545, 38, 52, 40);
		 GraphPanel.add(increment);

		 
		 table = new JTable();
		 table.setBounds(417, 122, 199, 450);
		 GraphPanel.add(table);
		 
		 JScrollPane tablescroll = new JScrollPane(table);
		 tablescroll.setBounds(425, 122, 202, 299);
		 GraphPanel.add(tablescroll);
		 table.setModel(new javax.swing.table.DefaultTableModel());
		 
		 table.setModel(new javax.swing.table.DefaultTableModel(
		            new Object [][] {},
		            new String [] { "X", "f(x)"}
		        ));
		 
		 keyboardPanel = new JPanel();
		 keyboardPanel.setBounds(0, 391, 362, 281);
		 getContentPane().add(keyboardPanel);
		 keyboardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

	}
	
	// DRAW THE TABLE WHICH HAS ALL F(X) VALUES
	private void drawtheTable() {
		End=Double.parseDouble(end.getText());
        Start=Double.parseDouble(start.getText());
        Inc=Double.parseDouble(increment.getText());
        rows=(int)Math.round(((End-Start)/Inc)+0.5);        
        String[][] str=new String [rows][2];
        int i=0;
        for (double d=Start;i<rows;d+=Inc,i++)
        {
            str[i][0]=Double.toString(d);
        }
        for (i=0;i<rows;i++)
        {
        	    expr=txtEnterFxHere.getText();
			Double fx=es.evaluate(expr,es,str[i][0]);
			str[i][1]=Double.toString(fx);
        }
       table.setModel(new DefaultTableModel
           (str,new String []{"X", "f(x)"}));
       graphingExpr(str);
	}
	
	//SET THE COLOR OF THE EXPRESSION
	private void setColors()
    {
        Color[] color2 = {Color.RED, Color.BLUE, Color.GREEN,
	            Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.BLACK};
        JLabel[] labels = {aColor,bColor,cColor,dColor,eColor,fColor,gColor};
        BasicStroke Stroke=new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        for(int i=0;i<labels.length;i++)
        {
            Graphics2D Colors = (Graphics2D)labels[i].getGraphics();
            Colors.setColor(color2[i]);
            Colors.setStroke(Stroke);
            Colors.drawLine(28,13,75,13);
        }
    }
	
	
	//DRAW THE PLOTS
	private void graphingExpr(String[][] str) {
		
		 Color[] color = {Color.RED, Color.BLUE, Color.GREEN,
		            Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.BLACK};
		
        Graphics2D graphics = (Graphics2D) graph.getGraphics();    
        graphics.setColor(color[function]);
        double y;double x1;int length=graph.getWidth();int Y;Double p;   String x;            

        int[][] Points = new int[length+1][2];
        xmin=Double.parseDouble(Xmin.getText());
        xmax=Double.parseDouble(Xmax.getText());

        for (int i=0;i<=length;i++)
        {
            x1=(((1.0*i/length)*(xmax-xmin))+xmin);
			x=Double.toString((double) x1);
            p=es.evaluate(expr,es,x);
            if(es.flag==-1) {
            		txtEnterFxHere.setText("invalid input");
				continue;
            }
            y=p;            
            Y=evalY(y);
            Points[i][0]=i;
            Points[i][1]=Y;
            
            
        }
      
        //DRAW ALL THE POINTS
        for (int i=1;i<Points.length;i+=1)
        {
        		if(Points[i][0]-Points[i-1][0]==1&&Math.abs(Points[i][1]-Points[i-1][1])<graph.getHeight()*3)
            {
        			graphics.drawLine(Points[i-1][0],Points[i-1][1],Points[i][0],Points[i][1]);       
            }
        		//no need of threads, slows it down
//                    try {
//                        Thread.sleep(10);
//                    } catch (Exception e) {}
        } 
	}

	private int evalY(double y) {
		int height=graph.getHeight();int result;
        ymin=Double.parseDouble(Ymin.getText());
        ymax=Double.parseDouble(Ymax.getText());
        result=(int)Math.round(height-(((y-ymin)/(ymax-ymin))*height));
		return result;
	}

	//SET THE AXIS
	private void drawaxisAction(ActionEvent e) {
		if(Axes) {
            Axes=false;
        }else{
            Axes=true;
        }
        plotting();
	}

	//SET THE GRID
	private void drawgridAction(ActionEvent e) {
		if(Grid) {
            Grid=false;
        }else{
            Grid=true;
        }
		plotting();
	}
	
	//SET THE GRAPH BEFORE PLOTTING
    public void plotting()
    {
        cleargraph();
        drawAxes();
        drawGrid();      
    }
    
    //DRAW THE GRID
	private void drawGrid() {
	        if (Grid){
	        		int length=graph.getWidth();
		        int height=graph.getHeight();
		        xmin=Double.parseDouble(Xmin.getText());
		        xmax=Double.parseDouble(Xmax.getText());
		        ymin=Double.parseDouble(Ymin.getText());
		        ymax=Double.parseDouble(Ymax.getText());
		
		        Graphics graphics = graph.getGraphics();
		        BasicStroke str=new BasicStroke(gridWidth,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND);
		        Graphics2D g2D = (Graphics2D) graphics;
		        g2D.setColor(gridColor);
		        g2D.setStroke(str);
		        
		        //Vertical bars
		        int x;
		        double xRange=xmax-xmin;        
		        int xminInt=(int)Math.round(xmin);
		        int xmaxInt=(int)Math.round(xmax);
		        for(int i=xminInt;i<=xmaxInt;i+=Math.round((xRange/100)+.5))
		        {
		            x=(int)Math.round((length*(i-xmin)/xRange));
		            graphics.drawLine(x,0,x,height);
		        }
		        //Horizontal Bars
		        int y;
		        double yRange=ymax-ymin;
		        int yminInt=(int)Math.round(ymin);
		        int ymaxInt=(int)Math.round(ymax);
		        for(int i=yminInt;i<=ymaxInt;i+=Math.round((yRange/100)+.5))
		        {
		            y=(int)Math.round(height-(height*(i-ymin)/yRange));
		            graphics.drawLine(0,y,length,y);
		        }
		     }
		
	}

	//DRAW THE AXIS
	private void drawAxes() {
		if (Axes)
		{
	        int length=graph.getWidth();
	        int height=graph.getHeight();
	        xmin=Double.parseDouble(Xmin.getText());
	        xmax=Double.parseDouble(Xmax.getText());
	        ymin=Double.parseDouble(Ymin.getText());
	        ymax=Double.parseDouble(Ymax.getText());
	
	        Graphics graphics = graph.getGraphics();
	        BasicStroke str=new BasicStroke(axisWidth,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND);
	        Graphics2D g2D = (Graphics2D) graphics;
	        g2D.setColor(axesColor);
	        g2D.setStroke(str);
	
	        //Horizontal Bars
	        int y;
	        double yRange=ymax-ymin;  
	        y=(int)Math.round(height-(height*(0.0-ymin)/yRange));
	        graphics.drawLine(0,y,length,y);
	        //Vertical bars
	        int x;
	        double xRange=xmax-xmin;        
	        x=(int)Math.round((length*(0.0-xmin)/xRange));
	        graphics.drawLine(x,0,x,height);
        }
		
	}

	//CLEAR THE GRAPH
	private void cleargraph() {
		int length=graph.getWidth();
		int height=graph.getHeight();
        Graphics2D graphics = (Graphics2D)graph.getGraphics();
        graphics.setColor(graphColor);
        graphics.fillRect(0,0,length,height);	
	}
}
