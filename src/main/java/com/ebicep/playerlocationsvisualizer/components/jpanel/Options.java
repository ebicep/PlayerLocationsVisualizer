package com.ebicep.playerlocationsvisualizer.components.jpanel;

import com.ebicep.playerlocationsvisualizer.components.jcombobox.GameSelector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Options extends JPanel {

    private final GameSelector gameSelector = new GameSelector();
    private final PlayerSelector playerSelector = new PlayerSelector();
    private final PlayerOptions playerOptions = new PlayerOptions();

    public Options() {
        setName("Options");
        setBorder(new EmptyBorder(new Insets(10, 15, 10, 15)));
        add(gameSelector);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(playerSelector);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(playerOptions);
        add(Box.createRigidArea(new Dimension(0, 500)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public GameSelector getGameSelector() {
        return gameSelector;
    }

    public PlayerSelector getPlayerSelector() {
        return playerSelector;
    }

}
