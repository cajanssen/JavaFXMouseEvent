package jansproj.basicfx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MouseEventExample extends Application 
{
	public MouseEventExample() 
	{
	}

	// this application waits for and reacts to mouse click events
	
	@Override
	public void start(Stage stage) throws Exception 
	{
		// Stage, Scene, root Node - standard stuff common to JavaFX programs
		stage.setTitle("Mouse Event Example");
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);

		int canvasWidth = 1000;
		int canvasHeight = 500;
		int canvasXCenter = canvasWidth / 2;
		int canvasYCenter = canvasHeight / 2;

		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		
		// rather than create a large tree of nodes, create a tree of only 
		// one node - canvas - and draw on that
		root.getChildren().add(canvas);
		
		double blackCircleRadius = 250;
		double blueCircleRadius = 200;
		double redCircleRadius = 150;
		double goldCircleRadius = 100;

		// make concentric circles
		// drawing point for strokeOval() is the upper left corner of bounding box, not the center
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setLineWidth(4.0);
		gc.setStroke(Color.BLACK);
		gc.strokeOval((canvasXCenter - blackCircleRadius), (canvasYCenter - blackCircleRadius), blackCircleRadius*2, blackCircleRadius*2);
	 	gc.setStroke(Color.BLUE);
		gc.strokeOval((canvasXCenter - blueCircleRadius), (canvasYCenter - blueCircleRadius), blueCircleRadius*2, blueCircleRadius*2);
		gc.setStroke(Color.RED);
		gc.strokeOval((canvasXCenter - redCircleRadius), (canvasYCenter - redCircleRadius), redCircleRadius*2, redCircleRadius*2);
		gc.setStroke(Color.GOLD);
		gc.strokeOval((canvasXCenter - goldCircleRadius), (canvasYCenter - goldCircleRadius), goldCircleRadius*2, goldCircleRadius*2);

		
		// attach a Mouse Click event handler to the scene
		// rather than create the object elsewhere and pass it in to the setOnMouseClicked() 
		// method, define the event handler right here with 
		// an anonymous inner class - common practice
		scene.setOnMouseClicked(
			new EventHandler<MouseEvent>()
			{
				public void handle(MouseEvent e)
				{
					System.out.println("x= " + e.getX() + " y= " + e.getY());
					double distance = Math.sqrt(((canvasXCenter - e.getX()) * (canvasXCenter - e.getX())) + (canvasYCenter - e.getY()) *(canvasYCenter - e.getY()));
					String location = "outside";
					if ( distance < blackCircleRadius )
						location = "black circle";
					if ( distance < blueCircleRadius )
						location = "blue circle";
					if ( distance < redCircleRadius )
						location = "red circle";
					if ( distance < goldCircleRadius )
						location = "gold circle";
					System.out.println(location);
				}
			});

		// calling show() on the Stage is a standard requirement for a JavaFX program
		stage.show();
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}
