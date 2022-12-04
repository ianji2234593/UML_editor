import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class window {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
					window.frame.setVisible(true);	

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("UML Editor");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyCanvas canvas = MyCanvas.getInstance();
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		canvas.setLayout(new GridLayout(1, 0, 0, 0));
		
		setMenu();
		setButton();
	}
	
	private void setButton() {
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		

		UMLButtonFactory btn_factory = new UMLButtonFactory();
		
		int btn_size = btn_factory.getSize();
		
		for (int i=0; i<btn_size; i++) {
			panel.add(btn_factory.getButton(i));
		}
	}
	
	private void setMenu() {
		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();
		
		//Build the menu.
		JMenu menu1 = new JMenu("File");
		JMenu menu2 = new JMenu("Edit");
		menuBar.add(menu1);
		menuBar.add(menu2);
		
		//a group of JMenuItems on Edit
		JMenuItem item1 = new JMenuItem("Group");
		JMenuItem item2 = new JMenuItem("UnGroup");
		JMenuItem item3 = new JMenuItem("Change object name");
		item1.addMouseListener(new GroupObjectAction());
		item2.addMouseListener(new UnGroupObjectAction());
		item3.addMouseListener(new ChangeNameAction());
		menu2.add(item1);
		menu2.add(item2);
		menu2.add(item3);
			
		frame.setJMenuBar(menuBar);
	}
}
