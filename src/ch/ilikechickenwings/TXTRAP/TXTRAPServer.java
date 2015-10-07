package ch.ilikechickenwings.TXTRAP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;

import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;

public class TXTRAPServer {
	public static WorldFrame worldFrame;

	public static void main(String[] args) {

		boolean listening = true;
		String s1 = (new StringBuilder())
				.append(System.getProperty("user.home"))
				.append("/.TXTRAPServer/".concat("world.w")).toString();
		File file0 = new File(s1);
		if (file0.exists()) {

			try {

				System.out.println("Loaded World");
				ObjectInputStream ois2 = new ObjectInputStream(
						new FileInputStream(s1));
				WorldFrame wF = (WorldFrame) ois2.readObject();
				worldFrame = wF;
				new Thread(worldFrame).start();

				ois2.close();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Generating new world");
			worldFrame = new WorldFrame("World");

		}

		try {

			ServerSocket serverSocket = new ServerSocket(33333);
			while (listening) {

				ServerProtocol pro = new ServerProtocol(serverSocket.accept(),
						worldFrame);
				WorldFrame.players.add(pro);
				new Thread(pro).start();

				Thread.sleep(50L);
			}
			serverSocket.close();

		} catch (IOException e) {
			System.err.println("Could not listen on port " + "33333");
			System.exit(-1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
