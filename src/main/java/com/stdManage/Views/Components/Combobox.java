package com.stdManage.Views.Components;

import com.stdManage.Main.Main;
import com.stdManage.Utils.U_Styles;
import com.stdManage.Views.Components.Dialog.Message;
import com.stdManage.Views.Swing.ScrollBar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.html.CSS;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class Combobox<E> extends JComboBox<E> {

    public String getLabeText() {
        return labeText;
    }

    public void setLabeText(String labeText) {
        this.labeText = labeText;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    private String labeText = "Label";
    private Color lineColor = U_Styles.COLOR_PRIMARY;
    private boolean mouseOver;

    @Override
    public void updateUI() {
        super.updateUI();

    }

    public void init(E[] data, int idxSelected, String title, int idxShow) {
        setModel(new javax.swing.DefaultComboBoxModel(data));
        if (data.length > 0) {
            setSelectedIndex(idxSelected);
        }

        setLabeText(title);
        installUI(idxShow);
    }

    /**
     *
     * @param idxValue
     * @return Object[idxValue]
     */
    public Object getObjectValueAt(int idxValue) {
        int idxSelected = this.getSelectedIndex();
        if (idxSelected >= 0) {
            Object[] value = (Object[]) this.getSelectedItem();
            return value[idxValue].toString();
        }
        return null;
    }

    private void installUI(int idxShow) {
        setUI(new ComboUI(this));
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component com = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(5, 5, 5, 5));

                if (isSelected) {
                    //set hover color
                    this.setBackground(U_Styles.COLOR_PRIMARY);
                }
                if (value instanceof Object[]) {
                    Object[] item = (Object[]) value;
                    setText(item[idxShow].toString());
                }
                return com;
            }
        });
    }

    public Combobox() {
        //set bg combobox
        setBackground(U_Styles.COLOR_WHITE);
        setBorder(new EmptyBorder(15, 3, 5, 3));
    }

    private class ComboUI extends BasicComboBoxUI {

        private final Animator animator;
        private boolean animateHinText = true;
        private float location;
        private boolean show;
        private Combobox combo;

        public ComboUI(Combobox combo) {
            this.combo = combo;
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    mouseOver = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    mouseOver = false;

                    repaint();
                }
            });
            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent fe) {
                    showing(false);
                    setBackground(U_Styles.COLOR_WHITE);
                }

                @Override
                public void focusLost(FocusEvent fe) {
                    showing(true);
                    setBackground(U_Styles.COLOR_WHITE);
                }
            });
            addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ie) {
                    if (!isFocusOwner()) {
                        if (getSelectedIndex() == -1) {
                            showing(true);
                        } else {
                            showing(false);
                        }
                    }
                }
            });
            //set arrow color
            addPopupMenuListener(new PopupMenuListener() {
                @Override
                public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
                    if (arrowButton != null) {
                        arrowButton.setBackground(new Color(200, 200, 200));
                    }
                }

                @Override
                public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
                    if (arrowButton != null) {
                        arrowButton.setBackground(new Color(150, 150, 150));
                    }
                }

                @Override
                public void popupMenuCanceled(PopupMenuEvent pme) {
                    if (arrowButton != null) {
                        arrowButton.setBackground(new Color(150, 150, 150));
                    }
                }
            });
            TimingTarget target = new TimingTargetAdapter() {
                @Override
                public void begin() {
                    animateHinText = getSelectedIndex() == -1;
                }

                @Override
                public void timingEvent(float fraction) {
                    location = fraction;
                    repaint();
                }

            };
            animator = new Animator(300, target);
            animator.setResolution(0);
            animator.setAcceleration(0.5f);
            animator.setDeceleration(0.5f);
        }

        @Override
        public void paintCurrentValueBackground(Graphics grphcs, Rectangle rctngl, boolean bln) {

        }

        @Override
        protected JButton createArrowButton() {
            return new ArrowButton();
        }

        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup pop = new BasicComboPopup(comboBox) {
                @Override
                protected JScrollPane createScroller() {
                    //height item
                    list.setFixedCellHeight(U_Styles.HEIGHT_ROW);
                    // list.setPreferredSize(new Dimension(200,200));

//                    JPanel panel = new JPanel();
//                    panel.add(list);
                    JScrollPane scroll = new JScrollPane(list);
                    scroll.setBackground(Color.BLUE);
//                    scroll.setSize(new Dimension(200,200));
//                    scroll.setLocation(scroll.getLocation().x, scroll.getLocation().y - 10);
                    //create scroll mouse
                    ScrollBarCustom sb = new ScrollBarCustom();
                    //số item mỗi lần cuộn chuột
                    sb.setUnitIncrement(10);
                    //scroll mouse color
                    sb.setForeground(U_Styles.COLOR_PRIMARY);
                    scroll.setVerticalScrollBar(sb);
                    scroll.setBorder(new LineBorder(U_Styles.COLOR_BLACK, 2));
                    System.out.println(".createScroller() " + scroll.getSize());
                    return scroll;
                }
            };
            pop.setBorder(new LineBorder(U_Styles.COLOR_GRAY2, 1));
            // pop.setBackground(Color.BLUE);
            //pop.setPreferredSize(new Dimension(200,200));
            return pop;
        }

        //paint hight line
        //jc: is result text
        @Override
        public void paint(Graphics grphcs, JComponent jc) {
            super.paint(grphcs, jc);
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            int width = getWidth();
            int height = getHeight();

            if (mouseOver) {
                g2.setColor(lineColor);
            } else {
                g2.setColor(U_Styles.COLOR_GRAY4);
            }
            g2.fillRect(2, height - 1, width - 4, 1);

            createHintText(g2);
            createLineStyle(g2);
            g2.dispose();
        }

        //create lable text
        private void createHintText(Graphics2D g2) {
            Insets in = getInsets();
            g2.setColor(U_Styles.COLOR_PRIMARY);
            FontMetrics ft = g2.getFontMetrics();
            Rectangle2D r2 = ft.getStringBounds(combo.getLabeText(), g2);
            double height = getHeight() - in.top - in.bottom;
            double textY = (height - r2.getHeight()) / 2;
            double size = 0;
            if (animateHinText) {
                if (show) {
                    size = 18 * (1 - location);
                } else {
                    size = 18 + 2;
                }
            } else {
                size = 18 + 2;
            }
            g2.drawString(combo.getLabeText(), in.right, (int) (in.top + textY + ft.getAscent() - size));
        }

        private void createLineStyle(Graphics2D g2) {
            if (isFocusOwner()) {
                double width = getWidth() - 4;
                int height = getHeight();
                g2.setColor(lineColor);
                double size;
                if (show) {
                    size = width * (1 - location);
                } else {
                    size = width * location;
                }
                double x = (width - size) / 2;
                g2.fillRect((int) (x + 2), height - 2, (int) size, 2);
            }
        }

        private void showing(boolean action) {

            if (animator.isRunning()) {
                animator.stop();
            } else {
                location = 1;
            }
            animator.setStartFraction(1f - location);
            show = action;
            location = 1f - location;
            animator.start();
        }

        private class ArrowButton extends JButton {

            public ArrowButton() {
                setContentAreaFilled(false);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                setBackground(new Color(150, 150, 150));
            }

            @Override
            public void paint(Graphics grphcs) {
                super.paint(grphcs);
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                int size = 10;
                int x = (width - size) / 2;
                int y = (height - size) / 2 + 5;
                int px[] = {x, x + size, x + size / 2};
                int py[] = {y, y, y + size};
                g2.setColor(getBackground());
                g2.fillPolygon(px, py, px.length);
                g2.dispose();
            }
        }
    }
}
