package pingtest;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
class GUI {

    private JTabbedPaneDemo jtb;
    private JFrame jf;
    private JPanel jp;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    public GUI(Router r1,Router r2,Pc pc1, Pc pc2){
        jf=new JFrame();
        jf.setTitle("GUI of network");
        jf.setSize(2000,1000);
        jp=new JPanel();
        b1=new JButton(r1.getName());
        b1.setBorderPainted(false);
        b1.setBounds(500, 250, 90, 50); 
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JDialog jd=new JDialog();
                jd.setSize(1000, 500);
                jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                jtb=new JTabbedPaneDemo(r1);
                jd.add(jtb,BorderLayout.CENTER);
                jd.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
                jd.setVisible(true);

            }

        });
        b2=new JButton(r2.getName());
        b2.setBorderPainted(false);
        b2.setBounds(500, 350, 90, 50); 
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JDialog jd2=new JDialog();
                jd2.setSize(1000, 500);
                jd2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                jtb=new JTabbedPaneDemo(r2);
                jd2.add(jtb,BorderLayout.CENTER);
                jd2.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
                jd2.setVisible(true);

            }

        });
        b3=new JButton(pc1.getName());
        b3.setBorderPainted(false);
        b3.setBounds(300, 250, 90, 50); 
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JDialog jd3=new JDialog();
                jd3.setSize(1000, 500);
                jd3.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                jtb=new JTabbedPaneDemo(pc1);
                jd3.add(jtb,BorderLayout.CENTER);
                jd3.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
                jd3.setVisible(true);

            }

        });
        b4=new JButton(pc2.getName());

        b4.setBorderPainted(false);
        b4.setBounds(300, 350, 90, 50); 
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JDialog jd4=new JDialog();
                jd4.setSize(1000, 500);
                jd4.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                jtb=new JTabbedPaneDemo(pc2);
                jd4.add(jtb,BorderLayout.CENTER);
                jd4.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
                jd4.setVisible(true);
                b4.setText(pc2.getName());

            }

        });


    

        jp.setLayout(null);
        jp.add(b1);
        jp.add(b2);
        jp.add(b3);
        jp.add(b4);
        jf.add(jp);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

   

    

}
