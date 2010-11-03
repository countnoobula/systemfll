package GUIPlanner;

//imports
import PlannerSystem.PlannerNoteData;
import Resources.Images.ImageLoader;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PlannerNote extends JPanel {

    private Image i1;
    private String note;
    private int xPos2;
    private int yPos2;
    private Point p;
    private GridBagConstraints gc;
    private JTextArea tArea;
    private Paint gp1;
    private NoteViewer nv;
    private JScrollPane scroll;
    private PlannerNote nested;
    private PlannerNoteData data;
    private boolean included = false;
    private int tempX = 0;
    private int tempY = 0;

    public PlannerNote(NoteViewer nv2, PlannerNoteData data2) {

        //create new instances
        this.nv = nv2;
        this.data = data2;
        this.nested = this;
        this.setLayout(new GridBagLayout());
        this.gc = new GridBagConstraints();
        this.tArea = new JTextArea();
        this.scroll = new JScrollPane(tArea);

        //apply component properties
        tArea.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);

        //add the mouse listeners for the moving of the components
        this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                p = e.getPoint();
                if (e.getPoint().getX() > nested.getWidth() - 20
                        && e.getPoint().getY() > nested.getHeight() - 29) {
                    tempX = (int) (nested.getWidth() - e.getPoint().getX());
                    tempY =
                            (int) (nested.getHeight() - e.getPoint().getY());
                    included = true;
                }
            }

            public void mouseReleased(MouseEvent e) {

                if (included == false) {
                    data.setX(data.getX() + xPos2);
                    data.setY(data.getY() + yPos2);
                    xPos2 = 0;
                    yPos2 = 0;
                    setBounds(getX(), getY(), getWidth(), getHeight());
                    nv.getMainCanvas().updateComponent(nested);
                    nv.getMainCanvas().revalidate();
                } else if (included == true) {
                    included = false;
                }
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {

                if (included == false) {
                    //methods which move the note around
                    xPos2 = (int) (e.getX() - p.getX());
                    yPos2 = (int) (e.getY() - p.getY());
                    data.setX(data.getX() + xPos2);
                    data.setY(data.getY() + yPos2);

                    //set the new bounds of the object
                    setBounds(getX(), getY(), getWidth(), getHeight());

                    //canvas update methods
                    nv.getMainCanvas().updateComponent(nested);
                    nv.getMainCanvas().revalidate();

                } else {
                    data.setWidth((int) e.getPoint().getX() + tempX);
                    data.setHeight((int) e.getPoint().getY() + tempY);
                    setBounds(getX(), getY(), getWidth(), getHeight());
                    nv.getMainCanvas().updateComponent(nested);
                    nv.getMainCanvas().revalidate();
                }
            }

            public void mouseMoved(MouseEvent e) {
            }
        });

        this.setOpaque(false);


        //make the colours graphics
        this.gp1 =
                new GradientPaint(0, 0, new Color(0, 192, 255, 200), 0, 100, new Color(0,
                142,
                200,
                200));
        try {
            i1 =
                    ImageIO.read(ImageLoader.class.getResource("Planner/noteBack.png"));
        } catch (IOException e) {
        }

        data.setX((int) (Math.random() * 400) + 20);
        data.setY((int) (Math.random() * 300) + 20);

        //layout the text area
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 30, 10);

        this.add(scroll, gc);


    }

    public PlannerNote(NoteViewer nv2, PlannerNoteData data2, Color color1, Color color2) {

        //create new instances
        this.nv = nv2;
        this.data = data2;
        this.nested = this;
        this.setLayout(new GridBagLayout());
        this.gc = new GridBagConstraints();
        this.tArea = new JTextArea();
        this.scroll = new JScrollPane(tArea);

        //apply component properties
        tArea.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);

        //add the mouse listeners for the moving of the components
        this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                p = e.getPoint();
                if (e.getPoint().getX() > nested.getWidth() - 20
                        && e.getPoint().getY() > nested.getHeight() - 29) {
                    tempX = (int) (nested.getWidth() - e.getPoint().getX());
                    tempY =
                            (int) (nested.getHeight() - e.getPoint().getY());
                    included = true;
                }
            }

            public void mouseReleased(MouseEvent e) {

                if (included == false) {
                    data.setX(data.getX() + xPos2);
                    data.setY(data.getY() + yPos2);
                    xPos2 = 0;
                    yPos2 = 0;
                    setBounds(getX(), getY(), getWidth(), getHeight());
                    nv.getMainCanvas().updateComponent(nested);
                    nv.getMainCanvas().revalidate();
                } else if (included == true) {
                    included = false;
                }
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {

                if (included == false) {
                    //methods which move the note around
                    xPos2 = (int) (e.getX() - p.getX());
                    yPos2 = (int) (e.getY() - p.getY());
                    data.setX(data.getX() + xPos2);
                    data.setY(data.getY() + yPos2);

                    //set the new bounds of the object
                    setBounds(getX(), getY(), getWidth(), getHeight());

                    //canvas update methods
                    nv.getMainCanvas().updateComponent(nested);
                    nv.getMainCanvas().revalidate();

                } else {
                    data.setWidth((int) e.getPoint().getX() + tempX);
                    data.setHeight((int) e.getPoint().getY() + tempY);
                    setBounds(getX(), getY(), getWidth(), getHeight());
                    nv.getMainCanvas().updateComponent(nested);
                    nv.getMainCanvas().revalidate();
                }
            }

            public void mouseMoved(MouseEvent e) {
            }
        });

        this.setOpaque(false);


        //make the colours graphics



        this.gp1 = new GradientPaint(0, 0, color1, 0, 100, color2);
        try {
            i1 = ImageIO.read(ImageLoader.class.getResource("Planner/noteBack.png"));
        } catch (IOException e) {
        }

        data.setX((int) (Math.random() * 400) + 20);
        data.setY((int) (Math.random() * 300) + 20);

        //layout the text area
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(10, 10, 30, 10);

        this.add(scroll, gc);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(gp1);
        g2d.fillRect(0, 0, this.getWidth() - 1, this.getHeight() - 9);
        g2d.drawImage(i1, 0, this.getHeight() - 9, this.getWidth() - 1, 9,
                null);

    }

    public void setNote(String note) {
        this.note = note;

    }

    public String getNote() {
        return note;
    }

    public void setXPos(int xPos) {

        data.setX(xPos);
    }

    public int getXPos() {
        return data.getX();
    }

    public void setYPos(int yPos) {
        data.setY(yPos);
    }

    public int getYPos() {

        return data.getY();
    }

    public void setWidth(int width) {
        this.data.setWidth(width);
    }

    public int getWidth() {
        return data.getWidth();
    }

    public void setHeight(int height) {
        this.data.setHeight(height);
    }

    public int getHeight() {
        return data.getHeight();
    }
}
