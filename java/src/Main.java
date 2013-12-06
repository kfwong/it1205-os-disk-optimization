import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		*/
		
		int previousCylinder = 1500;
		int currentCylinder = 857;
		ArrayList<Integer> inputSequence = new ArrayList<Integer>();
		inputSequence.add(751);
		inputSequence.add(4505);
		inputSequence.add(757);
		inputSequence.add(3506);
		inputSequence.add(4578);
		inputSequence.add(2200);
		inputSequence.add(351);
		inputSequence.add(22);
		inputSequence.add(1058);
		
		Algorithm algo = new Algorithm(previousCylinder, currentCylinder, inputSequence);
		
		System.out.println(algo.scan());
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
