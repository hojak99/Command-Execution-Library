import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cmd {

	private StringBuffer buffer;
	private Process process;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private StringBuffer readBuffer;
	private File file;

	public String returnInputCmd(String cmdOption, String inputCommand) {

		buffer = new StringBuffer();

		buffer.append("cmd.exe ");
		buffer.append(cmdOption + " ");
		buffer.append(inputCommand);

		return buffer.toString();
	}

	public String execCmd(String cmd) {
		try {
			process = Runtime.getRuntime().exec(cmd);
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line = null;
			readBuffer = new StringBuffer();

			while ((line = bufferedReader.readLine()) != null) {
				readBuffer.append(line);
				readBuffer.append("\r\n");
			}

			return readBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	public File makeResult2File(String result, String fileName, String filePath) {

		try {
			file = new File(filePath + fileName);
			bufferedWriter = new BufferedWriter(new FileWriter(file, false));

			bufferedWriter.write(result);
			bufferedWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		return file;
	}
}