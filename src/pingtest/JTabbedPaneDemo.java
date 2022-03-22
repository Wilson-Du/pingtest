package pingtest;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import java.awt.event.TextListener;
import java.awt.event.TextEvent;
class JTabbedPaneDemo  extends JPanel {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public JTabbedPaneDemo(Router r) {
        super(new GridLayout(1,1));
        JTabbedPane jt=new JTabbedPane();
        JComponent panel1=makeTextPanel(r);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), r.getName()));
        jt.addTab("Name",null, panel1,"Does nothing");

        JComponent panel2=makeTextPanel1(r,"S0/0/1",1);
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), r.getName_s001()));
        jt.addTab("S0/0/1",null,panel2,"Does twice as much nothing");

        JComponent panel3=makeTextPanel1(r,"ga0/0",0);
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), r.getName_ga00()));
        jt.addTab("Ga0/0",null,panel3,"Still does nothing");

        JComponent panel4=makeTextPanel2(r.getRoTable());
        panel4.setPreferredSize(new Dimension(410,50));
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Routing Table of"+" "+r.getName()));
        jt.addTab("Routing Table",null,panel4,"Does nothing at all");

        add(jt);
    }
    public JTabbedPaneDemo(Pc pc) {
        super(new GridLayout(1,1));
        JTabbedPane jt=new JTabbedPane();
        JComponent panel1=makeTextPanel(pc);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), pc.getName()));
        jt.addTab("basic information",null, panel1,"Does nothing");

 /*       JComponent panel2=makeTextPanel(pc.getIp(),pc.getSubmask(),pc.getGateway(),r.getMac_s1());
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), r.getName_s001()));
        jt.addTab("S0/0/1",null,panel2,"Does twice as much nothing");

        JComponent panel3=makeTextPanel(r.getIp("ga0/0"),r.getMask("ga0/0"),r.getNetwork("ga0/0"),r.getMac_s1());
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), r.getName_ga00()));
        jt.addTab("Ga0/0",null,panel3,"Still does nothing");

        JComponent panel4=makeTextPanel1(r.getRoTable());
        panel4.setPreferredSize(new Dimension(410,50));
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Routing Table of"+" "+r.getName()));
        jt.addTab("Routing Table",null,panel4,"Does nothing at all");*/

        add(jt);
    }
    protected JComponent makeTextPanel(Router r){
        JPanel panel=new JPanel(false);
        JLabel jl=new JLabel("Name:");
        TextField jtf=new TextField(16);
        jtf.addTextListener(new TextListener(){
            public void textValueChanged(TextEvent e){
                r.setName(jtf.getText());
            }
        });
        jtf.setText(r.getName());
        panel.add(jl);
        panel.add(jtf);
        return panel;
    }
    protected JComponent makeTextPanel(Pc pc){
        JPanel panel=new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        JLabel jl=new JLabel("Name:");
        TextField jtf=new TextField(16);
        jtf.addTextListener(new TextListener(){
            public void textValueChanged(TextEvent e){
                pc.setName(jtf.getText());
            }
        });
        jtf.setText(pc.getName());
        JLabel jl1=new JLabel("IP address:");
        TextField jtf1=new TextField(16);
        jtf1.addTextListener(new TextListener(){
            public void textValueChanged(TextEvent e){
                pc.setIp(jtf1.getText());
            }
        });
        jtf1.setText(pc.getIp());
        JLabel jl2=new JLabel("Subnet Mask:");
        TextField jtf2=new TextField(16);
        jtf2.addTextListener(new TextListener(){
            public void textValueChanged(TextEvent e){
                pc.setSubmask(jtf2.getText());
            }
        });
        jtf2.setText(pc.getSubmask());
        JLabel jl3=new JLabel("Gateway:");
        TextField jtf3=new TextField(16);
        jtf3.addTextListener(new TextListener(){
            public void textValueChanged(TextEvent e){
                pc.setGateway(jtf3.getText());
            }
        });
        jtf3.setText(pc.getGateway());
        JLabel jl4=new JLabel("Mac address:");
        TextField jtf4=new TextField(16);
        jtf4.setText(pc.getMac());
        constraints.gridx = 0;
        constraints.gridy = 0;     
        panel.add(jl, constraints);
        constraints.gridx = 1;
        panel.add(jtf, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;     
        panel.add(jl1, constraints);
        constraints.gridx = 1;
        panel.add(jtf1, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;     
        panel.add(jl2, constraints);
        constraints.gridx = 1;
        panel.add(jtf2, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;     
        panel.add(jl3, constraints);
        constraints.gridx = 1;
        panel.add(jtf3, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;     
        panel.add(jl4, constraints);
        constraints.gridx = 1;
        panel.add(jtf4, constraints);

        return panel;


    }
    protected JComponent makeTextPanel1(Router r,String n,int num){
        JPanel panel=new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        JLabel jl=new JLabel("IP address:");
        TextField jtf=new TextField(16);
        jtf.addTextListener(new TextListener(){
            public void textValueChanged(TextEvent e){
                r.setIp(num, jtf.getText());
            }
        });
        jtf.setText(r.getIp(n));
        JLabel jl1=new JLabel("Subnet Mask:");
        TextField jtf1=new TextField(16);
        jtf1.addTextListener(new TextListener(){
            public void textValueChanged(TextEvent e){
                r.setMask(num, jtf1.getText());
            }
        });
        jtf1.setText(r.getMask(n));
        JLabel jl2=new JLabel("Network:");
        TextField jtf2=new TextField(16);
        jtf2.addTextListener(new TextListener(){
            public void textValueChanged(TextEvent e){
                r.setNetwork(num,jtf2.getText());
            }
        });
        jtf2.setText(r.getNetwork(n));
        JLabel jl3=new JLabel("Mac address:");
        TextField jtf3=new TextField(16);
        jtf3.setText(r.getMac(n));
        constraints.gridx = 0;
        constraints.gridy = 0;     
        panel.add(jl, constraints);
        constraints.gridx = 1;
        panel.add(jtf, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;     
        panel.add(jl1, constraints);
        constraints.gridx = 1;
        panel.add(jtf1, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;     
        panel.add(jl2, constraints);
        constraints.gridx = 1;
        panel.add(jtf2, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;     
        panel.add(jl3, constraints);
        constraints.gridx = 1;
        panel.add(jtf3, constraints);
        return panel;
    }
    protected JComponent makeTextPanel2(String text){
        JPanel panel=new JPanel(false);
        JLabel jl=new JLabel("Routing Table:");
        JTextArea jta=new JTextArea(20,35);
        jta.insert(text, 1);
        panel.add(jl);
        panel.add(jta);
        return panel;
    }





}


