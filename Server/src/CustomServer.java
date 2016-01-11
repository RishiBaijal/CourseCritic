import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
			//System.out.println("IP: " + socket.getInetAddress());
			System.out.println("Listening on port number 8888....");
		} catch (Exception e) {
			e.printStackTrace();
			return ;
		}

		while (true) {
			try {
				socket = serverSocket.accept();
				dataInputStream = new DataInputStream(socket.getInputStream());
				dataOutputStream = new DataOutputStream(
						socket.getOutputStream());
				System.out.println("IP: " + socket.getInetAddress());
				System.out.println("message: " + dataInputStream.readUTF());
				dataOutputStream.writeUTF("Message received loud and clear!");

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
