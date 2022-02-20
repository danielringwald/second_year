package textproc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BookReaderController {
	public BookReaderController(GeneralWordCounter counter) {
		
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));	
	}

	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		SortedListModel<Map.Entry<String, Integer>> sortedList = new SortedListModel<Map.Entry<String, Integer>>(counter.getWordList());
		JList<Entry<String,Integer>> jList = new JList<Entry<String, Integer>>(sortedList);
		JScrollPane jsp = new JScrollPane(jList);
		
		JPanel jp = new JPanel();
		ButtonGroup bg = new ButtonGroup();						//Samma buttongroup gör att bara en kan vara selected
		JRadioButton jb1 = new JRadioButton("Alpabetic");
		JRadioButton jb2 = new JRadioButton("Frequency");
		bg.add(jb1);
		bg.add(jb2);
		JButton textButton = new JButton("Find");
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(100,24));;
		jp.add(jb1);
		jp.add(jb2);
		jp.add(textButton);
		jp.add(textField);
		pane.add(jp, BorderLayout.SOUTH);
		
		jb1.addActionListener(e -> sortedList.sort((e1,e2) -> e1.getKey().compareTo(e2.getKey())));
		jb2.addActionListener(e ->sortedList.sort((e1,e2) -> e2.getValue()-e1.getValue()));
		
		textButton.addActionListener(e -> {
			int n = 0;
			for (int i = 0; i < sortedList.getSize(); i++) {
				Entry<String, Integer> entry = sortedList.getElementAt(i);
				if (entry.getKey().contentEquals(textField.getText().toLowerCase().trim())) {		//Struntar i versaler och mellansteg
					jList.ensureIndexIsVisible(i);
					jList.setSelectedIndex(i);
					jList.setSelectionBackground(Color.GREEN);
					n = 1;
					break;
				}
			}
			if (n == 0) {
			JOptionPane.showMessageDialog(frame, "Ordet finns ej");				//Använder en int för att hålla kolla på ifall
			}																	//Vi hittat ordet eller inte, annars poppar skit upp
		});
		
		
		
		
		pane.add(jsp,BorderLayout.CENTER);
//		pane är en behållarkomponent till vilken de övriga komponenterna
//		(listvy, knappar etc.) ska läggas till.
		frame.pack();
		frame.setVisible(true);
		
		
		}

}
