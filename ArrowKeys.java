import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class ArrowKeys {
	public static JFrame frame = new JFrame();

	public ArrowKeys() {
		//creates a window using jframe
		frame.setVisible(true);
		//set frame to be really small
		frame.setSize(1, 1);
		//has to be focusable to get input
		frame.setFocusable(true);
		//create a panel
		JPanel panel = new JPanel();



		frame.addKeyListener(new KeyListener() {


			@Override
			//this must exist for the keylistener
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
			}


			@Override
			//on a key pressed we check if its an arrow key or not
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
					//if its right we send back a 1
				case KeyEvent.VK_RIGHT:
					DropZone.setX(1);
					break;
					
				case KeyEvent.VK_LEFT:
					// if its left we send back a -1
					DropZone.setX(-1);
					break;
				case KeyEvent.VK_SPACE:
				//if its space when send back a 0
					DropZone.setX(0);


				}
				
			}



		});

		frame.add(panel);
	}
	
	public static void closewindow(){
		//closes the jframe window
		frame.dispose();
	}

}