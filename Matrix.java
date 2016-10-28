package com.liuqi.test;

import java.util.Random;


public class Matrix {
	public static Object LOCK = new Object();

	public static final int ROW = 5000;
	public static final int COL = 5000;
	static long endTime;
	static long startTime;


	public static void main(String[] args) {

		int[][] array1=new int[ROW][COL];
		int[][] array2=new int[ROW][COL];

		Random ra =new Random();


		for (int i=0;i<ROW;i++){
			for (int j = 0; j < COL; j++) {
				array1[i][j] = ra.nextInt(10)+1;
				array2[i][j] = ra.nextInt(10)+1;
			} 
		} 
		Matrix add = new Matrix();
		Calc thread1 = add.new Calc(ROW,COL,array1,array2);
		startTime = System.currentTimeMillis();
		System.out.println(startTime);
		thread1.start();

	}

	public class Calc extends Thread{
		private int row;
		private int col;
		private long tempR;
		private int[][] temp1;
		private int[][] temp2;
		private long[][] resaultTemp;

		public Calc(int row, int col,int[][] temp1,int[][] temp2) {
			this.temp1 = temp1;
			this.temp2 = temp2;
			this.row = row;
			this.col = col;

		}


		@Override
		public void run() {
			resaultTemp =  new long[row][row];
			synchronized (LOCK) {
				for (int k = 0; k < row;k++) {
					for (int i = 0; i < row;i++) {
						for (int j = 0; j < col;j++) {
							tempR += temp1[k][j] * temp2[i][j];	
						}
						resaultTemp[k][i] = tempR;	
					}
				}
			}

			Print();

			System.out.println("程序运行时间："+(endTime-startTime)/1000+"s");
		}

		public void Print() {
			for (int k = 0; k < row; k++) {
				for (int t = 0; t < row; t++) {
					System.out.print(resaultTemp[k][t]);
					System.out.print("\t");
					System.out.print("\t");
				}
				System.out.println();


			}
			endTime = System.currentTimeMillis();
			System.out.println(endTime);


		}



	} 


} 




