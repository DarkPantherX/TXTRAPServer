package ch.ilikechickenwings.TXTRAP;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ch.ilikechickenwings.TXTRAP.Entity.Player;
import ch.ilikechickenwings.TXTRAP.Frames.NewWorldFrame;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;
import ch.ilikechickenwings.TXTRAP.Interface.WorkTimer;

public class ServerProtocol implements Runnable, Processable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private boolean asdf = true;

	private Processable processable;
	private Player player;
	private WorldFrame worldFrame;

	public ServerProtocol(Socket accept, WorldFrame wF) {
		processable = this;
		this.setSocket(accept);
		setWorldFrame(wF);

		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			NetInput net = ((NetInput) ois.readObject());
			System.out.println(net.getTxt());
			sendMessage(new NetInput(
					"Welcome to the online version of the game, please type 'login <username> <password>' or 'new'",
					Console.startOutput, true));

		} catch (IOException e) {
			close();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public synchronized void processInput(String[] s, ServerProtocol sP) {

		switch (s[0].toLowerCase()) {
		case "login":
			if (s.length == 3) {
				String s1 = (new StringBuilder())
						.append(System.getProperty("user.home"))
						.append("/.TXTRAPServer/".concat(s[1].concat(".dat").toLowerCase()))
						.toString();
				File file0 = new File(s1);
				if (file0.exists()) {

					try {
						ObjectInputStream ois2 = new ObjectInputStream(
								new FileInputStream(s1));
						Player p = (Player) ois2.readObject();

						if (p.password.equals(s[2])) {
							setPlayer(p);
							player.getCity().getEntities().add(player);
							processable = player.processable;

							if (processable instanceof WorkTimer) {
								WorkTimer wT = (WorkTimer) processable;
								wT.setWorldFrame(worldFrame);
								wT.setsP(this);
								new Thread(wT).start();
							}

							TXTRAPServer.worldFrame.loadedGame(this);
							

						} else {
							sendMessage(new NetInput("Invalid password",
									Console.errorOutput));

						}
						ois2.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {

					sendMessage(new NetInput("No player found with this name",
							Console.errorOutput));

				}
			}
			break;
		case "new":
			setProcessable(new NewWorldFrame(this));
			break;

		}

	}

	@Override
	public void run() {
		System.out.println("Just connected to "
				+ socket.getRemoteSocketAddress());

		while (asdf) {

			try {

				NetInput mei = (NetInput) ois.readObject();
				processable.processInput(mei.getTxt().split(" "), this);

				Thread.sleep(50L);
			} catch (EOFException e) {
				close();

			} catch (IOException e) {
				close();
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				close();
				e.printStackTrace();
			}
		}

	}

	@Override
	public String getStartInput() {
		return "You are somewhere you shouldn't be... ask the admins...";
	}


	public synchronized void close() {

		try {
			if (player != null) {
				File file0 = new File((new StringBuilder())
						.append(System.getProperty("user.home"))
						.append("/.TXTRAPServer/".concat((player.getName()
								.concat(".dat")).toLowerCase())).toString());
				if (!file0.exists()) {
					File file1 = new File((new StringBuilder())
							.append(System.getProperty("user.home"))
							.append("/.TXTRAPServer").toString());
					file1.mkdir();
				}

				String s1 = new StringBuilder()
						.append(System.getProperty("user.home"))
						.append("/.TXTRAPServer/")
						.append((player.getName().concat(".dat")).toLowerCase()).toString();
				try {
					player.processable = processable;
					// String s0[]={"stop"};
					// processable.processInput(s0, this);
					ObjectOutputStream oos = new ObjectOutputStream(
							new FileOutputStream(s1));
					oos.writeObject(player);
					oos.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				player.getCity().getEntities().remove(player);
				WorldFrame.players.remove(this);
				sendMessageToAll(new NetInput(getPlayer().getName()
						+ " left the game",Console.startOutput));
			}
			oos.close();
			ois.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		asdf = false;
		WorldFrame.players.remove(this);
		System.out.println("Disconected from: "
				+ socket.getRemoteSocketAddress());

	}

	
	public void sendMessageToAll(NetInput mes) {
		for (ServerProtocol s : WorldFrame.players) {

			s.sendMessage(mes);

		}

	}
	
	
	public synchronized void sendMessage(NetInput mes) {

		if (socket.isConnected()) {
			try {
				oos.writeObject(mes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			close();

		}
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket
	 *            the socket to set
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Processable getProcessable() {
		return processable;
	}

	public void setProcessable(Processable processable) {
		this.processable = processable;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public WorldFrame getWorldFrame() {
		return worldFrame;
	}

	public void setWorldFrame(WorldFrame worldFrame) {
		this.worldFrame = worldFrame;
	}

}
