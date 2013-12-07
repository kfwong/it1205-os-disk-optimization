import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JTextField;
import java.awt.Color;

import org.apache.commons.lang3.StringUtils;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.CardLayout;

import javax.swing.ImageIcon;

import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JLabel lblError;
	private JTextField txtPrevious;
	private JTextField txtCurrent;
	private JTextField txtSequence;
	private DiskMovementPlot diskMovementPlot;
	private JLabel lblFCFS = new JLabel("");
	private JLabel lblSSTF = new JLabel("");
	private JLabel lblSCAN = new JLabel("");
	private JLabel lblLOOK = new JLabel("");
	private JLabel lblCSCAN = new JLabel("");
	private JLabel lblCLOOK = new JLabel("");
	private JCheckBox cbFCFS = new JCheckBox("Plot Graph");
	private JCheckBox cbSSTF = new JCheckBox("Plot Graph");
	private JCheckBox cbSCAN = new JCheckBox("Plot Graph");
	private JCheckBox cbLOOK = new JCheckBox("Plot Graph");
	private JCheckBox cbCSCAN = new JCheckBox("Plot Graph");
	private JCheckBox cbCLOOK = new JCheckBox("Plot Graph");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

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

	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Disk Optimization");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1032, 677);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,}));

		lblError = new JLabel("Error: Malform sequence.");
		lblError.setIcon(new ImageIcon(Main.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		panel.add(lblError, "2, 1, 5, 1");
		lblError.setForeground(Color.RED);

		JLabel lblPrevious = new JLabel("Previous");
		panel.add(lblPrevious, "2, 3, right, default");

		txtPrevious = new JTextField();
		txtPrevious.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refresh();
			}
		});
		panel.add(txtPrevious, "4, 3, 3, 1, fill, default");
		txtPrevious.setColumns(10);
		txtPrevious.setText("1578");

		JLabel lblCurrent = new JLabel("Current");
		panel.add(lblCurrent, "2, 5, right, default");

		txtCurrent = new JTextField();
		txtCurrent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refresh();
			}
		});
		panel.add(txtCurrent, "4, 5, 3, 1, fill, default");
		txtCurrent.setColumns(10);
		txtCurrent.setText("3098");

		JLabel lblSequence = new JLabel("Sequence");
		panel.add(lblSequence, "2, 7, right, default");

		txtSequence = new JTextField();
		txtSequence.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refresh();
			}
		});
		panel.add(txtSequence, "4, 7, 3, 1, fill, default");
		txtSequence.setColumns(10);
		txtSequence.setText("751,4505,757,3506,4578,50,351,22,1058");

		JLabel lblFcfs = new JLabel("FCFS");
		lblFcfs.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblFcfs, "2, 9");

		panel.add(lblFCFS, "4, 9");
		cbFCFS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});

		panel.add(cbFCFS, "6, 9");

		JLabel lblSstf = new JLabel("SSTF");
		lblSstf.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblSstf, "2, 11, right, default");

		panel.add(lblSSTF, "4, 11");
		cbSSTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		
		panel.add(cbSSTF, "6, 11");

		JLabel lblScan = new JLabel("SCAN");
		lblScan.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblScan, "2, 13");

		panel.add(lblSCAN, "4, 13");
		cbSCAN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});

		panel.add(cbSCAN, "6, 13");

		JLabel lblLook = new JLabel("LOOK");
		lblLook.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblLook, "2, 15");

		panel.add(lblLOOK, "4, 15");
		cbLOOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});

		panel.add(cbLOOK, "6, 15");

		JLabel lblCscan = new JLabel("C-SCAN");
		lblCscan.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblCscan, "2, 17");

		lblCSCAN.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(lblCSCAN, "4, 17");
		cbCSCAN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});

		panel.add(cbCSCAN, "6, 17");
		
		JLabel lblClook = new JLabel("C-LOOK");
		panel.add(lblClook, "2, 19, right, default");
		
		panel.add(lblCLOOK, "4, 19");
		cbCLOOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		
		panel.add(cbCLOOK, "6, 19");

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new CardLayout(0, 0));

		refresh();
	}

	private void refresh() {
		int previous = 0;
		int current = 0;
		List<Integer> sequence = new ArrayList<Integer>();

		try {
			try {
				previous = Integer.parseInt(txtPrevious.getText());
				lblError.setText("");
			} catch (NumberFormatException ex) {
				throw new Exception("Previous: Invalid number format. Only integer allowed.");
			}

			try {
				current = Integer.parseInt(txtCurrent.getText());
				lblError.setText("");
			} catch (NumberFormatException ex) {
				throw new Exception("Current: Invalid number format. Only integer allowed.");
			}

			try {
				sequence = new ArrayList<Integer>();
				for (String term : txtSequence.getText().split(",")) {
					sequence.add(Integer.parseInt(term));
				}
				lblError.setText("");
			} catch (NumberFormatException ex) {
				throw new Exception("Sequence: Invalid sequence format. Only integer allowed. Multiple values seperated by comma (,). i.e: 1000,2000,3000");
			}
			
			if (lblError.getText().isEmpty()) {
				lblError.setVisible(false);
				if (diskMovementPlot != null) {
					this.remove(diskMovementPlot);
					diskMovementPlot = null;
				}

				DiskOptimizationAlgorithm doa = new DiskOptimizationAlgorithm(previous, current, sequence);
				diskMovementPlot = new DiskMovementPlot(doa, 
						cbFCFS.isSelected(),
						cbSSTF.isSelected(),
						cbSCAN.isSelected(),
						cbLOOK.isSelected(),
						cbCSCAN.isSelected(),
						cbCLOOK.isSelected());
				diskMovementPlot.setBackground(Color.WHITE);
				contentPane.add(diskMovementPlot, BorderLayout.CENTER);
				
				lblFCFS.setText(StringUtils.join(doa.fcfs(), ","));
				lblSSTF.setText(StringUtils.join(doa.sstf(), ","));
				lblSCAN.setText(StringUtils.join(doa.scan(), ","));
				lblLOOK.setText(StringUtils.join(doa.look(), ","));
				lblCSCAN.setText(StringUtils.join(doa.cscan(), ","));
				lblCLOOK.setText(StringUtils.join(doa.clook(), ","));

				this.repaint();
				this.revalidate();
			}
		} catch (Exception ex) {
			lblError.setVisible(true);
			lblError.setText(ex.getMessage());
		}
	}

}
