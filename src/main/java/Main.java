

import javax.swing.JFrame;
import Game.Game;

public class Main {
	// execute application
		public static void main( String args[] )
		{
			JFrame frame = new JFrame( "Avoid the Asteroid!" );
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

			// create game
			Game g = new Game(); 
			
			frame.add( g );				// add graphics to frame
			frame.setSize( 1280, 720 ); // set frame size
			frame.setVisible( true ); 	// display frame
		} // end main
}
