package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class BookReaderController {
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
	}
	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		JRadioButton buttAlph = new JRadioButton("Alphabetic");
		JRadioButton buttFreq = new JRadioButton("Frequency");
		JTextField textField = new JTextField(20);
		JButton buttFind = new JButton("Find"); 
		ButtonGroup bg = new ButtonGroup();
		bg.add(buttAlph);
		bg.add(buttFreq);
		buttAlph.setSelected(true);
		
		
		SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<Map.Entry<String, Integer>>(counter.getWordList());
		JList<Map.Entry<String, Integer>> listView = new JList<>(listModel);
		listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		buttAlph.addActionListener(event -> {
			listModel.sort((a,b) -> a.getKey().compareTo(b.getKey()));
		});
		
		buttFreq.addActionListener(event -> {
			listModel.sort(new WordCountComparator());
		});
		
		buttFind.addActionListener(event -> {
			boolean found = false;
			System.out.println(textField.getText());
			for(int i = 0; i<listModel.getSize();i++) {
				if(listModel.getElementAt(i).getKey().startsWith(textField.getText().toLowerCase().strip())){
					found = true;
					System.out.println(listModel.getElementAt(i).getKey());
					listView.ensureIndexIsVisible(i);
					listView.setSelectedIndex(i);
					break;
				}
			}
			if(!found) {
				JOptionPane.showMessageDialog(null, "Could not find the word: "+textField.getText().toLowerCase().strip(), "Warning", JOptionPane.WARNING_MESSAGE);
			}
			});
		
		JPanel southPanel = new JPanel();
		southPanel.add(buttAlph);
		southPanel.add(buttFreq);
		southPanel.add(textField);
		southPanel.add(buttFind);
		JScrollPane scrollPane = new JScrollPane(listView);
		scrollPane.setPreferredSize(new Dimension(700, 500));
		scrollPane.setBorder(new EmptyBorder(5,10,5,10));
				
		pane.add(scrollPane, BorderLayout.CENTER);
		pane.add(southPanel, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
	}
}