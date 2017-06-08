import javax.swing.*;
import java.io.*;

/**
 * Created by ferenc on 2017.06.07..
 */
public class CopyProgress extends SwingWorker<Integer, Integer> {

    JTextField fromPath;
    JTextField toPath;
    JProgressBar progressBar;

    public CopyProgress(JTextField fromPath, JTextField toPath, JProgressBar progressBar) {
        this.fromPath = fromPath;
        this.toPath = toPath;
        this.progressBar = progressBar;
    }

    public void copy(String fromPath, String toPath, JProgressBar progressBar) {

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(fromPath);
            outputStream = new FileOutputStream(toPath);

            long fileLength = inputStream.available();
            byte[] buf = new byte[1024];
            int size;
            long flag = 0;
            while ((size = inputStream.read(buf)) != -1 && !isCancelled()) {
                outputStream.write(buf, 0, size);
                flag += size;
                int percent = (int) (100 * flag/fileLength);
                progressBar.setValue(percent);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected Integer doInBackground() throws Exception {
        copy(fromPath.getText(), toPath.getText(), progressBar);
        return null;
    }
}
