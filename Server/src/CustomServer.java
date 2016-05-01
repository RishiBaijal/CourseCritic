import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomServer {
	public static void main(String args[]) {
		ServerSocket serverSocket = null;
		Socket socket = null;

		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		try {
			serverSocket = new ServerSocket(8888);
			// System.out.println("IP: " + socket.getInetAddress());
			System.out.println("Listening on port number 8888....");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		while (true) {
			try {
				String filename = "accel.csv";
				socket = serverSocket.accept();
				dataInputStream = new DataInputStream(socket.getInputStream());
				dataOutputStream = new DataOutputStream(
						socket.getOutputStream());
				System.out.println("IP: " + socket.getInetAddress());
				String s = dataInputStream.readUTF();
				System.out.println("message: " + s);
				try {
					FileWriter fileWriter = new FileWriter(filename);
					fileWriter.append(s);
					fileWriter.flush();
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				} finally {
					dataOutputStream
							.writeUTF("Message received loud and clear!");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (dataOutputStream != null) {
					try {
						dataOutputStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (dataInputStream != null) {
					try {
						dataInputStream.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
