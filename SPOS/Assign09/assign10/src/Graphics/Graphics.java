package Graphics;
import ProcessDetails.*;
import javax.swing.JFrame;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.util.Queue;

public class Graphics extends JFrame {
	
	JPanel main;

	public Graphics() {
		this.setSize(500,300); 
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		main=new JPanel();
		main.setBounds(0,0,400,300);
		main.setLayout(null);
		
		JLabel proc=new JLabel("Gnatt Chart");
		proc.setBounds(50,50,100,30);
	    proc.setOpaque(true);
	    proc.setBackground(Color.white);
	    proc.setFont(new Font("Seriff",Font.BOLD+Font.ITALIC,12));
	    main.add(proc);
	    proc.setVisible(true);
	    
		this.add(main);
		this.setVisible(true);
	}
	
	public void buildChart(Queue<GnattChart> g) {
	
		int starting=100;
		int colorCnt=0;
		int start=0;
		
		for(GnattChart gi:g) {

			JLabel proc=new JLabel(gi.getProcess());
			JLabel time=new JLabel();
			
			if(start==0) {
				time.setText(start+" "+gi.getTime());
			}
			else {
				time.setText("  "+gi.getTime());
			}
		
			start=gi.getTime();
			proc.setBounds(starting,100,10+2*gi.getTime(),30);
			time.setBounds(starting,130,10+2*gi.getTime(),30);
			starting=starting+10+2*gi.getTime();
			
			
		    proc.setOpaque(true);
		    time.setOpaque(true);
		    //change color of the JLabel
		    if(colorCnt%2==0) {
		       proc.setBackground(Color.white);
		    }
		    else {
			   proc.setBackground(Color.gray);
		    }
		    colorCnt++;
		    proc.setFont(new Font("Seriff",Font.BOLD+Font.ITALIC,8));
		    time.setFont(new Font("Seriff",Font.BOLD+Font.ITALIC,5));
		    main.add(proc);
		    main.add(time);
		    proc.setVisible(true);
		}
	}
}
