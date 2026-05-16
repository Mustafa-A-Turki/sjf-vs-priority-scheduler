package app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GanttPanel extends JPanel {

    private List<String> timeline;

    public GanttPanel(List<String> timeline) {

        this.timeline = timeline;

        int totalWidth = 60;

        if (timeline != null && !timeline.isEmpty()) {

            List<String> blocks = new ArrayList<>();

            String current = timeline.get(0);

            blocks.add(current);

            for (int i = 1; i < timeline.size(); i++) {

                if (!timeline.get(i).equals(current)) {

                    current = timeline.get(i);

                    blocks.add(current);
                }
            }

            totalWidth += timeline.size() * 45;

            totalWidth += 300;
        }

        setPreferredSize(new Dimension(totalWidth, 220));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (timeline == null || timeline.isEmpty())
            return;

        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(new Font("Arial", Font.BOLD, 14));

        List<String> blocks = new ArrayList<>();
        List<Integer> times = new ArrayList<>();

        String current = timeline.get(0);

        blocks.add(current);
        times.add(0);

        for (int i = 1; i < timeline.size(); i++) {

            if (!timeline.get(i).equals(current)) {

                current = timeline.get(i);

                blocks.add(current);
                times.add(i);
            }
        }

        times.add(timeline.size());

        int x = 30;
        int y = 70;
        int height = 60;

        for (int i = 0; i < blocks.size(); i++) {

            int duration = times.get(i + 1) - times.get(i);

            int width = duration * 45;

            g2.setColor(new Color(173, 216, 230));
            g2.fillRect(x, y, width, height);

            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, width, height);

            g2.drawString(
                    blocks.get(i),
                    x + width / 2 - 10,
                    y + 35
            );

            g2.drawString(
                    String.valueOf(times.get(i)),
                    x,
                    y + 85
            );

            x += width;
        }

        g2.drawString(
                String.valueOf(times.get(times.size() - 1)),
                x,
                y + 85
        );
    }
}