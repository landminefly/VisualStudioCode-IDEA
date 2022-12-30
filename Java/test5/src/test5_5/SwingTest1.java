package test5_5;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class SwingTest1
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            var frame = new SimpleFrame();
            frame.setTitle("ºŒ»ª");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class SimpleFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    public SimpleFrame()
    {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        add(new SimpleComponent());
    }
}

class SimpleComponent extends JComponent
{
    private static final int MESSAGE_X = 75;
    private static final int MESSAGE_Y = 100;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    @Override
    public void paintComponents(Graphics g)
    {
        g.drawString("Diana",MESSAGE_X,MESSAGE_Y);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}
