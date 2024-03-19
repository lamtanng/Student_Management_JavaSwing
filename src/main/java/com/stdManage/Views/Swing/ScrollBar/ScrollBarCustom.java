package com.stdManage.Views.Swing.ScrollBar;

import com.stdManage.Utils.Styles;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setForeground(Styles.COLOR_PRIMARY);
        setUnitIncrement(20);
        setOpaque(false);
    }
}
