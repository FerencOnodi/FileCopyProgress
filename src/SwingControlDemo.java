import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by ferenc on 2017.06.06..
 */
public class SwingControlDemo {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel fromPanel;
    private JPanel toPanel;
    private JPanel buttonPanel;
    private JPanel progressPanel;

    JTextField fromText = null;
    JTextField toText = null;
    JProgressBar progressBar = null;

    CopyProgress copyProgress = null;

    public SwingControlDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SwingControlDemo swingControlDemo = new SwingControlDemo();
                swingControlDemo.showPath();
                swingControlDemo.showButton();
                swingControlDemo.showProgressBar();
            }
        });
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Yo!");
        mainFrame.setSize(400, 250);
        mainFrame.setLayout(new GridLayout(6, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        headerLabel = new JLabel("Yo, copy some shit my nigga!", JLabel.CENTER);
        fromPanel = new JPanel();
        fromPanel.setLayout(new FlowLayout());
        toPanel = new JPanel();
        toPanel.setLayout(new FlowLayout());
        buttonPanel = new JPanel();
        progressPanel = new JPanel();

        mainFrame.add(headerLabel);
        mainFrame.add(fromPanel);
        mainFrame.add(toPanel);
        mainFrame.add(progressPanel);
        mainFrame.add(buttonPanel);
        mainFrame.setVisible(true);
    }

    private void showPath() {
        JLabel from = new JLabel("From: ", JLabel.LEFT);
        fromText = new JTextField("/home/ferenc/Downloads/atom-amd64.deb",20);
        JLabel to = new JLabel("TO: ", JLabel.CENTER);
        toText = new JTextField("/home/ferenc/Desktop/atom-amd64.deb",20);

        fromPanel.add(from);
        fromPanel.add(fromText);
        toPanel.add(to);
        toPanel.add(toText);

        mainFrame.setVisible(true);
    }

    private void showProgressBar() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);

        progressPanel.add(progressBar);
    }

    private void showButton() {
        JButton copy = new JButton("Copy");
        JButton cancel = new JButton("Cancel");

        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        copyProgress = new CopyProgress(fromText, toText, progressBar);
                        copyProgress.execute();
                    }
                });
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyProgress.cancel(true);
            }
        });

        buttonPanel.add(copy);
        buttonPanel.add(cancel);

        mainFrame.setVisible(true);
    }
}