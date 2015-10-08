package ch.ilikechickenwings.TXTRAP.Frames;

import java.io.File;

import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.NetInput;
import ch.ilikechickenwings.TXTRAP.ServerProtocol;
import ch.ilikechickenwings.TXTRAP.TXTRAPServer;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;
import ch.ilikechickenwings.TXTRAP.Entity.Player;

public class NewWorldFrame implements Processable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ServerProtocol sP;

	private StringBuilder name;
	private StringBuilder gameClass;
	private StringBuilder password;
	private StringBuilder password1;

	public NewWorldFrame(ServerProtocol sP1) {
		this.sP = sP1;
		sP.sendMessage(new NetInput(
				"You will create a new character on this server!",
				Console.startOutputComment, true, true));
		sP.sendMessage(new NetInput(
				"Please enter you desired name! Write 'back to go to the login screen'",
				Console.startOutput));

	}

	@Override
	public void processInput(String[] s, ServerProtocol sP) {

		if (s[0].toLowerCase().equals("back")) {
			sP.setProcessable(sP);
			sP.sendMessage(new NetInput(
					"Write 'login <playername> <password>' to login or creat a new charakter with 'new' ",
					Console.startOutputComment, true));

		} else {

			if (name == null) {
				name = new StringBuilder(s[0].toLowerCase());

				File file0 = new File((new StringBuilder())
						.append(System.getProperty("user.home"))
						.append("/.TXTRAPServer/".concat(name.toString()
								.concat(".dat"))).toString());
				if (file0.exists()) {
					sP.sendMessage(new NetInput("Player already exists!",
							Console.errorOutput));
					name = null;
				} else {
					sP.sendMessage(new NetInput(
							"\n Enter your class name, warrior",
							Console.startOutput));

				}

			} else if (gameClass == null) {
				gameClass = new StringBuilder(s[0]);

				sP.sendMessage(new NetInput("Enter your password",
						Console.startOutput));

			} else if (password == null) {
				password = new StringBuilder(s[0]);
				sP.sendMessage(new NetInput("Re-enter password",
						Console.startOutput, true, true));

			} else if (password1 == null) {
				if (password.toString().equals(s[0])) {
					sP.sendMessage(new NetInput(
							"Welcome to the new World, "
									+ name.toString()
									+ ", be prepared for a world full of adventures, you start of in the gread City Thamariel",
							Console.startOutput, true, true));

					WorldFrame f = TXTRAPServer.worldFrame;
					sP.setPlayer((new Player(name.toString(), gameClass
							.toString(), f.getCities().get(0), null, password
							.toString())));
					sP.getPlayer().getCity().getEntities().add(sP.getPlayer());
					sP.setProcessable(f);
					sP.sendMessageToAll(new NetInput(sP.getPlayer().getName()
							+ " entered the game"));
					sP.sendMessage(new NetInput(
							"Type help for available commands",
							Console.standardOutput));
					f.getPvisits().add(name.toString());

				} else {

					sP.sendMessage(new NetInput(
							"Passwords did not match, enter password again: ",Console.errorOutput,true,true));
					password = null;
					password1 = null;
				}

			}
		}
	}

	@Override
	public String getStartInput() {
		return "You shouldn't be here, ask an admin...";
	}
}
