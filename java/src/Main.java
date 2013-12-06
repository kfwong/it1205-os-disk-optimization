import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

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
		/*
		int previousCylinder = 700;
		int currentCylinder = 857;
		ArrayList<Integer> inputSequence = new ArrayList<Integer>();
		inputSequence.add(751);
		inputSequence.add(4505);
		inputSequence.add(757);
		inputSequence.add(3506);
		inputSequence.add(4578);
		inputSequence.add(50);
		inputSequence.add(351);
		inputSequence.add(22);
		inputSequence.add(1058);
		
		DiskOptimizationAlgorithm algo = new DiskOptimizationAlgorithm(previousCylinder, currentCylinder, inputSequence);
		
		System.out.println(algo.fcfs());
		System.out.println(algo.sstf());
		System.out.println(algo.scan());
		System.out.println(algo.look());
		System.out.println(algo.cscan());
		System.out.println(algo.clook());
		*/
		
		Integer[] i = {1,2,3,4,5};
		Integer[] j = {7,8,9,0,1};
		Integer[] k = {2,3,4,5,6};
		
		List<Integer[]> a = new ArrayList<Integer[]>();
		a.add(i);
		a.add(j);
		a.add(k);
		
		Integer[][] b = a.toArray(new Integer[a.size()][i.length]);
		
		for(int x = 0; x<b.length;x++){
			for(int y=0; y<i.length; y++){
				System.out.print(b[x][y]);
			}
			System.out.println();
		}
		
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
