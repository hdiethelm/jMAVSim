package me.drton.jmavsim;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * @author SungTae Moon <munhoney@gmail.com>
 * @file SensorParamPanel.java
 * Sensor Control Parameter Panel
 * <p>
 * This panel is used for the sensor test and analysis
 */

public class SensorParamPanel extends JPanel {
    private JSpinner accelSpinner;
    private JSpinner gyroSpinner;
    private JPanel mainPanel;
    private JSpinner magSpinner;
    private JSpinner magOffSpinner;
    private JSpinner visSpinner;
    private JSpinner presSpinner;
    private JSpinner gpsSpinner;
    private JSpinner massSpinner;
    private JCheckBox gpsCheck;
    private JCheckBox visCheck;
    
    protected Sensors sensors = null;
    protected MAVLinkHILSystem hilSystem = null;
    
    public SensorParamPanel() {

        this.add(mainPanel);

        accelSpinner.setModel(new SpinnerNumberModel(0.0f, 0.0f, 1.0f, 0.01f));
        gyroSpinner.setModel(new SpinnerNumberModel(0.0f, 0.0f, 1.0f, 0.01f));
        gpsSpinner.setModel(new SpinnerNumberModel(0.0f, 0.0f, 100.0f, 1.0f));
        magSpinner.setModel(new SpinnerNumberModel(0.0f, 0.0f, 1.0f, 0.001f));
        magOffSpinner.setModel(new SpinnerNumberModel(0.0f, 0.0f, 365.0f, 1.0f));
        visSpinner.setModel(new SpinnerNumberModel(0.0f, 0.0f, 1.0f, 0.001f));
        presSpinner.setModel(new SpinnerNumberModel(0.0f, 0.0f, 1.0f, 0.01f));
        massSpinner.setModel(new SpinnerNumberModel(0.0f, 0.0f, 5.0f, 0.1f));

        accelSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double value = (Double) accelSpinner.getValue();
                sensors.setParameter("noise_Acc", value.floatValue());
            }
        });

        gyroSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double value = (Double) gyroSpinner.getValue();
                sensors.setParameter("noise_Gyo", value.floatValue());
            }
        });

        magSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double value = (Double) magSpinner.getValue();
                sensors.setParameter("noise_Mag", value.floatValue());
            }
        });

        magOffSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double value = (Double) magOffSpinner.getValue();
                sensors.setParameter("off_Mag", value.floatValue()*(float)Math.PI/180.0f);
            }
        });

        visSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double value = (Double) visSpinner.getValue();
                sensors.setParameter("noise_Vis", value.floatValue());
            }
        });

        presSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double value = (Double) presSpinner.getValue();
                sensors.setParameter("noise_Prs", value.floatValue());
            }
        });

        gpsSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double value = (Double) gpsSpinner.getValue();
                sensors.setParameter("gpsNoiseStdDev", value.floatValue());
            }
        });

        massSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double value = (Double) massSpinner.getValue();
                sensors.setParameter("mass", value.floatValue());
            }
        });
        
        gpsCheck.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                boolean enabled = gpsCheck.isSelected();
                hilSystem.setGPS(enabled);
            }
        });
        
        visCheck.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                boolean enabled = visCheck.isSelected();
                hilSystem.setVision(enabled);
            }
        });

    }

    public JPanel panel() {
        return mainPanel;
    }

    public void setSensor(Sensors sensors) {
        this.sensors = sensors;

        // init value
        accelSpinner.setValue(new Double(sensors.param("noise_Acc")));
        gyroSpinner.setValue(new Double(sensors.param("noise_Gyo")));
        magSpinner.setValue(new Double(sensors.param("noise_Mag")));
        magOffSpinner.setValue(new Double(sensors.param("off_Mag")));
        visSpinner.setValue(new Double(sensors.param("noise_Vis")));
        presSpinner.setValue(new Double(sensors.param("noise_Prs")));
        gpsSpinner.setValue(new Double(sensors.param("gpsNoiseStdDev")));
        massSpinner.setValue(new Double(sensors.param("mass")));
        
    }
    
    public void setHILSystem(MAVLinkHILSystem hilSystem) {
        this.hilSystem = hilSystem;

        // init value
        gpsCheck.setSelected(hilSystem.isGPSEnabled());
        visCheck.setSelected(hilSystem.isVisionEnabled());

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(13, 2, new Insets(0, 0, 0, 0), -1, -1));
        
        final JLabel label0 = new JLabel();
        label0.setText("--- SENSORS ---");
        mainPanel.add(label0, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        final JLabel label1 = new JLabel();
        label1.setText("Accel Noise StdDev");
        mainPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accelSpinner = new JSpinner();
        mainPanel.add(accelSpinner, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        
        final JLabel label2 = new JLabel();
        label2.setText("Gyro Noise StdDev");
        mainPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gyroSpinner = new JSpinner();
        mainPanel.add(gyroSpinner, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        
        final JLabel label3 = new JLabel();
        label3.setText("Mag Noise StdDev");
        mainPanel.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        magSpinner = new JSpinner();
        mainPanel.add(magSpinner, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
     
        final JLabel label31 = new JLabel();
        label31.setText("Mag Offset Deg");
        mainPanel.add(label31, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        magOffSpinner = new JSpinner();
        mainPanel.add(magOffSpinner, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        final JLabel label32 = new JLabel();
        label32.setText("Vision Noise StdDev");
        mainPanel.add(label32, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        visSpinner = new JSpinner();
        mainPanel.add(visSpinner, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        final JLabel label4 = new JLabel();
        label4.setText("Pressure Noise StdDev");
        mainPanel.add(label4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        presSpinner = new JSpinner();
        mainPanel.add(presSpinner, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        final JLabel label5 = new JLabel();
        label5.setText("GPS Noise StdDev");
        mainPanel.add(label5, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpsSpinner = new JSpinner();
        mainPanel.add(gpsSpinner, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        
        final JLabel label6 = new JLabel();
        label6.setText("--- VEHICLE ---");
        mainPanel.add(label6, new GridConstraints(8, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
       
        final JLabel label7 = new JLabel();
        label7.setText("mass");
        mainPanel.add(label7, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        massSpinner = new JSpinner();
        mainPanel.add(massSpinner, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        
        final JLabel label8 = new JLabel();
        label8.setText("--- Global Sensors ---");
        mainPanel.add(label8, new GridConstraints(10, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
       
        final JLabel label9 = new JLabel();
        label9.setText("GPS Enabled");
        mainPanel.add(label9, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gpsCheck = new JCheckBox();
        mainPanel.add(gpsCheck, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        
        final JLabel label10 = new JLabel();
        label10.setText("Vision Enabled");
        mainPanel.add(label10, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        visCheck = new JCheckBox();
        mainPanel.add(visCheck, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
}

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
