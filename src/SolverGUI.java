import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SolverGUI{
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel userPanel;
	
	//Inverting op amp stuff
	private JLabel invertingOpAmpHeader;
	
	//Noninverting op amp stuff
	private NonInvertingOpAmp ni; 
	private JLabel noninvertingOpAmpHeader;
	private JLabel noninvertingOpAmpGainLabel;
	private JTextField noninvertingOpAmpGainField;
	private JButton noninvertingOpAmpGainButton;
	private JLabel noninvertingSummary;
	private JButton noninvertingSubmit;
	private JLabel noninvertingComponents;
	
	public SolverGUI() {
		initialize();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SolverGUI runGUI = new SolverGUI();
				System.out.println("running");
			}
			
		}
		);
	}
	
	private void initialize() {
		int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int screenWidth  = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		frame = new JFrame();
		frame.setTitle("Solver");
		frame.setSize(screenWidth, screenHeight);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel(new BorderLayout());
		userPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		Insets originalInsets = new Insets(0,0,0,0);
		
		//INVERTING OP AMP STUFF
		invertingOpAmpHeader = new JLabel("Inverting Op Amps");
		c.gridx = 2;
		c.gridy = 0;
		c.ipady = 10;
		c.gridwidth = 1;
		c.insets = new Insets(0,100,0,0);
		invertingOpAmpHeader.setFont (invertingOpAmpHeader.getFont ().deriveFont (20.0f));
		userPanel.add(invertingOpAmpHeader, c);
		
		//NON INVERTING OP AMP STUFF
		ni = new NonInvertingOpAmp();
		noninvertingOpAmpHeader = new JLabel("Non-Inverting Op Amps");
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 10;
		c.gridwidth = 1;
		noninvertingOpAmpHeader.setFont (noninvertingOpAmpHeader.getFont ().deriveFont (20.0f));
		userPanel.add(noninvertingOpAmpHeader, c);
		
		noninvertingOpAmpGainLabel = new JLabel("Desired Gain: ");
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 10;
		c.gridwidth = 1;
		c.insets = new Insets(0,-40,0,0);
		//c.anchor = GridBagConstraints.LINE_START;
		userPanel.add(noninvertingOpAmpGainLabel, c);
		
		noninvertingOpAmpGainField = new JTextField("");
		noninvertingOpAmpGainField.setPreferredSize(new Dimension(50,15));
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 10;
		c.gridwidth = 1;
		c.insets = new Insets(0,100,0,0);
		//c.anchor = GridBagConstraints.LINE_START;
		userPanel.add(noninvertingOpAmpGainField, c);
		
		noninvertingOpAmpGainButton = new JButton("Enter");
		noninvertingOpAmpGainButton.setPreferredSize(new Dimension(70, 15));
		noninvertingOpAmpGainButton.addActionListener(new handleNonInvertingOpAmpGainEntered()); 
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 0;
		c.gridwidth = 0;
		c.insets = new Insets(0,-30,0,0);
		//c.anchor = GridBagConstraints.LINE_START;
		userPanel.add(noninvertingOpAmpGainButton, c);
		
		noninvertingSummary = new JLabel("<html>Summary:<br/>Gain: " + ni.getGain() + "</html>");
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0,100,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		userPanel.add(noninvertingSummary,c);
		
		noninvertingSubmit = new JButton("Submit");
		noninvertingSubmit.addActionListener(new handleNonInvertingSubmit());
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10,100,0,0);
		c.anchor = GridBagConstraints.LINE_START;
		userPanel.add(noninvertingSubmit, c);
	
		noninvertingComponents = new JLabel("<html>R1: " + ni.getR1() + "<br/>R2: " + ni.getR2() + "</html>");
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10,100,0,0);
		userPanel.add(noninvertingComponents, c);
		
		mainPanel.add(userPanel, BorderLayout.NORTH);
		frame.add(mainPanel);
		
		
	}

	class handleNonInvertingOpAmpGainEntered implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				ni.setGain(Double.parseDouble(noninvertingOpAmpGainField.getText()));
				noninvertingSummary.setText("<html>Summary:<br/>Gain: " + ni.getGain() + "</html>");
				
			}
			catch(Exception f) {
				
			}
			finally {
				noninvertingOpAmpGainField.setText("");
			}
		}
	}
	
	class handleNonInvertingSubmit implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				ni.gainSolver(ni.getGain(), 0.5);
				noninvertingComponents.setText("<html>R1: " + ni.getR1() + "<br/>R2: " + ni.getR2() + "</html>");
				System.out.println("hello there test test");
			}
			catch(Exception f) {
				
			}
			
		}
		
	}
	
	
}