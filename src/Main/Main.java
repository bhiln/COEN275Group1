package Main;

import javax.swing.JFrame;

import GameView.GameView;

public class Main {
	// execute application
		public static void main( String args[] )
		{
			JFrame frame = new JFrame( "Avoid the Asteroid!" );
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

			GameView g = new GameView(); 
			frame.add( g );
			frame.setSize( 1280, 720 ); // set frame size
			frame.setVisible( true ); // display frame
//			g.createAsteroid(10);
		} // end main
}
