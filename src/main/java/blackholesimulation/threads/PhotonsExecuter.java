package blackholesimulation.threads;

import blackholesimulation.controllers.SimulationController;
import blackholesimulation.space.BlackHole;
import blackholesimulation.space.SpaceObject;
import javafx.scene.shape.Circle;

public class PhotonsExecuter implements Runnable {
	
	private SimulationController controller;
	private BlackHole blackhole;
	private SpaceObject[] photons;
	private Circle[] photonsView;
	
	
	
	public PhotonsExecuter(SimulationController controller, BlackHole blackhole, SpaceObject[] photons,
			Circle[] photonsView) {
		this.controller = controller;
		this.blackhole = blackhole;
		this.photons = photons;
		this.photonsView = photonsView;
	}



	@Override
	public void run() {
		boolean goInfinity = false;
		boolean beyondScreen = false;
		while (!goInfinity && !beyondScreen) {
			for (int i = 0; i < photonsView.length; i++) {
				if (photonsView[i] == null) {
					break;
				}

				goInfinity = blackhole.pull(photons[i]);
				beyondScreen = controller.animation(photonsView[i], photons[i]);
			}
		}
	}

}
