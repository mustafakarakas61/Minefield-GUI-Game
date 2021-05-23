import java.awt.GridLayout;

import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class MayinTarlasi implements MouseListener {

	
	
	static JFrame frame;
	
	
	
	Btn[][] board= new Btn[10][10];
	int openButton;
	
	public MayinTarlasi() {
		openButton=0;
		frame = new JFrame("Mayýn Tarlasý");
	
		
		
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(10,10));
		
		for(int row =0;row<board.length;row++) {
			for(int col=0;col<board[0].length;col++) {
				Btn b= new Btn(row,col);
				frame.add(b);
				b.addMouseListener(this);
				board[row][col]=b;
				
			}
		}
		
		generateMine();
		updateCount();
	//	print();
		
		frame.setVisible(true);
	
	}
	public  void generateMine() {
		int i=0;
		while(i<board[0].length*1+1) {
			int randRow= (int) (Math.random()*board.length);
			int randCol= (int) (Math.random()*board[0].length);
			
			while(board[randRow][randCol].isMine())//mayýn var mý ? isMine
			{
				 randRow= (int) (Math.random()*board.length);
				 randCol= (int) (Math.random()*board[0].length);
			}
			board[randRow][randCol].setMine(true);
			i++;
		}
	}
	public void print() {
		for(int row =0;row<board.length;row++) {
			for(int col=0;col<board[0].length;col++) {
				if(board[row][col].isMine()) {
					board[row][col].setIcon(new ImageIcon("mine.png"));
				}else {
					board[row][col].setText(board[row][col].getCount()+"");
					board[row][col].setEnabled(false);
				}
			}
		}
	}
	public void updateCount() {
		for(int row =0;row<board.length;row++) {
			for(int col=0;col<board[0].length;col++) {
				if(board[row][col].isMine()) {
					counting(row,col);
				}
			}
			}
	}
	public void counting(int row, int col) {
		for(int i=row -1 ;i<=row+1;i++)
		{
			for(int k=col-1;k<=col+1;k++) {
				try {
					int value = board[i][k].getCount();
					board[i][k].setCount(++value);
				} catch (Exception e) {
					
				}
			}
		}
		
	}
	
	public void open(int r,int c) {
		if(r<0||r>=board.length|| c<0||c>=board[0].length||board[r][c].getText().length()>0||board[r][c].isEnabled()==false) {
			return;
		}else if(board[r][c].getCount()!=0) {
			board[r][c].setText(board[r][c].getCount()+"");
			board[r][c].setEnabled(false);
			openButton++;
		}else {
			openButton++;
			board[r][c].setEnabled(false);
			open(r-1,c);
			open(r+1,c);
			open(r,c-1);
			open(r,c+1);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Btn b=(Btn) e.getComponent();
		
		if(e.getButton()==1) {
			System.out.println("Kutuya basýldý !");
			if(b.isMine()) {
				JOptionPane.showMessageDialog(frame,"Mayýn Bastýnýz Oyun Bitti !");
				print();
				yenidenBazlat();
				
			}else {
				open(b.getRow(),b.getCol());
				if(openButton==89) {
					JOptionPane.showMessageDialog(frame,"Tebrikler Oyunu KAZANDINIZ !");
					print();
				}
			}
			
			
		}else if(e.getButton()==3) {
			if(!b.isFlag()) {
				b.setIcon(new ImageIcon("flag.png"));
				System.out.println("Bayrak koyuldu !");
				b.setFlag(true);
			}else {
				b.setIcon(null);
				System.out.println("Bayrak Kaldýrýldý !");
				b.setFlag(false);
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void yenidenBazlat() {
	int control = JOptionPane.showConfirmDialog(frame, "Yeniden Baþlatmak istiyor musunuz?", "Yeniden Bazlat",JOptionPane.YES_NO_OPTION );
		if(control==0) {
			frame.setVisible(false);
			MayinTarlasi oyun = new MayinTarlasi();
			
		}else {
			frame.setVisible(false);
		}
		
	}
	
	
}
